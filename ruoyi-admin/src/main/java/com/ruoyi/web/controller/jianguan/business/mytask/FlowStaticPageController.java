package com.ruoyi.web.controller.jianguan.business.mytask;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.DisableDataFilter;
import com.ruoyi.common.annotation.MyRequestBody;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.object.ResponseResult;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.domain.vo.FlowKeysVo;
import com.ruoyi.flowable.factory.FlowCustomExtFactory;
import com.ruoyi.flowable.model.FlowTaskComment;
import com.ruoyi.flowable.service.*;
import com.ruoyi.flowable.utils.FlowOperationHelper;
import com.ruoyi.workflow.plugin.FlowablePluginExecutor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: lpeng
 * @Date: 2022-04-03 09:15
 * @Description:
 */
@Api(tags = "通用流程操作接口-自开发页面")
@Slf4j
@RestController
@RequestMapping("${common-flow.urlPrefix}/flowStaticPage")
public class FlowStaticPageController {

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
    private FlowStaticPageService flowStaticPageService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;

    @Autowired
    private FlowablePluginExecutor flowablePluginExecutor;

    @DisableDataFilter
    @PostMapping("/startProcess/{processDefinitionKey}")
    @ApiOperation("提交流程任务，返回流程实例信息")
    public ResponseResult<ProcessInstance> startProcess(String processKey, String businessKey, Long userId, Map<String, Object> variables) throws Exception {
        return ResponseResult.success(flowStaticPageService.startProcess(processKey, businessKey, userId, variables));
    }

    @DisableDataFilter
    @PostMapping("/startProcessFlow/{processDefinitionKey}")
    @ApiOperation("提交流程任务，返回流程实例信息")
    public ResponseResult<String> startProcessFlow(@MyRequestBody String processKey, @MyRequestBody String businessKey, @MyRequestBody Long userId, @MyRequestBody Map<String, Object> variables) throws Exception {
        return ResponseResult.success(flowStaticPageService.startProcess(processKey, businessKey, userId, variables).getId());
    }

    /**
     * 发起任务并完成第一个节点
     *
     * @param processDefinitionKey
     * @param flowTaskCommentDto
     * @param taskVariableData
     * @param masterData
     * @param slaveData
     * @param copyData
     * @param businessKey
     * @return
     */
    @DisableDataFilter
    @PostMapping("/startAndTakeUserTask/{processDefinitionKey}")
    public ResponseResult<Void> startAndTakeUserTask(
            @PathVariable("processDefinitionKey") String processDefinitionKey,
            @MyRequestBody(required = true) FlowTaskCommentDto flowTaskCommentDto,
            @MyRequestBody JSONObject taskVariableData,
            @MyRequestBody(required = true) JSONObject masterData,
            @MyRequestBody JSONObject slaveData,
            @MyRequestBody JSONObject copyData,
            @MyRequestBody String businessKey) {
        return flowStaticPageService.startAndTakeUserTask(processDefinitionKey, flowTaskCommentDto, taskVariableData, masterData, slaveData, copyData, businessKey);
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
        return flowStaticPageService.rejectRuntimeTask(processInstanceId, taskId, comment);
    }

    /**
     *
     */
    @Deprecated
    @GetMapping(value = "/start/process")
    @ApiOperation(value = "测试-废弃")
    public void test(@RequestParam String processKey, @RequestParam String businessKey, @RequestParam Long userId) throws Exception {
        //获取流程实例
        //设置流程启动参数
        Map<String, Object> variables = new HashMap<>();
        ProcessInstance processInstance = flowStaticPageService.startAndTakeFirstByProcessKey(processKey, businessKey, new FlowTaskComment(), null);

    }


    /**
     * 提交流程的用户任务。
     * 该接口无需数据权限过滤，因此用DisableDataFilter注解标注。如果当前系统没有支持数据权限过滤，该注解不会有任何影响。
     *
     * @param processInstanceId  流程实例Id。
     * @param taskId             流程任务Id。
     * @param flowTaskCommentDto 流程审批数据。
     * @param taskVariableData   流程任务变量数据。
     * @param masterData         流程审批相关的主表数据。
     * @param slaveData          流程审批相关的多个从表数据。
     * @param copyData           传阅数据，格式为type和id，type的值参考FlowConstant中的常量值。
     * @return 应答结果对象。
     */
    @DisableDataFilter
    @PostMapping("/submitUserTask")
    @ApiOperation(value = "提交流程的用户任务")
    public ResponseResult<String> submitUserTask(
            @MyRequestBody(required = true) String processInstanceId,
            @MyRequestBody(required = true) String taskId,
            @MyRequestBody(required = true) FlowTaskCommentDto flowTaskCommentDto,
            @MyRequestBody JSONObject taskVariableData,
            @MyRequestBody JSONObject masterData,
            @MyRequestBody JSONObject slaveData,
            @MyRequestBody JSONObject copyData,
            @MyRequestBody String projectId) {
        ProcessInstance processInstance = flowApiService.getProcessInstance(processInstanceId);
        ResponseResult<String> stringResponseResult = flowStaticPageService.submitUserTask(processInstanceId, taskId, flowTaskCommentDto, taskVariableData, masterData,
                slaveData, copyData, projectId);
        flowablePluginExecutor.executeApply(processInstance);
        return stringResponseResult;
    }


    /**
     * 暂停任务
     *
     * @param processInstanceId
     * @return
     */
    @DisableDataFilter
    @PostMapping("/suspendUserTask")
    @ApiOperation(value = "暂停任务")
    public ResponseResult<Void> suspendUserTask(@MyRequestBody(required = true) String processInstanceId, @MyRequestBody(required = true) String taskId) {
        return flowStaticPageService.suspendUserTask(processInstanceId, taskId);
    }

    /**
     * 重新启动任务
     *
     * @param processInstanceId
     * @return
     */
    @DisableDataFilter
    @PostMapping("/activateTask")
    @ApiOperation(value = "重新启动任务")
    public ResponseResult<Void> activateUserTask(@MyRequestBody(required = true) String processInstanceId,
                                                 @MyRequestBody(required = true) String taskId) {
        return flowStaticPageService.activateUserTask(processInstanceId, taskId);
    }

    /**
     * 撤销任务
     *
     * @param processDefinitionKey
     * @param businessKey
     * @return
     */
    @DisableDataFilter
    @PostMapping("/withdrawUserTask")
    @ApiOperation(value = "撤销任务")
    public ResponseResult<Void> withdrawUserTask(@MyRequestBody(required = true) String processDefinitionKey,
                                                 @MyRequestBody(required = true) String businessKey,
                                                 @MyRequestBody(required = true) String reason) {

        return flowStaticPageService.withdrawUserTask(processDefinitionKey, businessKey, reason);
    }

    /**
     * 获取流程信息
     *
     * @param businessKey
     * @return
     */
    @ApiOperation("获取流程实例当前和前一个节点的已经审批的人员")
    @RequestMapping("/getFlowAndTaskInfo")
    public ResponseBase getFlowAndTaskInfo(String businessKey) {
        //返回
        FlowKeysVo vo = new FlowKeysVo();
        //先查询正在运行的
        List<Task> tasks = taskService.createTaskQuery().active().processInstanceBusinessKey(businessKey).list();
        if (Objects.nonNull(tasks) && !tasks.isEmpty()){
            Task task = tasks.get(0);
            if (Objects.nonNull(task)){
                //查询流程实例信息
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
                vo.setProcessDefinitionId(processInstance.getProcessDefinitionId());
                vo.setTaskId(task.getId());
                vo.setProcessInstanceId(processInstance.getId());
                return ResponseBase.success(vo);
            }
        }
        //否则查询历史，说明流程已经完成
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery()
            .processInstanceBusinessKey(businessKey).list();
        if (CollUtil.isNotEmpty(historicProcessInstances)){
//            #109 审批完成后，工作流审批记录显示不正确问题
            historicProcessInstances.sort(Comparator.comparing(HistoricProcessInstance::getEndTime).reversed());
            HistoricProcessInstance historicProcessInstance = historicProcessInstances.get(0);
            vo.setProcessDefinitionId(historicProcessInstance.getProcessDefinitionId());
            vo.setTaskId("");
            vo.setProcessInstanceId(historicProcessInstance.getId());
        }
        return ResponseBase.success(vo);
    }
}
