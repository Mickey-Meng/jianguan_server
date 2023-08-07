package com.ruoyi.web.controller.jianguan.business.mytask;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.DisableDataFilter;
import com.ruoyi.common.annotation.MyRequestBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.domain.object.*;
import com.ruoyi.common.enums.ErrorCodeEnum;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.MyPageUtil;
import com.ruoyi.flowable.common.constant.*;
import com.ruoyi.flowable.domain.entity.ActRuTask;
import com.ruoyi.flowable.domain.entity.ActRuVariable;
import com.ruoyi.flowable.domain.vo.FlowTaskCommentVo;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.flowable.domain.vo.TaskInfoVo;
import com.ruoyi.flowable.factory.FlowCustomExtFactory;
import com.ruoyi.flowable.model.*;
import com.ruoyi.flowable.service.*;
import com.ruoyi.flowable.utils.FlowOperationHelper;
import com.ruoyi.workflow.plugin.FlowablePluginExecutor;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程操作接口类
 *
 * @author Jerry
 * @date 2021-06-06
 */
@Api(tags = "通用流程操作接口")
@Slf4j
@RestController
@RequestMapping("${common-flow.urlPrefix}/flowOperation")
public class FlowOperationController {

    @Autowired
    private FlowEntryService flowEntryService;
    @Autowired
    private FlowTaskCommentService flowTaskCommentService;
    @Autowired
    private FlowTaskExtService flowTaskExtService;
    @Autowired
    private FlowApiService flowApiService;
    @Autowired
    private FlowWorkOrderService flowWorkOrderService;
    @Autowired
    private FlowMessageService flowMessageService;
    @Autowired
    private FlowOperationHelper flowOperationHelper;
    @Autowired
    private FlowCustomExtFactory flowCustomExtFactory;
    @Autowired
    private FlowTaskHandleService flowTaskHandleService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private FlowablePluginExecutor flowablePluginExecutor;
    @Autowired
    private ActRuTaskService actRuTaskService;
    @Autowired
    private ActRuVariableService actRuVariableService;

    /**
     * 根据指定流程的主版本，发起一个流程实例。
     *
     * @param processDefinitionKey 流程标识。
     * @return 应答结果对象。
     */
    @PostMapping("/startOnly")
    public ResponseResult<Void> startOnly(@MyRequestBody(required = true) String processDefinitionKey) {
        // 1. 验证流程数据的合法性。
        ResponseResult<FlowEntry> flowEntryResult = flowOperationHelper.verifyAndGetFlowEntry(processDefinitionKey);
        if (!flowEntryResult.isSuccess()) {
            return ResponseResult.errorFrom(flowEntryResult);
        }
        // 2. 验证流程一个用户任务的合法性。
        FlowEntryPublish flowEntryPublish = flowEntryResult.getData().getMainFlowEntryPublish();
        ResponseResult<TaskInfoVo> taskInfoResult =
                flowOperationHelper.verifyAndGetInitialTaskInfo(flowEntryPublish, false);
        if (!taskInfoResult.isSuccess()) {
            return ResponseResult.errorFrom(taskInfoResult);
        }
        flowApiService.start(flowEntryPublish.getProcessDefinitionId(), null);
        return ResponseResult.success();
    }

    /**
     * 获取开始节点之后的第一个任务节点的数据。
     *
     * @param processDefinitionKey 流程标识。
     * @return 任务节点的自定义对象数据。
     */
    @GetMapping("/viewInitialTaskInfo")
    public ResponseResult<TaskInfoVo> viewInitialTaskInfo(@RequestParam String processDefinitionKey) {
        ResponseResult<FlowEntry> flowEntryResult = flowOperationHelper.verifyAndGetFlowEntry(processDefinitionKey);
        if (!flowEntryResult.isSuccess()) {
            return ResponseResult.errorFrom(flowEntryResult);
        }
        FlowEntryPublish flowEntryPublish = flowEntryResult.getData().getMainFlowEntryPublish();
        String initTaskInfo = flowEntryPublish.getInitTaskInfo();
        TaskInfoVo taskInfo = StrUtil.isBlank(initTaskInfo)
                ? null : JSON.parseObject(initTaskInfo, TaskInfoVo.class);
        if (taskInfo != null) {
//            String loginName = TokenData.takeFromRequest().getLoginName();
            taskInfo.setAssignedMe(StrUtil.equalsAny(
                    taskInfo.getAssignee(), LoginHelper.getUsername(), FlowConstant.START_USER_NAME_VAR));
        }
        return ResponseResult.success(taskInfo);
    }

    /**
     * 获取流程实例所有审批人
     *
     * @param processInstanceId 流程实例Id。
     * @return 操作应答结果。
     */
    @GetMapping("/getProcessNodeExecutors")
    public ResponseResult getProcessNodeExecutors(String processDefinitionId, String processInstanceId) {
        Object result = flowApiService.getProcessNodeExecutors(processDefinitionId, processInstanceId);
        return ResponseResult.success(result);
    }


    /**
     * 获取流程运行时指定任务的信息。
     *
     * @param processDefinitionId 流程引擎的定义Id。
     * @param processInstanceId   流程引擎的实例Id。
     * @param taskId              流程引擎的任务Id。
     * @return 任务节点的自定义对象数据。
     */
    @GetMapping("/viewRuntimeTaskInfo")
    public ResponseResult<TaskInfoVo> viewRuntimeTaskInfo(
            @RequestParam String processDefinitionId,
            @RequestParam String processInstanceId,
            @RequestParam String taskId) {
        Task task = flowApiService.getProcessInstanceActiveTask(processInstanceId, taskId);
        ProcessInstance processInstance = flowApiService.getProcessInstance(processInstanceId);
        ResponseResult<TaskInfoVo> taskInfoResult = flowOperationHelper.verifyAndGetRuntimeTaskInfo(task);
        if (!taskInfoResult.isSuccess()) {
            return ResponseResult.errorFrom(taskInfoResult);
        }
        TaskInfoVo taskInfoVo = taskInfoResult.getData();
        taskInfoVo.setTaskStatus(FlowTaskStatus.APPROVING + "");
        FlowTaskExt flowTaskExt =
                flowTaskExtService.getByProcessDefinitionIdAndTaskId(processDefinitionId, taskInfoVo.getTaskKey());
        if (flowTaskExt != null) {
            if (StrUtil.isNotBlank(flowTaskExt.getOperationListJson())) {
                taskInfoVo.setOperationList(JSON.parseArray(flowTaskExt.getOperationListJson(), JSONObject.class));
            }
            if (StrUtil.isNotBlank(flowTaskExt.getVariableListJson())) {
                taskInfoVo.setVariableList(JSON.parseArray(flowTaskExt.getVariableListJson(), JSONObject.class));
            }
            if (StrUtil.isNotBlank(flowTaskExt.getPropertyListJson())) {
                taskInfoVo.setPropertyList(JSON.parseArray(flowTaskExt.getPropertyListJson(), JSONObject.class));
            }
        }
        if (processInstance != null && processInstance.getBusinessKey() != null) {
            taskInfoVo.setBusinessKey(processInstance.getBusinessKey());
        }
        if (processInstance != null && processInstance.getProcessDefinitionKey() != null) {
            taskInfoVo.setProcessDefinitionKey(processInstance.getProcessDefinitionKey());
        }
        // 設置 任务为在办
        FlowTaskHandle flowTaskHandle = new FlowTaskHandle();
        flowTaskHandle.setTaskId(taskId);
        flowTaskHandle.setTaskKey(taskInfoVo.getTaskKey());
        flowTaskHandle.setProcessDefinitionId(processDefinitionId);
        flowTaskHandle.setProcessInstanceId(processInstanceId);
        flowTaskHandle.setTaskHandleStatus(FlowTaskHandleStatus.HANDLEING);
//        TokenData tokenData = TokenData.takeFromRequest();
        LoginUser loginUser = LoginHelper.getLoginUser();
        flowTaskHandle.setTaskHandleUserId(loginUser.getUserId());
        flowTaskHandle.setTaskHandleUserLoginName(loginUser.getUsername());
        flowTaskHandle.setTaskHandleUserName(loginUser.getNickName());
        flowTaskHandle.setProcessDefinitionKey(processInstance.getProcessDefinitionKey());
        flowTaskHandle.setProcessDefinitionName(processInstance.getProcessDefinitionName());
        flowTaskHandleService.saveUnHandleNew(flowTaskHandle);
        return ResponseResult.success(taskInfoVo);
    }

    /**
     * 获取流程运行时指定任务的信息。
     *
     * @param processDefinitionId 流程引擎的定义Id。
     * @param processInstanceId   流程引擎的实例Id。
     * @param taskId              流程引擎的任务Id。
     * @return 任务节点的自定义对象数据。
     */
    @GetMapping("/viewHistoricTaskInfo")
    public ResponseResult<TaskInfoVo> viewHistoricTaskInfo(
            @RequestParam String processDefinitionId,
            @RequestParam String processInstanceId,
            @RequestParam String taskId) {
        String errorMessage;
        HistoricTaskInstance taskInstance = flowApiService.getHistoricTaskInstance(processInstanceId, taskId);
        String loginName = TokenData.takeFromRequest().getLoginName();
        if (!StrUtil.equals(taskInstance.getAssignee(), loginName)) {
            errorMessage = "数据验证失败，当前用户不是指派人！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        TaskInfoVo taskInfoVo = JSON.parseObject(taskInstance.getFormKey(), TaskInfoVo.class);
        taskInfoVo.setTaskStatus(FlowTaskStatus.SUBMITTED + "");
        FlowTaskExt flowTaskExt =
                flowTaskExtService.getByProcessDefinitionIdAndTaskId(processDefinitionId, taskInstance.getTaskDefinitionKey());
        if (flowTaskExt != null) {
            if (StrUtil.isNotBlank(flowTaskExt.getOperationListJson())) {
                taskInfoVo.setOperationList(JSON.parseArray(flowTaskExt.getOperationListJson(), JSONObject.class));
            }
            if (StrUtil.isNotBlank(flowTaskExt.getVariableListJson())) {
                taskInfoVo.setVariableList(JSON.parseArray(flowTaskExt.getVariableListJson(), JSONObject.class));
            }
            if (StrUtil.isNotBlank(flowTaskExt.getPropertyListJson())) {
                taskInfoVo.setPropertyList(JSON.parseArray(flowTaskExt.getPropertyListJson(), JSONObject.class));
            }
        }
        HistoricProcessInstance processInstance = flowApiService.getHistoricProcessInstance(processInstanceId);
        if (processInstance != null && processInstance.getBusinessKey() != null) {
            taskInfoVo.setBusinessKey(processInstance.getBusinessKey());
        }
        if (processInstance != null && processInstance.getProcessDefinitionKey() != null) {
            taskInfoVo.setProcessDefinitionKey(processInstance.getProcessDefinitionKey());
        }
        return ResponseResult.success(taskInfoVo);
    }

    /**
     * 获取第一个提交表单数据的任务信息。
     *
     * @param processInstanceId 流程实例Id。
     * @return 任务节点的自定义对象数据。
     */
    @GetMapping("/viewInitialHistoricTaskInfo")
    public ResponseResult<TaskInfoVo> viewInitialHistoricTaskInfo(@RequestParam String processInstanceId) {
        String errorMessage;
        List<FlowTaskComment> taskCommentList =
                flowTaskCommentService.getFlowTaskCommentList(processInstanceId);
        if (CollUtil.isEmpty(taskCommentList)) {
            return ResponseResult.error(ErrorCodeEnum.DATA_NOT_EXIST);
        }
        FlowTaskComment taskComment = taskCommentList.get(0);
        HistoricTaskInstance task = flowApiService.getHistoricTaskInstance(processInstanceId, taskComment.getTaskId());
        if (StrUtil.isBlank(task.getFormKey())) {
            errorMessage = "数据验证失败，指定任务的formKey属性不存在，请重新修改流程图！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        TaskInfoVo taskInfo = JSON.parseObject(task.getFormKey(), TaskInfoVo.class);
        taskInfo.setTaskKey(task.getTaskDefinitionKey());

        taskInfo.setTaskStatus(FlowTaskStatus.SUBMITTED + "");
        FlowTaskExt flowTaskExt =
                flowTaskExtService.getByProcessDefinitionIdAndTaskId(task.getProcessDefinitionId(), task.getTaskDefinitionKey());
        if (flowTaskExt != null) {
            if (StrUtil.isNotBlank(flowTaskExt.getOperationListJson())) {
                taskInfo.setOperationList(JSON.parseArray(flowTaskExt.getOperationListJson(), JSONObject.class));
            }
            if (StrUtil.isNotBlank(flowTaskExt.getVariableListJson())) {
                taskInfo.setVariableList(JSON.parseArray(flowTaskExt.getVariableListJson(), JSONObject.class));
            }
            if (StrUtil.isNotBlank(flowTaskExt.getPropertyListJson())) {
                taskInfo.setPropertyList(JSON.parseArray(flowTaskExt.getPropertyListJson(), JSONObject.class));
            }
        }
        HistoricProcessInstance processInstance = flowApiService.getHistoricProcessInstance(processInstanceId);
        if (processInstance != null && processInstance.getBusinessKey() != null) {
            taskInfo.setBusinessKey(processInstance.getBusinessKey());
        }
        if (processInstance != null && processInstance.getProcessDefinitionKey() != null) {
            taskInfo.setProcessDefinitionKey(processInstance.getProcessDefinitionKey());
        }
        return ResponseResult.success(taskInfo);
    }

    /**
     * 根据消息Id，获取流程Id关联的业务数据。
     * NOTE：白名单接口。
     *
     * @param messageId 抄送消息Id。
     * @param snapshot  是否获取抄送或传阅时任务的业务快照数据。如果为true，后续任务导致的业务数据修改，将不会返回给前端。
     * @return 抄送消息关联的流程实例业务数据。
     */
    @DisableDataFilter
    @GetMapping("/viewCopyBusinessData")
    public ResponseResult<JSONObject> viewCopyBusinessData(
            @RequestParam Long messageId, @RequestParam(required = false) Boolean snapshot) {
        String errorMessage;
        // 验证流程任务的合法性。
        FlowMessage flowMessage = flowMessageService.getById(messageId);
        if (flowMessage == null) {
            return ResponseResult.error(ErrorCodeEnum.DATA_NOT_EXIST);
        }
        if (flowMessage.getMessageType() != FlowMessageType.COPY_TYPE) {
            errorMessage = "数据验证失败，当前消息不是抄送类型消息！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        if (flowMessage.getOnlineFormData() == null || flowMessage.getOnlineFormData()) {
            errorMessage = "数据验证失败，当前消息为在线表单数据，不能通过该接口获取！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        if (!flowMessageService.isCandidateIdentityOnMessage(messageId)) {
            errorMessage = "数据验证失败，当前用户没有权限访问该消息！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        JSONObject businessObject = null;
        if (snapshot != null && snapshot) {
            if (StrUtil.isNotBlank(flowMessage.getBusinessDataShot())) {
                businessObject = JSON.parseObject(flowMessage.getBusinessDataShot());
            }
            return ResponseResult.success(businessObject);
        }
        ProcessInstance instance = flowApiService.getProcessInstance(flowMessage.getProcessInstanceId());
        // 如果业务主数据为空，则直接返回。
        if (StrUtil.isBlank(instance.getBusinessKey())) {
            errorMessage = "数据验证失败，当前消息为所属流程实例没有包含业务主键Id！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        String businessData = flowCustomExtFactory.getBusinessDataExtHelper().getBusinessData(
                flowMessage.getProcessDefinitionKey(), flowMessage.getProcessInstanceId(), instance.getBusinessKey());
        if (StrUtil.isNotBlank(businessData)) {
            businessObject = JSON.parseObject(businessData);
        }
        // 将当前消息更新为已读
        flowMessageService.readCopyTask(messageId);
        return ResponseResult.success(businessObject);
    }

    /**
     * 提交多实例加签。
     *
     * @param processInstanceId 流程实例Id。
     * @param taskId            多实例任务的上一级任务Id。
     * @param newAssignees      新的加签人列表，多个指派人之间逗号分隔。
     * @return 应答结果。
     */
    @PostMapping("/submitConsign")
    public ResponseResult<Void> submitConsign(
            @MyRequestBody(required = true) String processInstanceId,
            @MyRequestBody(required = true) String taskId,
            @MyRequestBody(required = true) String newAssignees) {
        String errorMessage;
        if (!flowApiService.existActiveProcessInstance(processInstanceId)) {
            errorMessage = "数据验证失败，当前流程实例已经结束，不能执行加签！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        HistoricTaskInstance taskInstance = flowApiService.getHistoricTaskInstance(processInstanceId, taskId);
        if (taskInstance == null) {
            errorMessage = "数据验证失败，当前任务不存在！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        if (!StrUtil.equals(taskInstance.getAssignee(), TokenData.takeFromRequest().getLoginName())) {
            errorMessage = "数据验证失败，任务指派人与当前用户不匹配！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        List<Task> activeTaskList = flowApiService.getProcessInstanceActiveTaskList(processInstanceId);
        Task activeMultiInstanceTask = null;
        for (Task activeTask : activeTaskList) {
            Object startTaskId = flowApiService.getTaskVariable(
                    activeTask.getId(), FlowConstant.MULTI_SIGN_START_TASK_VAR);
            if (startTaskId != null && startTaskId.toString().equals(taskId)) {
                activeMultiInstanceTask = activeTask;
                break;
            }
        }
        if (activeMultiInstanceTask == null) {
            errorMessage = "数据验证失败，指定加签任务不存在或已审批完毕！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        flowApiService.submitConsign(taskInstance, activeMultiInstanceTask, newAssignees);
        return ResponseResult.success();
    }

    /**
     * 返回当前用户待办的任务列表。
     *
     * @param processDefinitionKey  流程标识。
     * @param processDefinitionName 流程定义名 (模糊查询)。
     * @param taskName              任务名称 (魔术查询。
     * @param pageParam             分页对象。
     * @return 返回当前用户待办的任务列表。如果指定流程标识，则仅返回该流程的待办任务列表。
     */
    @PostMapping("/listRuntimeTask")
    public ResponseResult<PageInfo<FlowTaskVo>> listRuntimeTask(
            @MyRequestBody String processDefinitionKey,
            @MyRequestBody String processDefinitionName,
            @MyRequestBody String taskName,
            @MyRequestBody(required = true) MyPageParam pageParam,
            @MyRequestBody String projectId) {
//        String username = TokenData.takeFromRequest().getLoginName();
        return flowApiService.listRunTiemTask(processDefinitionKey, processDefinitionName, taskName, pageParam, projectId);
//        String username = LoginHelper.getUsername();
//        log.info("name=" + username);
//        PageInfo<Task> pageData = flowApiService.getTaskListByUserName(
//                username, processDefinitionKey, processDefinitionName, taskName, pageParam, projectId);
//        List<FlowTaskVo> flowTaskVoList = flowApiService.convertToFlowTaskList(pageData.getList());
//        PageInfo<FlowTaskVo> taskPageInfo = new PageInfo<>(flowTaskVoList);
//        taskPageInfo.setTotal(pageData.getTotal());
//        taskPageInfo.setPageNum(pageParam.getPageNum());
//        taskPageInfo.setPageSize(pageParam.getPageSize());
//        return ResponseResult.success(taskPageInfo);
    }



    /**
     * 返回当前用户待办的任务数量。
     *
     * @return 返回当前用户待办的任务数量。
     */
    @PostMapping("/countRuntimeTask")
    public ResponseResult<Long> countRuntimeTask() {
        String username = TokenData.takeFromRequest().getLoginName();
        long totalCount = flowApiService.getTaskCountByUserName(username);
        return ResponseResult.success(totalCount);
    }

    /**
     * 主动驳回当前的待办任务到开始节点，只用当前待办任务的指派人或者候选者才能完成该操作。
     *
     * @param processInstanceId 流程实例Id。
     * @param taskId            待办任务Id。
     * @param comment           驳回备注。
     * @return 操作应答结果。
     */
    @PostMapping("/rejectToStartUserTask")
    public ResponseResult<Void> rejectToStartUserTask(
            @MyRequestBody(required = true) String processInstanceId,
            @MyRequestBody(required = true) String taskId,
            @MyRequestBody(required = true) String comment) {
        String errorMessage;
        flowablePluginExecutor.executeRejectToStart(processInstanceId);
        ResponseResult<Task> taskResult =
                flowOperationHelper.verifySubmitAndGetTask(processInstanceId, taskId, null);
        if (!taskResult.isSuccess()) {
            return ResponseResult.errorFrom(taskResult);
        }
        FlowTaskComment firstTaskComment = flowTaskCommentService.getFirstFlowTaskComment(processInstanceId);
        CallResult result = flowApiService.backToRuntimeTask(
                taskResult.getData(), firstTaskComment.getTaskKey(), true, comment);
        if (!result.isSuccess()) {
            return ResponseResult.errorFrom(result);
        }
        return ResponseResult.success();
    }

    /**
     * 主动驳回当前的待办任务，只用当前待办任务的指派人或者候选者才能完成该操作。
     *
     * @param processInstanceId 流程实例Id。
     * @param taskId            待办任务Id。
     * @param comment           驳回备注。
     * @return 操作应答结果。
     */
    @PostMapping("/rejectRuntimeTask")
    public ResponseResult<Void> rejectRuntimeTask(
            @MyRequestBody(required = true) String processInstanceId,
            @MyRequestBody(required = true) String taskId,
            @MyRequestBody(required = true) String comment) {
        String errorMessage;
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

    /**
     * 撤回当前用户提交的，但是尚未被审批的待办任务。只有已办任务的指派人才能完成该操作。
     *
     * @param processInstanceId 流程实例Id。
     * @param taskId            待撤回的已办任务Id。
     * @param comment           撤回备注。
     * @return 操作应答结果。
     */
    @PostMapping("/revokeHistoricTask")
    public ResponseResult<Void> revokeHistoricTask(
            @MyRequestBody(required = true) String processInstanceId,
            @MyRequestBody(required = true) String taskId,
            @MyRequestBody(required = true) String comment) {
        String errorMessage;
        if (!flowApiService.existActiveProcessInstance(processInstanceId)) {
            errorMessage = "数据验证失败，当前流程实例已经结束，不能执行撤回！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        HistoricTaskInstance taskInstance = flowApiService.getHistoricTaskInstance(processInstanceId, taskId);
        if (taskInstance == null) {
            errorMessage = "数据验证失败，当前任务不存在！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        if (!StrUtil.equals(taskInstance.getAssignee(), TokenData.takeFromRequest().getLoginName())) {
            errorMessage = "数据验证失败，任务指派人与当前用户不匹配！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        FlowTaskComment taskComment = flowTaskCommentService.getLatestFlowTaskComment(processInstanceId);
        if (taskComment == null) {
            errorMessage = "数据验证失败，当前实例没有任何审批提交记录！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        if (!taskComment.getTaskId().equals(taskId)) {
            errorMessage = "数据验证失败，当前审批任务已被办理，不能撤回！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        List<Task> activeTaskList = flowApiService.getProcessInstanceActiveTaskList(processInstanceId);
        if (CollUtil.isEmpty(activeTaskList)) {
            errorMessage = "数据验证失败，当前流程没有任何待办任务！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        if (taskComment.getApprovalType().equals(FlowApprovalType.TRANSFER)) {
            if (activeTaskList.size() > 1) {
                errorMessage = "数据验证失败，转办任务数量不能多于1个！";
                return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
            }
            // 如果是转办任务，无需节点跳转，将指派人改为当前用户即可。
            Task task = activeTaskList.get(0);
            task.setAssignee(TokenData.takeFromRequest().getLoginName());
        } else {
            CallResult result =
                    flowApiService.backToRuntimeTask(activeTaskList.get(0), null, false, comment);
            if (!result.isSuccess()) {
                return ResponseResult.errorFrom(result);
            }
        }
        return ResponseResult.success();
    }

    /**
     * 获取当前流程任务的审批列表。
     *
     * @param processInstanceId 当前运行时的流程实例Id。
     * @return 当前流程实例的详情数据。
     */
    @GetMapping("/listFlowTaskComment")
    public ResponseResult<List<FlowTaskCommentVo>> listFlowTaskComment(@RequestParam String processInstanceId) {
        return flowApiService.listFlowTaskComment(processInstanceId);
    }


    /**
     * 获取指定流程定义的流程图。
     *
     * @param processDefinitionId 流程定义Id。
     * @return 流程图。
     */
    @GetMapping("/viewProcessBpmn")
    public ResponseResult<String> viewProcessBpmn(@RequestParam String processDefinitionId) throws IOException {
        BpmnXMLConverter converter = new BpmnXMLConverter();
        BpmnModel bpmnModel = flowApiService.getBpmnModelByDefinitionId(processDefinitionId);
        byte[] xmlBytes = converter.convertToXML(bpmnModel);
        InputStream in = new ByteArrayInputStream(xmlBytes);
        return ResponseResult.success(StreamUtils.copyToString(in, StandardCharsets.UTF_8));
    }

    /**
     * 获取流程图高亮数据。
     *
     * @param processInstanceId 流程实例Id。
     * @return 流程图高亮数据。
     */
    @GetMapping("/viewHighlightFlowData")
    public ResponseResult<JSONObject> viewHighlightFlowData(@RequestParam String processInstanceId) {
        HistoricProcessInstance hpi = flowApiService.getHistoricProcessInstance(processInstanceId);
        BpmnModel bpmnModel = flowApiService.getBpmnModelByDefinitionId(hpi.getProcessDefinitionId());
        List<Process> processList = bpmnModel.getProcesses();
        List<FlowElement> flowElementList = new LinkedList<>();
        processList.forEach(p -> flowElementList.addAll(p.getFlowElements()));
        Map<String, String> allSequenceFlowMap = new HashMap<>(16);
        for (FlowElement flowElement : flowElementList) {
            if (flowElement instanceof SequenceFlow) {
                SequenceFlow sequenceFlow = (SequenceFlow) flowElement;
                String ref = sequenceFlow.getSourceRef();
                String targetRef = sequenceFlow.getTargetRef();
                allSequenceFlowMap.put(ref + targetRef, sequenceFlow.getId());
            }
        }
        Set<String> finishedTaskSet = new LinkedHashSet<>();
        //获取流程实例的历史节点(全部执行过的节点，被拒绝的任务节点将会出现多次)
        List<HistoricActivityInstance> activityInstanceList =
                flowApiService.getHistoricActivityInstanceList(processInstanceId);
        List<String> activityInstanceTask = activityInstanceList.stream()
                .filter(s -> !StrUtil.equals(s.getActivityType(), "sequenceFlow"))
                .map(HistoricActivityInstance::getActivityId).collect(Collectors.toList());
        Set<String> finishedTaskSequenceSet = new LinkedHashSet<>();
        for (int i = 0; i < activityInstanceTask.size(); i++) {
            String current = activityInstanceTask.get(i);
            if (i != activityInstanceTask.size() - 1) {
                String next = activityInstanceTask.get(i + 1);
                finishedTaskSequenceSet.add(current + next);
            }
            finishedTaskSet.add(current);
        }
        Set<String> finishedSequenceFlowSet = new HashSet<>();
        finishedTaskSequenceSet.forEach(s -> finishedSequenceFlowSet.add(allSequenceFlowMap.get(s)));
        //获取流程实例当前正在待办的节点
        List<HistoricActivityInstance> unfinishedInstanceList =
                flowApiService.getHistoricUnfinishedInstanceList(processInstanceId);
        Set<String> unfinishedTaskSet = new LinkedHashSet<>();
        for (HistoricActivityInstance unfinishedActivity : unfinishedInstanceList) {
            unfinishedTaskSet.add(unfinishedActivity.getActivityId());
        }
        JSONObject jsonData = new JSONObject();
        jsonData.put("finishedTaskSet", finishedTaskSet);
        jsonData.put("finishedSequenceFlowSet", finishedSequenceFlowSet);
        jsonData.put("unfinishedTaskSet", unfinishedTaskSet);
        return ResponseResult.success(jsonData);
    }

    /**
     * 获取当前用户的已办理的审批任务列表。
     *
     * @param processDefinitionName 流程名。
     * @param beginDate             流程发起开始时间。
     * @param endDate               流程发起结束时间。
     * @param pageParam             分页对象。
     * @return 查询结果应答。
     */
    @PostMapping("/listHistoricTask")
    public ResponseResult<PageInfo<Map<String, Object>>> listHistoricTask(
            @MyRequestBody String processDefinitionName,
            @MyRequestBody String beginDate,
            @MyRequestBody String endDate,
            @MyRequestBody(required = true) MyPageParam pageParam,
            @MyRequestBody String projectId) throws ParseException {
        MyPageData<HistoricTaskInstance> pageData =
                flowApiService.getHistoricTaskInstanceFinishedList(processDefinitionName, beginDate, endDate, pageParam, projectId);
        List<Map<String, Object>> resultList = new LinkedList<>();
        pageData.getDataList().forEach(instance -> resultList.add(BeanUtil.beanToMap(instance)));
        List<HistoricTaskInstance> taskInstanceList = pageData.getDataList();
        if (CollUtil.isNotEmpty(taskInstanceList)) {
            Set<String> instanceIdSet = taskInstanceList.stream()
                    .map(HistoricTaskInstance::getProcessInstanceId).collect(Collectors.toSet());
            List<HistoricProcessInstance> instanceList = flowApiService.getHistoricProcessInstanceList(instanceIdSet);
            Map<String, HistoricProcessInstance> instanceMap =
                    instanceList.stream().collect(Collectors.toMap(HistoricProcessInstance::getId, c -> c));
            resultList.forEach(result -> {
                HistoricProcessInstance instance = instanceMap.get(result.get("processInstanceId").toString());
                result.put("processDefinitionKey", instance.getProcessDefinitionKey());
                result.put("processDefinitionName", instance.getProcessDefinitionName());
                result.put("startUser", instance.getStartUserId());
                result.put("businessKey", instance.getBusinessKey());
            });
            Set<String> taskIdSet =
                    taskInstanceList.stream().map(HistoricTaskInstance::getId).collect(Collectors.toSet());
            List<FlowTaskComment> commentList = flowTaskCommentService.getFlowTaskCommentListByTaskIds(taskIdSet);
            Map<String, List<FlowTaskComment>> commentMap =
                    commentList.stream().collect(Collectors.groupingBy(FlowTaskComment::getTaskId));
            resultList.forEach(result -> {
                List<FlowTaskComment> comments = commentMap.get(result.get("id").toString());
                if (CollUtil.isNotEmpty(comments)) {
                    result.put("approvalType", comments.get(0).getApprovalType());
                    comments.remove(0);
                }
            });
        }
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(resultList);
        mapPageInfo.setPageSize(pageParam.getPageSize());
        mapPageInfo.setPageNum(pageParam.getPageNum());
        mapPageInfo.setTotal(pageData.getTotalCount());
        return ResponseResult.success(mapPageInfo);
    }

    /**
     * 根据输入参数查询，当前用户的历史流程数据。
     *
     * @param processDefinitionName 流程名。
     * @param beginDate             流程发起开始时间。
     * @param endDate               流程发起结束时间。
     * @param pageParam             分页对象。
     * @return 查询结果应答。
     */
    @PostMapping("/listHistoricProcessInstance")
    public ResponseResult<PageInfo<Map<String, Object>>> listHistoricProcessInstance(
            @MyRequestBody String processDefinitionName,
            @MyRequestBody String beginDate,
            @MyRequestBody String endDate,
            @MyRequestBody(required = true) MyPageParam pageParam,
            @MyRequestBody String projectId) throws ParseException {
//        String loginName = TokenData.takeFromRequest().getLoginName();
        String loginName = LoginHelper.getUsername();
        MyPageData<HistoricProcessInstance> pageData = flowApiService.getHistoricProcessInstanceList(
                null, processDefinitionName, loginName, beginDate, endDate, pageParam, true, projectId);
        List<Map<String, Object>> resultList = new LinkedList<>();
        pageData.getDataList().forEach(instance -> resultList.add(BeanUtil.beanToMap(instance)));
        //分页
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(resultList);
        mapPageInfo.setPageSize(pageParam.getPageSize());
        mapPageInfo.setPageNum(pageParam.getPageNum());
        mapPageInfo.setTotal(pageData.getTotalCount());
        return ResponseResult.success(mapPageInfo);
    }

    /**
     * 根据输入参数查询，所有历史流程数据。
     *
     * @param processDefinitionName 流程名。
     * @param startUser             流程发起用户。
     * @param beginDate             流程发起开始时间。
     * @param endDate               流程发起结束时间。
     * @param pageParam             分页对象。
     * @return 查询结果。
     */
    @PostMapping("/listAllHistoricProcessInstance")
    public ResponseResult<MyPageData<Map<String, Object>>> listAllHistoricProcessInstance(
            @MyRequestBody String processDefinitionName,
            @MyRequestBody String startUser,
            @MyRequestBody String beginDate,
            @MyRequestBody String endDate,
            @MyRequestBody(required = true) MyPageParam pageParam,
            @MyRequestBody String projectId) throws ParseException {
        MyPageData<HistoricProcessInstance> pageData = flowApiService.getHistoricProcessInstanceList(
                null, processDefinitionName, startUser, beginDate, endDate, pageParam, false, projectId);
        List<Map<String, Object>> resultList = new LinkedList<>();
        pageData.getDataList().forEach(instance -> resultList.add(BeanUtil.beanToMap(instance)));
        return ResponseResult.success(MyPageUtil.makeResponseData(resultList, pageData.getTotalCount()));
    }

    /**
     * 催办工单，只有流程发起人才可以催办工单。
     * 催办场景必须要取消数据权限过滤，因为流程的指派很可能是跨越部门的。
     * 既然被指派和催办了，这里就应该禁用工单表的数据权限过滤约束。
     * 如果您的系统没有支持数据权限过滤，DisableDataFilter不会有任何影响，建议保留。
     *
     * @param workOrderId 工单Id。
     * @return 应答结果。
     */
    @DisableDataFilter
    @PostMapping("/remindRuntimeTask")
    public ResponseResult<Void> remindRuntimeTask(@MyRequestBody(required = true) Long workOrderId) {
        FlowWorkOrder flowWorkOrder = flowWorkOrderService.getById(workOrderId);
        if (flowWorkOrder == null) {
            return ResponseResult.error(ErrorCodeEnum.DATA_NOT_EXIST);
        }
        String errorMessage;
        if (!flowWorkOrder.getCreateUserId().equals(TokenData.takeFromRequest().getUserId())) {
            errorMessage = "数据验证失败，只有流程发起人才能催办工单!";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        if (flowWorkOrder.getFlowStatus().equals(FlowTaskStatus.FINISHED)) {
            errorMessage = "数据验证失败，已经结束的流程，不能催办工单！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        flowMessageService.saveNewRemindMessage(flowWorkOrder);
        return ResponseResult.success();
    }

    /**
     * 取消工作流工单，仅当没有进入任何审批流程之前，才可以取消工单。
     *
     * @param workOrderId  工单Id。
     * @param cancelReason 取消原因。
     * @return 应答结果。
     */
    @DisableDataFilter
    @PostMapping("/cancelWorkOrder")
    public ResponseResult<Void> cancelWorkOrder(
            @MyRequestBody(required = true) Long workOrderId,
            @MyRequestBody(required = true) String cancelReason) {
        FlowWorkOrder flowWorkOrder = flowWorkOrderService.getById(workOrderId);
        if (flowWorkOrder == null) {
            return ResponseResult.error(ErrorCodeEnum.DATA_NOT_EXIST);
        }
        String errorMessage;
        if (!flowWorkOrder.getFlowStatus().equals(FlowTaskStatus.SUBMITTED)) {
            errorMessage = "数据验证失败，当前流程已经进入审批状态，不能撤销工单！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        if (!flowWorkOrder.getCreateUserId().equals(TokenData.takeFromRequest().getUserId())) {
            errorMessage = "数据验证失败，当前用户不是工单所有者，不能撤销工单！";
            return ResponseResult.error(ErrorCodeEnum.DATA_VALIDATED_FAILED, errorMessage);
        }
        CallResult result = flowApiService.stopProcessInstance(
                flowWorkOrder.getProcessInstanceId(), cancelReason, true);
        if (!result.isSuccess()) {
            return ResponseResult.errorFrom(result);
        }
        return ResponseResult.success();
    }

    /**
     * 终止流程实例，将任务从当前节点直接流转到主流程的结束事件。
     *
     * @param processInstanceId 流程实例Id。
     * @param stopReason        停止原因。
     * @return 执行结果应答。
     */
    @DisableDataFilter
    @PostMapping("/stopProcessInstance")
    public ResponseResult<Void> stopProcessInstance(
            @MyRequestBody(required = true) String processInstanceId,
            @MyRequestBody(required = true) String stopReason) {
        // 先处理业务逻辑
        flowablePluginExecutor.executeStop(processInstanceId);
        CallResult result = flowApiService.stopProcessInstance(processInstanceId, stopReason, false);
        if (!result.isSuccess()) {
            return ResponseResult.errorFrom(result);
        }
        return ResponseResult.success();
    }

    /**
     * 删除流程实例。
     *
     * @param processInstanceId 流程实例Id。
     * @return 执行结果应答。
     */
    @PostMapping("/deleteProcessInstance")
    public ResponseResult<Void> deleteProcessInstance(@MyRequestBody(required = true) String processInstanceId) {
        flowApiService.deleteProcess(processInstanceId);
        return ResponseResult.success();
    }

    


    /**
     * 删除流程实例。
     *
     * @param processInstanceId 流程实例Id。
     * @return 执行结果应答。
     */
    @PostMapping("/handDeleteProcessInstance")
    public ResponseResult<Void> handDeleteProcessInstance(@MyRequestBody(required = true) String processInstanceId) {
        ActRuTask actRuTask = new ActRuTask();
        actRuTask.setProcessInstanceId(processInstanceId);
        actRuTaskService.updateActRuTask(actRuTask);
        ActRuVariable actRuVariable = new ActRuVariable();
        actRuVariable.setProcessInstanceId(processInstanceId);
        actRuVariableService.updateActRuVariable(actRuVariable);
        flowApiService.stopProcessInstance(processInstanceId, "手动删除",false);
        flowApiService.deleteProcessInstance(processInstanceId);
        return ResponseResult.success();
    }


    /**
     * 删除流程实例。
     *
     * @param processInstanceId 流程实例Id。
     * @return 执行结果应答。
     */
    @PostMapping("/handDeleteCopyProcessInstance")
    public ResponseResult<Void> handDeleteCopyProcessInstance(@MyRequestBody(required = true) String processInstanceId) {
        flowMessageService.removeByProcessInstanceId(processInstanceId);
        return ResponseResult.success();
    }

    /**
     * 删除流程实例。
     *
     * @param businessKey 业务数据id。
     * @return 执行结果应答。
     */
    @PostMapping("/deleteProcessInstanceByBusinessKey")
    public ResponseBase deleteProcessInstanceByBusinessKey(String businessKey) {
        return flowApiService.deleteProcessInstanceByBusinessKey(businessKey);
    }


    /**
     * 返回当前用户任务列表。
     *
     * @param processDefinitionKey  流程标识。
     * @param processDefinitionName 流程定义名 (模糊查询)。
     * @param taskName              任务名称 (魔术查询。
     * @param taskHandleStatus      1: 待办 2：在办  3：已办
     * @param pageParam
     * @return 返回当前用户待办-在办 的任务列表。如果指定流程标识，则仅返回该流程的待办任务列表。
     */
    @PostMapping("/listHandleTask")
    public ResponseBase listHandleTask(
            @MyRequestBody String processDefinitionKey,
            @MyRequestBody String processDefinitionName,
            @MyRequestBody String taskName,
            @MyRequestBody Integer taskHandleStatus,
            @MyRequestBody(required = true) MyPageParam pageParam) {
//        String username = TokenData.takeFromRequest().getLoginName();
        String username = LoginHelper.getUsername();
        // 重写了一个方法来获取在办和待办
        MyPageData<Task> pageData = flowTaskHandleService.getTaskListByUserName(
                username, processDefinitionKey, processDefinitionName, taskName, taskHandleStatus, pageParam);
        List<FlowTaskVo> flowTaskVoList = flowApiService.convertToFlowTaskList(pageData.getDataList());
        return ResponseBase.success(MyPageUtil.makeResponseData(flowTaskVoList, pageData.getTotalCount()));
    }


}
