package com.ruoyi.flowable.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.object.CallResult;
import com.ruoyi.common.core.domain.object.ResponseResult;
import com.ruoyi.common.core.domain.object.TokenData;
import com.ruoyi.common.core.sequence.wrapper.IdGeneratorWrapper;
import com.ruoyi.common.enums.ErrorCodeEnum;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.flowable.common.constant.FlowApprovalType;
import com.ruoyi.flowable.common.constant.FlowConstant;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.domain.entity.*;
import com.ruoyi.flowable.domain.vo.TaskInfoVo;
import com.ruoyi.flowable.exception.FlowOperationException;
import com.ruoyi.flowable.factory.FlowCustomExtFactory;
import com.ruoyi.flowable.model.*;
import com.ruoyi.flowable.service.*;
import com.ruoyi.flowable.utils.BaseFlowIdentityExtHelper;
import com.ruoyi.flowable.utils.FlowOperationHelper;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: lpeng
 * @Date: 2022-04-03 09:47
 * @Description:
 */

@Slf4j
@Service("flowStaticPageService")
public class FlowStaticPageServiceImpl implements FlowStaticPageService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FlowTaskCommentService flowTaskCommentService;
    @Autowired
    private FlowTaskExtService flowTaskExtService;
    @Autowired
    private FlowWorkOrderService flowWorkOrderService;
    @Autowired
    private FlowMessageService flowMessageService;

    @Autowired
    private FlowCustomExtFactory flowCustomExtFactory;

    @Autowired
    private FlowApiService flowApiService;

    @Autowired
    private FlowOperationHelper flowOperationHelper;

    @Autowired
    private FlowSuspendService flowSuspendService;

    @Autowired
    private IdGeneratorWrapper idGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<String> submitUserTask(String processInstanceId, String taskId, FlowTaskCommentDto flowTaskCommentDto,
                                                 JSONObject taskVariableData, JSONObject masterData, JSONObject slaveData,
                                                 JSONObject copyData, String projectId){
        String errorMessage;
        // 验证流程任务的合法性。
        Task task = flowApiService.getProcessInstanceActiveTask(processInstanceId, taskId);
        ResponseResult<TaskInfoVo> taskInfoResult = flowOperationHelper.verifyAndGetRuntimeTaskInfo(task);
        if (!taskInfoResult.isSuccess()) {
            return ResponseResult.errorFrom(taskInfoResult);
        }
        TaskInfoVo taskInfo = taskInfoResult.getData();

        CallResult assigneeVerifyResult = flowApiService.verifyAssigneeOrCandidateAndClaim(task);
        if (!assigneeVerifyResult.isSuccess()) {
            return ResponseResult.errorFrom(assigneeVerifyResult);
        }

        ProcessInstance instance = flowApiService.getProcessInstance(processInstanceId);
        String dataId = instance.getBusinessKey();
        // 这里把传阅数据放到任务变量中，是为了避免给流程数据操作方法增加额外的方法调用参数。
        if (MapUtil.isNotEmpty(copyData)) {
            if (taskVariableData == null) {
                taskVariableData = new JSONObject();
            }
            taskVariableData.put(FlowConstant.COPY_DATA_KEY, copyData);
        }
        if(taskVariableData == null) {
            taskVariableData = new JSONObject();
        }
        taskVariableData.put("projectId", projectId);

        FlowTaskComment flowTaskComment = BeanUtil.copyProperties(flowTaskCommentDto, FlowTaskComment.class);
        if (StrUtil.isBlank(dataId)) {
            this.saveNewAndTakeTask( processInstanceId, taskId, dataId, flowTaskComment, taskVariableData );
        }
        try {
            if (StrUtil.equals(flowTaskComment.getApprovalType(), FlowApprovalType.TRANSFER)) {
                if (StrUtil.isBlank(flowTaskComment.getDelegateAssginee())) {
                    errorMessage = "数据验证失败，加签或转办任务指派人不能为空！！";
                    return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
                }
            }
            flowApiService.completeTask(task, flowTaskComment, taskVariableData);
        } catch (FlowOperationException e) {
            log.error("Failed to call [FlowOnlineOperationService.updateAndTakeTask]", e);
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, e.getMessage());
        }
        String nextExecutor = "";
        if (processInstanceId != null) {
            nextExecutor = flowApiService.getNextExecutor(processInstanceId);
        }
        return ResponseResult.success(nextExecutor);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Void> rejectRuntimeTask( String processInstanceId,  String taskId, String comment) {
        ResponseResult<Task> taskResult =
                flowOperationHelper.verifySubmitAndGetTask(processInstanceId, taskId, null);
        if (!taskResult.isSuccess()) {
            return ResponseResult.errorFrom(taskResult);
        }
        CallResult result = flowApiService.backToRuntimeTask(taskResult.getData(), null, true, comment);
        if (!result.isSuccess()) {
            return ResponseResult.errorFrom(result);
        }
        return ResponseResult.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Void> startAndTakeUserTask(
             String processDefinitionKey, FlowTaskCommentDto flowTaskCommentDto, JSONObject taskVariableData,
              JSONObject masterData,  JSONObject slaveData, JSONObject copyData, String businessKey) {
        // 1. 验证流程数据的合法性。
        ResponseResult<FlowEntry> flowEntryResult = flowOperationHelper.verifyAndGetFlowEntry(processDefinitionKey);
        if (!flowEntryResult.isSuccess()) {
            return ResponseResult.errorFrom(flowEntryResult);
        }
        String errorMessage;
        // 2. 验证流程一个用户任务的合法性。
        FlowEntryPublish flowEntryPublish = flowEntryResult.getData().getMainFlowEntryPublish();
        if (!flowEntryPublish.getActiveStatus()) {
            errorMessage = "数据验证失败，当前流程发布对象已被挂起，不能启动新流程！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        ResponseResult<TaskInfoVo> taskInfoResult =
                flowOperationHelper.verifyAndGetInitialTaskInfo(flowEntryPublish, true);
        if (!taskInfoResult.isSuccess()) {
            return ResponseResult.errorFrom(taskInfoResult);
        }
        TaskInfoVo taskInfo = taskInfoResult.getData();
        // 这里把传阅数据放到任务变量中，是为了避免给流程数据操作方法增加额外的方法调用参数。
        if (MapUtil.isNotEmpty(copyData)) {
            if (taskVariableData == null) {
                taskVariableData = new JSONObject();
            }
            taskVariableData.put(FlowConstant.COPY_DATA_KEY, copyData);
        }
        FlowTaskComment flowTaskComment = BeanUtil.copyProperties(flowTaskCommentDto, FlowTaskComment.class);
        this.startAndTakeFirstByProcessKey(processDefinitionKey, businessKey,flowTaskComment,taskVariableData);
        return ResponseResult.success();
    }

    /**
     * 暂停流程
     * @param processInstanceId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Void> suspendUserTask(String processInstanceId,String taskId) {
        Task task = flowApiService.getProcessInstanceActiveTask(processInstanceId, taskId);
        //判断是否激活的状态
        if(task!=null){
            ProcessInstance instance = flowApiService.getProcessInstance(processInstanceId);
            FlowSuspend flowSuspend=new FlowSuspend();
            TokenData tokenData = TokenData.takeFromRequest();
            flowSuspend.setSuspendFlowId(idGenerator.nextLongId());
            flowSuspend.setUpdateUserId(tokenData.getUserId());
            flowSuspend.setCreateUserId(tokenData.getUserId());
            Date now = new Date();
            flowSuspend.setUpdateTime(now);
            flowSuspend.setCreateTime(now);
            flowSuspend.setProcessDefinitionKey(instance.getProcessDefinitionKey());
            flowSuspend.setProcessDefinitionName(instance.getProcessDefinitionName());
            flowSuspend.setProcessInstanceId(processInstanceId);
            flowSuspend.setFlowTaskId(taskId);
            flowSuspend.setFlowTaskName(task.getName());
            flowSuspend.setProcessInstanceBusinessKey(instance.getBusinessKey());
            flowSuspendService.getBaseMapper().insert(flowSuspend);
            runtimeService.suspendProcessInstanceById(processInstanceId);
        }
        return ResponseResult.success();
    }

    /**
     * 重新激活
     * @param processInstanceId
     * @return
     */
    @Override
    public ResponseResult<Void> activateUserTask(String processInstanceId,String taskId) {
        FlowSuspend flowSuspend=new FlowSuspend();
        QueryWrapper<FlowSuspend> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("process_instance_id",processInstanceId);
        queryWrapper.eq("flow_task_id",taskId);
        List<FlowSuspend> flowSuspendList=flowSuspendService.getBaseMapper().selectList(queryWrapper);
        if(!CollectionUtils.isEmpty(flowSuspendList)){
            flowSuspendService.getBaseMapper().delete(queryWrapper);
        }
        runtimeService.activateProcessInstanceById(processInstanceId);
        return ResponseResult.success();
    }

    /**
     * 撤回
     * @param processDefinitionKey
     * @param businessKey
     * @param reason
     * @return
     */
    @Override
    public ResponseResult<Void> withdrawUserTask(String processDefinitionKey, String businessKey,
                                                 String reason ) {
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey)
                .processInstanceBusinessKey(businessKey).orderByTaskCreateTime().desc().list();
        if (CollectionUtils.isEmpty(tasks)) {
          return  ResponseResult.error("400","流程未启动或已执行完成,无法撤回！");
        }
        //当前撤回人
        Long userId = TokenData.takeFromRequest().getUserId();
        Task task = tasks.get(0);
        List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()
                .processDefinitionKey(processDefinitionKey)
                .processInstanceBusinessKey(businessKey)
                .orderByTaskCreateTime()
                .desc()
                .list();

        if(!CollectionUtils.isEmpty(htiList)){
            if(!CollectionUtils.isEmpty(htiList.stream().filter(i->i.getExecutionId().equals(userId)).collect(Collectors.toList()))){
                CallResult callResult= flowApiService.backToRuntimeTask(task, null, false,reason);
                if (!callResult.isSuccess()) {
                    return ResponseResult.errorFrom(callResult);
                }
            }else{
                return    ResponseResult.error("400","您无法撤回该流程！");
            }
        }else{
            return    ResponseResult.error("400","您无法撤回该流程2222！");
        }
        return ResponseResult.success();
    }


    //------------------以下属于内部调用------------------
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessInstance startProcess(String processKey, String businessKey, Long userId, Map<String, Object> variableMap) throws Exception{

//        String loginName = TokenData.takeFromRequest().getLoginName();
        String loginName = LoginHelper.getUsername();
        variableMap.put("functionalType", processKey);
        variableMap.put("businessKey", businessKey);
        variableMap.put(FlowConstant.PROC_INSTANCE_INITIATOR_VAR, loginName);
        variableMap.put(FlowConstant.PROC_INSTANCE_START_USER_NAME_VAR, loginName);
        Authentication.setAuthenticatedUserId(loginName);
        //设置流程的发起人(当前登陆作为发起人）
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, businessKey, variableMap);
        //给流程实例首个提交任务的节点设置执行人
        Authentication.setAuthenticatedUserId(null);
        return processInstance;
    }


    /**
     * 启动第一个任务 并完结掉
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProcessInstance startAndTakeFirst(
            String processDefinitionId, Object dataId, FlowTaskComment flowTaskComment, JSONObject taskVariableData) {
        String loginName = TokenData.takeFromRequest().getLoginName();
        Authentication.setAuthenticatedUserId(loginName);
        // 设置流程变量。
        Map<String, Object> variableMap = this.initAndGetProcessInstanceVariables(processDefinitionId);
        // 根据当前流程的主版本，启动一个流程实例，同时将businessKey参数设置为主表主键值。
        ProcessInstance instance = runtimeService.startProcessInstanceById(
                processDefinitionId, dataId.toString(), variableMap);
        // 获取流程启动后的第一个任务。
        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).active().singleResult();
        if (StrUtil.equalsAny(task.getAssignee(), loginName, FlowConstant.START_USER_NAME_VAR)) {
            // 按照规则，调用该方法的用户，就是第一个任务的assignee，因此默认会自动执行complete。
            flowTaskComment.fillWith(task);
            this.completeTask(task, flowTaskComment, taskVariableData);
        }
        return instance;
    }

    /**
     * 启动第一个任务 并完结掉
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProcessInstance startAndTakeFirstByProcessKey(
            String processKey, String businessKey,   FlowTaskComment flowTaskComment, JSONObject taskVariableData) {
//        String loginName = TokenData.takeFromRequest().getLoginName();
        String loginName =LoginHelper.getUsername();
        Map<String, Object> variableMap =new HashMap<>();
        variableMap.put("functionalType", processKey);
        variableMap.put("businessKey", businessKey);
        variableMap.put(FlowConstant.PROC_INSTANCE_INITIATOR_VAR, loginName);
        variableMap.put(FlowConstant.PROC_INSTANCE_START_USER_NAME_VAR, loginName);
        String projectId = (String)RedisUtils.getCacheObject(LoginHelper.getUserId() + ".projectId");
        log.info("startAndTakeFirstByProcessKey.projectId: {}", (String)RedisUtils.getCacheObject(LoginHelper.getUserId() + ".projectId"));
        variableMap.put("projectId", projectId);
        Authentication.setAuthenticatedUserId(loginName);
        // 根据当前流程的主版本，启动一个流程实例，同时将businessKey参数设置为主表主键值。
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, businessKey, variableMap);
        //给流程实例首个提交任务的节点设置执行人
        Authentication.setAuthenticatedUserId(loginName);
        // 设置流程变量。
        variableMap   = this.initAndGetProcessInstanceVariables(processInstance.getProcessInstanceId());
        // 获取流程启动后的第一个任务。
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().singleResult();
        if (StrUtil.equalsAny(task.getAssignee(), loginName, FlowConstant.START_USER_NAME_VAR)) {
            // 按照规则，调用该方法的用户，就是第一个任务的assignee，因此默认会自动执行complete。
            flowTaskComment.fillWith(task);
            flowTaskComment.setApprovalType(FlowApprovalType.SAVE);
            this.completeTask(task, flowTaskComment, taskVariableData);
        }
        // 更新运行时-任务表和任务变量表
        updateRuntimeTaskAndVariable(processInstance);
        return processInstance;
    }

    @Autowired
    private ActRuTaskService actRuTaskService;

    @Autowired
    private ActRuVariableService actRuVariableService;

    private void updateRuntimeTaskAndVariable(ProcessInstance processInstance) {
        ActRuTask actRuTask = new ActRuTask();
        actRuTask.setScopeId("jianguan");
        actRuTask.setScopeType("cmmn");
        actRuTask.setProcessInstanceId(processInstance.getId());
        actRuTaskService.updateActRuTask(actRuTask);
        ActRuVariable actRuVariable = new ActRuVariable();
        actRuVariable.setProcessInstanceId(processInstance.getId());
        actRuVariable.setScopeId("jianguan");
        actRuVariable.setScopeType("cmmn");
        actRuVariableService.updateActRuVariable(actRuVariable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitConsign(HistoricTaskInstance startTaskInstance, Task multiInstanceActiveTask, String newAssignees) {
        JSONArray assigneeArray = JSON.parseArray(newAssignees);
        for (int i = 0; i < assigneeArray.size(); i++) {
            Map<String, Object> variables = new HashMap<>(2);
            variables.put("assignee", assigneeArray.getString(i));
            variables.put(FlowConstant.MULTI_SIGN_START_TASK_VAR, startTaskInstance.getId());
            runtimeService.addMultiInstanceExecution(
                    multiInstanceActiveTask.getTaskDefinitionKey(), multiInstanceActiveTask.getProcessInstanceId(), variables);
        }
        FlowTaskComment flowTaskComment = new FlowTaskComment();
        flowTaskComment.fillWith(startTaskInstance);
        flowTaskComment.setApprovalType(FlowApprovalType.MULTI_CONSIGN);
        String showName = TokenData.takeFromRequest().getLoginName();
        String comment = String.format("用户 [%s] 加签 [%s]。", showName, newAssignees);
        flowTaskComment.setComment(comment);
        flowTaskCommentService.saveNew(flowTaskComment);
        return;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void completeTask(Task task, FlowTaskComment flowTaskComment, JSONObject taskVariableData) {
        JSONObject passCopyData = null;
        if (taskVariableData != null) {
            passCopyData = (JSONObject) taskVariableData.remove(FlowConstant.COPY_DATA_KEY);
        }
        if (flowTaskComment != null) {
            // 这里处理多实例会签逻辑。
            if (flowTaskComment.getApprovalType().equals(FlowApprovalType.MULTI_SIGN)) {
                String loginName = TokenData.takeFromRequest().getLoginName();
                if (taskVariableData == null) {
                    taskVariableData = new JSONObject();
                }
                String assigneeList = taskVariableData.getString(FlowConstant.MULTI_ASSIGNEE_LIST_VAR);
                if (StrUtil.isBlank(assigneeList)) {
                    FlowTaskExt flowTaskExt = flowTaskExtService.getByProcessDefinitionIdAndTaskId(
                            task.getProcessDefinitionId(), task.getTaskDefinitionKey());
                    assigneeList = this.buildMutiSignAssigneeList(flowTaskExt.getOperationListJson());
                    if (assigneeList != null) {
                        taskVariableData.put(FlowConstant.MULTI_ASSIGNEE_LIST_VAR, StrUtil.split(assigneeList,','));
                    }
                }
                Assert.isTrue(StrUtil.isNotBlank(assigneeList));
                taskVariableData.put(FlowConstant.MULTI_AGREE_COUNT_VAR, 0);
                taskVariableData.put(FlowConstant.MULTI_REFUSE_COUNT_VAR, 0);
                taskVariableData.put(FlowConstant.MULTI_ABSTAIN_COUNT_VAR, 0);
                taskVariableData.put(FlowConstant.MULTI_SIGN_NUM_OF_INSTANCES_VAR, 0);
                taskVariableData.put(FlowConstant.MULTI_SIGN_START_TASK_VAR, task.getId());
                String comment = String.format("用户 [%s] 会签 [%s]。", loginName, assigneeList);
                flowTaskComment.setComment(comment);
            }
            // 处理转办。
            if (FlowApprovalType.TRANSFER.equals(flowTaskComment.getApprovalType())) {
                taskService.setAssignee(task.getId(), flowTaskComment.getDelegateAssginee());
                flowTaskComment.fillWith(task);
                flowTaskCommentService.saveNew(flowTaskComment);
                return;
            }
            if (taskVariableData == null) {
                taskVariableData = new JSONObject();
            }
            this.handleMultiInstanceApprovalType(
                    task.getExecutionId(), flowTaskComment.getApprovalType(), taskVariableData);
            taskVariableData.put(FlowConstant.OPERATION_TYPE_VAR, flowTaskComment.getApprovalType());
            flowTaskComment.fillWith(task);
            flowTaskCommentService.saveNew(flowTaskComment);
        }
        // 判断当前完成执行的任务，是否存在抄送设置。
        Object copyData = runtimeService.getVariable(
                task.getProcessInstanceId(), FlowConstant.COPY_DATA_MAP_PREFIX + task.getTaskDefinitionKey());
        if (copyData != null || passCopyData != null) {
            JSONObject copyDataJson = this.mergeCopyData(copyData, passCopyData);
            flowMessageService.saveNewCopyMessage(task, copyDataJson);
        }
        taskService.complete(task.getId(), taskVariableData);
        flowMessageService.updateFinishedStatusByTaskId(task.getId());
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveNewAndTakeTask(
            String processInstanceId,
            String taskId,
            String businessId,
            FlowTaskComment flowTaskComment,
            JSONObject taskVariableData ) {

        Task task = flowApiService.getProcessInstanceActiveTask(processInstanceId, taskId);
        flowApiService.setBusinessKeyForProcessInstance(processInstanceId, businessId);
        Map<String, Object> variables =
                flowApiService.initAndGetProcessInstanceVariables(task.getProcessDefinitionId());
        if (taskVariableData == null) {
            taskVariableData = new JSONObject();
        }
        taskVariableData.putAll(variables);
        flowApiService.completeTask(task, flowTaskComment, taskVariableData);
        ProcessInstance instance = flowApiService.getProcessInstance(processInstanceId);
        flowWorkOrderService.saveNew(instance, businessId,null, null);
    }

    /**
     * 初始化并返回流程实例的变量Map。
     * @param processDefinitionId 流程定义Id。
     * @return 初始化后的流程实例变量Map。
     */
    @Override
    public Map<String, Object> initAndGetProcessInstanceVariables(String processDefinitionId) {
        TokenData tokenData = TokenData.takeFromRequest();
//        String loginName = tokenData.getLoginName();
        String loginName = LoginHelper.getUsername();
        // 设置流程变量。
        Map<String, Object> variableMap = new HashMap<>(4);
        variableMap.put(FlowConstant.PROC_INSTANCE_INITIATOR_VAR, loginName);
        variableMap.put(FlowConstant.PROC_INSTANCE_START_USER_NAME_VAR, loginName);
        List<FlowTaskExt> flowTaskExtList = flowTaskExtService.getByProcessDefinitionId(processDefinitionId);
        boolean hasDeptPostLeader = false;
        boolean hasUpDeptPostLeader = false;
        boolean hasPostCandidateGroup = false;
        for (FlowTaskExt flowTaskExt : flowTaskExtList) {
            if (StrUtil.equals(flowTaskExt.getGroupType(), FlowConstant.GROUP_TYPE_UP_DEPT_POST_LEADER)) {
                hasUpDeptPostLeader = true;
            } else if (StrUtil.equals(flowTaskExt.getGroupType(), FlowConstant.GROUP_TYPE_DEPT_POST_LEADER)) {
                hasDeptPostLeader = true;
            } else if (StrUtil.equals(flowTaskExt.getGroupType(), FlowConstant.GROUP_TYPE_POST)) {
                hasPostCandidateGroup = true;
            }
        }
        // 如果流程图的配置中包含用户身份相关的变量(如：部门领导和上级领导审批)，flowIdentityExtHelper就不能为null。
        // 这个需要子类去实现 BaseFlowIdentityExtHelper 接口，并注册到FlowCustomExtFactory的工厂中。
        BaseFlowIdentityExtHelper flowIdentityExtHelper = flowCustomExtFactory.getFlowIdentityExtHelper();
        if (hasUpDeptPostLeader) {
            Assert.notNull(flowIdentityExtHelper);
            Object upLeaderDeptPostId = flowIdentityExtHelper.getUpLeaderDeptPostId(tokenData.getDeptId());
            if (upLeaderDeptPostId != null) {
                variableMap.put(FlowConstant.GROUP_TYPE_UP_DEPT_POST_LEADER_VAR, upLeaderDeptPostId.toString());
            }
        }
        if (hasDeptPostLeader) {
            Assert.notNull(flowIdentityExtHelper);
            Object leaderDeptPostId = flowIdentityExtHelper.getLeaderDeptPostId(tokenData.getDeptId());
            if (leaderDeptPostId != null) {
                variableMap.put(FlowConstant.GROUP_TYPE_DEPT_POST_LEADER_VAR, leaderDeptPostId.toString());
            }
        }
        if (hasPostCandidateGroup) {
            Assert.notNull(flowIdentityExtHelper);
            Map<String, Object> postGroupDataMap =
                    this.buildPostCandidateGroupData(flowIdentityExtHelper, flowTaskExtList);
            variableMap.putAll(postGroupDataMap);
        }
        this.buildCopyData(flowTaskExtList, variableMap);
        return variableMap;
    }

    private Map<String, Object> buildPostCandidateGroupData(
            BaseFlowIdentityExtHelper flowIdentityExtHelper, List<FlowTaskExt> flowTaskExtList) {
        Map<String, Object> postVariableMap = new HashMap<>();
        Set<String> selfPostIdSet = new HashSet<>();
        Set<String> upPostIdSet = new HashSet<>();
        for (FlowTaskExt flowTaskExt : flowTaskExtList) {
            if (flowTaskExt.getGroupType().equals(FlowConstant.GROUP_TYPE_POST)) {
                Assert.notNull(flowTaskExt.getDeptPostListJson());
                List<FlowTaskPostCandidateGroup> groupDataList =
                        JSONArray.parseArray(flowTaskExt.getDeptPostListJson(), FlowTaskPostCandidateGroup.class);
                for (FlowTaskPostCandidateGroup groupData : groupDataList) {
                    if (groupData.getType().equals(FlowConstant.GROUP_TYPE_SELF_DEPT_POST_VAR)) {
                        selfPostIdSet.add(groupData.getPostId());
                    } else if (groupData.getType().equals(FlowConstant.GROUP_TYPE_UP_DEPT_POST_VAR)) {
                        upPostIdSet.add(groupData.getPostId());
                    }
                }
            }
        }
        if (CollUtil.isNotEmpty(selfPostIdSet)) {
            Map<String, String> deptPostIdMap =
                    flowIdentityExtHelper.getDeptPostIdMap(TokenData.takeFromRequest().getDeptId(), selfPostIdSet);
            for (String postId : selfPostIdSet) {
                if (MapUtil.isNotEmpty(deptPostIdMap) && deptPostIdMap.containsKey(postId)) {
                    String deptPostId = deptPostIdMap.get(postId);
                    postVariableMap.put(FlowConstant.SELF_DEPT_POST_PREFIX + postId, deptPostId);
                } else {
                    postVariableMap.put(FlowConstant.SELF_DEPT_POST_PREFIX + postId, "");
                }
            }
        }
        if (CollUtil.isNotEmpty(upPostIdSet)) {
            Map<String, String> upDeptPostIdMap =
                    flowIdentityExtHelper.getUpDeptPostIdMap(TokenData.takeFromRequest().getDeptId(), upPostIdSet);
            for (String postId : upPostIdSet) {
                if (MapUtil.isNotEmpty(upDeptPostIdMap) && upDeptPostIdMap.containsKey(postId)) {
                    String upDeptPostId = upDeptPostIdMap.get(postId);
                    postVariableMap.put(FlowConstant.UP_DEPT_POST_PREFIX + postId, upDeptPostId);
                } else {
                    postVariableMap.put(FlowConstant.UP_DEPT_POST_PREFIX + postId, "");
                }
            }
        }
        return postVariableMap;
    }

    private void buildCopyData(List<FlowTaskExt> flowTaskExtList, Map<String, Object> variableMap) {
        TokenData tokenData = TokenData.takeFromRequest();
        for (FlowTaskExt flowTaskExt : flowTaskExtList) {
            if (StrUtil.isBlank(flowTaskExt.getCopyListJson())) {
                continue;
            }
            List<JSONObject> copyDataList = JSON.parseArray(flowTaskExt.getCopyListJson(), JSONObject.class);
            Map<String, Object> copyDataMap = new HashMap<>(copyDataList.size());
            for (JSONObject copyData : copyDataList) {
                String type = copyData.getString("type");
                String id = copyData.getString("id");
                copyDataMap.put(type, id == null ? "" : id);
            }
            variableMap.put(FlowConstant.COPY_DATA_MAP_PREFIX + flowTaskExt.getTaskId(), JSON.toJSONString(copyDataMap));
        }
    }


    private String buildMutiSignAssigneeList(String operationListJson) {
        FlowTaskMultiSignAssign multiSignAssignee = null;
        List<FlowTaskOperation> taskOperationList = JSONArray.parseArray(operationListJson, FlowTaskOperation.class);
        for (FlowTaskOperation taskOperation : taskOperationList) {
            if ("multi_sign".equals(taskOperation.getType())) {
                multiSignAssignee = taskOperation.getMultiSignAssignee();
                break;
            }
        }
        Assert.notNull(multiSignAssignee);
        if (FlowTaskMultiSignAssign.ASSIGN_TYPE_USER.equals(multiSignAssignee.getAssigneeType())) {
            return multiSignAssignee.getAssigneeList();
        }
        Set<String> usernameSet = null;
        BaseFlowIdentityExtHelper extHelper = flowCustomExtFactory.getFlowIdentityExtHelper();
        Set<String> idSet = CollUtil.newHashSet(StrUtil.split(multiSignAssignee.getAssigneeList(), ","));
        switch (multiSignAssignee.getAssigneeType()) {
            case FlowTaskMultiSignAssign.ASSIGN_TYPE_ROLE:
                usernameSet = extHelper.getUsernameListByRoleIds(idSet);
                break;
            case FlowTaskMultiSignAssign.ASSIGN_TYPE_DEPT:
                usernameSet = extHelper.getUsernameListByDeptIds(idSet);
                break;
            case FlowTaskMultiSignAssign.ASSIGN_TYPE_POST:
                usernameSet = extHelper.getUsernameListByPostIds(idSet);
                break;
            case FlowTaskMultiSignAssign.ASSIGN_TYPE_DEPT_POST:
                usernameSet = extHelper.getUsernameListByDeptPostIds(idSet);
                break;
            default:
                break;
        }
        return CollUtil.isEmpty(usernameSet) ? null : CollUtil.join(usernameSet, ",");
    }
    private void handleMultiInstanceApprovalType(String executionId, String approvalType, JSONObject taskVariableData) {
        if (StrUtil.isBlank(approvalType)) {
            return;
        }
        if (StrUtil.equalsAny(approvalType,
                FlowApprovalType.MULTI_AGREE,
                FlowApprovalType.MULTI_REFUSE,
                FlowApprovalType.MULTI_ABSTAIN)) {
            Map<String, Object> variables = runtimeService.getVariables(executionId);
            Integer agreeCount = (Integer) variables.get(FlowConstant.MULTI_AGREE_COUNT_VAR);
            Integer refuseCount = (Integer) variables.get(FlowConstant.MULTI_REFUSE_COUNT_VAR);
            Integer abstainCount = (Integer) variables.get(FlowConstant.MULTI_ABSTAIN_COUNT_VAR);
            Integer nrOfInstances = (Integer) variables.get(FlowConstant.NUMBER_OF_INSTANCES_VAR);
            taskVariableData.put(FlowConstant.MULTI_AGREE_COUNT_VAR, agreeCount);
            taskVariableData.put(FlowConstant.MULTI_REFUSE_COUNT_VAR, refuseCount);
            taskVariableData.put(FlowConstant.MULTI_ABSTAIN_COUNT_VAR, abstainCount);
            taskVariableData.put(FlowConstant.MULTI_SIGN_NUM_OF_INSTANCES_VAR, nrOfInstances);
            switch (approvalType) {
                case FlowApprovalType.MULTI_AGREE:
                    if (agreeCount == null) {
                        agreeCount = 0;
                    }
                    taskVariableData.put(FlowConstant.MULTI_AGREE_COUNT_VAR, agreeCount + 1);
                    break;
                case FlowApprovalType.MULTI_REFUSE:
                    if (refuseCount == null) {
                        refuseCount = 0;
                    }
                    taskVariableData.put(FlowConstant.MULTI_REFUSE_COUNT_VAR, refuseCount + 1);
                    break;
                case FlowApprovalType.MULTI_ABSTAIN:
                    if (abstainCount == null) {
                        abstainCount = 0;
                    }
                    taskVariableData.put(FlowConstant.MULTI_ABSTAIN_COUNT_VAR, abstainCount + 1);
                    break;
                default:
                    break;
            }
        }
    }


    private JSONObject mergeCopyData(Object copyData, JSONObject passCopyData) {
        TokenData tokenData = TokenData.takeFromRequest();
        // passCopyData是传阅数据，copyData是抄送数据。
        JSONObject resultCopyDataJson = passCopyData;
        if (resultCopyDataJson == null) {
            resultCopyDataJson = JSON.parseObject(copyData.toString());
        } else if (copyData != null) {
            JSONObject copyDataJson = JSON.parseObject(copyData.toString());
            for (Map.Entry<String, Object> entry : copyDataJson.entrySet()) {
                String value = resultCopyDataJson.getString(entry.getKey());
                if (value == null) {
                    resultCopyDataJson.put(entry.getKey(), entry.getValue());
                } else {
                    List<String> list1 = StrUtil.split(value, ",");
                    List<String> list2 = StrUtil.split(entry.getValue().toString(), ",");
                    Set<String> valueSet = new HashSet<>(list1);
                    valueSet.addAll(list2);
                    resultCopyDataJson.put(entry.getKey(), StrUtil.join(",", valueSet));
                }
            }
        }
        BaseFlowIdentityExtHelper flowIdentityExtHelper = flowCustomExtFactory.getFlowIdentityExtHelper();
        for (Map.Entry<String, Object> entry : resultCopyDataJson.entrySet()) {
            String type = entry.getKey();
            switch (type) {
                case FlowConstant.GROUP_TYPE_UP_DEPT_POST_LEADER_VAR:
                    Object upLeaderDeptPostId =
                            flowIdentityExtHelper.getUpLeaderDeptPostId(tokenData.getDeptId());
                    entry.setValue(upLeaderDeptPostId);
                    break;
                case FlowConstant.GROUP_TYPE_DEPT_POST_LEADER_VAR:
                    Object leaderDeptPostId =
                            flowIdentityExtHelper.getLeaderDeptPostId(tokenData.getDeptId());
                    entry.setValue(leaderDeptPostId);
                    break;
                case FlowConstant.GROUP_TYPE_SELF_DEPT_POST_VAR:
                    Set<String> selfPostIdSet = new HashSet<>(StrUtil.split(entry.getValue().toString(), ","));
                    Map<String, String> deptPostIdMap =
                            flowIdentityExtHelper.getDeptPostIdMap(tokenData.getDeptId(), selfPostIdSet);
                    String deptPostIdValues = "";
                    if (deptPostIdMap != null) {
                        deptPostIdValues = StrUtil.join(",", deptPostIdMap.values());
                    }
                    entry.setValue(deptPostIdValues);
                    break;
                case FlowConstant.GROUP_TYPE_UP_DEPT_POST_VAR:
                    Set<String> upPostIdSet = new HashSet<>(StrUtil.split(entry.getValue().toString(), ","));
                    Map<String, String> upDeptPostIdMap =
                            flowIdentityExtHelper.getUpDeptPostIdMap(tokenData.getDeptId(), upPostIdSet);
                    String upDeptPostIdValues = "";
                    if (upDeptPostIdMap != null) {
                        upDeptPostIdValues = StrUtil.join(",", upDeptPostIdMap.values());
                    }
                    entry.setValue(upDeptPostIdValues);
                    break;
                default:
                    break;
            }
        }
        return resultCopyDataJson;
    }

    @Override
    public Task getTaskById(String taskId){
        return flowApiService.getTaskById(taskId);
    }
}
