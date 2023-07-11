package com.ruoyi.web.controller.jianguan.business.mytask;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.MyRequestBody;
import com.ruoyi.common.core.domain.object.MyPageData;
import com.ruoyi.common.core.domain.object.MyPageParam;
import com.ruoyi.common.core.domain.object.ResponseBase;
import com.ruoyi.common.core.domain.object.ResponseResult;
import com.ruoyi.flowable.domain.vo.FlowTaskCommentVo;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.flowable.service.FlowAuditEntryService;
import com.ruoyi.flowable.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程操作接口类
 *
 * @author Jerry
 * @date 2021-06-06
 */
@Api(tags = "流程操作接口")
@Slf4j
@RestController
@RequestMapping("flow/flowOperation")
public class FlowAuthOperationController {


    @Autowired
    private FlowOperationController flowOperationController;
    @Autowired
    private UserService userService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;

    @Autowired
    private FlowAuditEntryService flowAuditEntryService;

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
    public ResponseBase listRuntimeTask(
            @MyRequestBody String processDefinitionKey,
            @MyRequestBody String processDefinitionName,
            @MyRequestBody String taskName,
            @MyRequestBody(required = true) MyPageParam pageParam,
            @MyRequestBody String projectId) {
        ResponseResult<PageInfo<FlowTaskVo>> taskPageInfo = flowOperationController.listRuntimeTask(processDefinitionKey, processDefinitionName, taskName, pageParam, projectId);
        List<FlowTaskVo> flowTaskVos = taskPageInfo.getData().getList();
        if (Objects.nonNull(flowTaskVos) && !flowTaskVos.isEmpty()) {
            List<String> userNames = flowTaskVos.stream().map(FlowTaskVo::getProcessInstanceInitiator).collect(Collectors.toList());
            Map<String, String> names = userService.getNamesByUserName(userNames);
            if (Objects.nonNull(names) && !names.isEmpty()) {
                flowTaskVos.forEach(flowTaskVo -> flowTaskVo.
                        setProcessInstanceInitiatorName(names.getOrDefault(flowTaskVo.getProcessInstanceInitiator(), flowTaskVo.getProcessInstanceInitiator())));
            }
        }
        PageInfo<FlowTaskVo> taskPage = new PageInfo<>(flowTaskVos);
        taskPage.setTotal(taskPageInfo.getData().getTotal());
        taskPage.setPageNum(pageParam.getPageNum());
        taskPage.setPageSize(pageParam.getPageSize());
        return ResponseBase.success(taskPage);
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
    public ResponseBase listHistoricTask(
            @MyRequestBody String processDefinitionName,
            @MyRequestBody String beginDate,
            @MyRequestBody String endDate,
            @MyRequestBody(required = true) MyPageParam pageParam,
            @MyRequestBody String projectId) throws ParseException {
        ResponseResult<PageInfo<Map<String, Object>>> taskPageInfo = flowOperationController.listHistoricTask(processDefinitionName, beginDate, endDate, pageParam, projectId);
        List<Map<String, Object>> flowTaskVos = taskPageInfo.getData().getList();
        if (Objects.nonNull(flowTaskVos) && !flowTaskVos.isEmpty()) {
            List<String> userNames = new ArrayList<>();
            flowTaskVos.forEach(flowTaskVo -> userNames.add(String.valueOf(flowTaskVo.get("startUser"))));
            Map<String, String> names = userService.getNamesByUserName(userNames);
            if (Objects.nonNull(names) && !names.isEmpty()) {
                flowTaskVos.forEach(flowTaskVo -> flowTaskVo.
                        put("startUserName", names.getOrDefault(String.valueOf(flowTaskVo.get("startUser")), String.valueOf(flowTaskVo.get("startUser")))));
            }
        }
        PageInfo<Map<String, Object>> taskPage = new PageInfo<>(flowTaskVos);
        taskPage.setTotal(taskPageInfo.getData().getTotal());
        taskPage.setPageNum(pageParam.getPageNum());
        taskPage.setPageSize(pageParam.getPageSize());
        return ResponseBase.success(taskPage);
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
    public ResponseBase listHistoricProcessInstance(
            @MyRequestBody String processDefinitionName,
            @MyRequestBody String beginDate,
            @MyRequestBody String endDate,
            @MyRequestBody(required = true) MyPageParam pageParam,
            @MyRequestBody String projectId) throws ParseException {
        ResponseResult<PageInfo<Map<String, Object>>> taskPageInfo = flowOperationController.listHistoricProcessInstance(processDefinitionName, beginDate, endDate, pageParam, projectId);
        List<Map<String, Object>> flowTaskVos = taskPageInfo.getData().getList();
        if (Objects.nonNull(flowTaskVos) && !flowTaskVos.isEmpty()) {
            List<String> userNames = new ArrayList<>();
            flowTaskVos.forEach(flowTaskVo -> userNames.add(String.valueOf(flowTaskVo.get("startUserId"))));
            Map<String, String> names = userService.getNamesByUserName(userNames);
            if (Objects.nonNull(names) && !names.isEmpty()) {
                flowTaskVos.forEach(flowTaskVo -> flowTaskVo.
                        put("startUserName", names.getOrDefault(String.valueOf(flowTaskVo.get("startUserId")), String.valueOf(flowTaskVo.get("startUserId")))));
            }
        }
        PageInfo<Map<String, Object>> taskPage = new PageInfo<>(flowTaskVos);
        taskPage.setTotal(taskPageInfo.getData().getTotal());
        taskPage.setPageNum(pageParam.getPageNum());
        taskPage.setPageSize(pageParam.getPageSize());
        return ResponseBase.success(taskPage);
    }

    /**
     * 获取当前流程任务的审批列表。
     *
     * @param processInstanceId 当前运行时的流程实例Id。
     * @return 当前流程实例的详情数据。
     */
    @GetMapping("/listFlowTaskComment")
    public ResponseResult<List<FlowTaskCommentVo>> listFlowTaskComment(@RequestParam String processInstanceId) {
        ResponseResult<List<FlowTaskCommentVo>> listResponseResult = flowOperationController.listFlowTaskComment(processInstanceId);
        List<FlowTaskCommentVo> data = listResponseResult.getData();
        if (Objects.nonNull(data) && !data.isEmpty()) {
            List<String> userNames = new ArrayList<>();
            data.forEach(flowTaskVo -> userNames.add(flowTaskVo.getCreateLoginName()));
            Map<String, String> names = userService.getNamesByUserName(userNames);
            if (Objects.nonNull(names) && !names.isEmpty()) {
                data.forEach(flowTaskVo -> flowTaskVo.
                        setCreateName(names.getOrDefault(flowTaskVo.getCreateLoginName(), flowTaskVo.getCreateLoginName())));
            }
        }
        return ResponseResult.success(data);
    }


    /**
     * 获取当前流程任务的审批列表。
     *
     * @param processInstanceId 当前运行时的流程实例Id。
     * @return 当前流程实例的详情数据。
     */
    @GetMapping("/deleteFlowAndBusinessData")
    public ResponseBase deleteFlowAndBusinessData(@RequestParam String processInstanceId) {
        flowOperationController.stopProcessInstance(processInstanceId, "111");
        flowOperationController.deleteProcessInstance(processInstanceId);
        return ResponseBase.success(true);
    }

    /**
     * 批量删除流程实例。
     *
     * @param processInstanceIds 当前运行时的流程实例Id。
     * @return 当前流程实例的详情数据。
     */
    @PostMapping("/deleteFlowAndBusinessDataByList")
    public ResponseBase deleteFlowAndBusinessDataByList(@RequestBody List<String> processInstanceIds) {
        try {
            processInstanceIds.forEach(processInstanceId -> {
                flowOperationController.stopProcessInstance(processInstanceId, "111");
                flowOperationController.deleteProcessInstance(processInstanceId);
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseBase.success(true);
    }

    /**
     * 所有历史流程数据。
     *
     * @return 当前流程实例的详情数据。
     */
    @PostMapping("/listAllHistoricProcessInstance")
    public ResponseBase listAllHistoricProcessInstance(@MyRequestBody String processDefinitionName,
                                                       @MyRequestBody String startUser,
                                                       @MyRequestBody String beginDate,
                                                       @MyRequestBody String endDate,
                                                       @MyRequestBody(required = true) MyPageParam pageParam,
                                                       @MyRequestBody String projectId) throws ParseException {
        ResponseResult<MyPageData<Map<String, Object>>> myPageDataResponseResult = flowOperationController.
                listAllHistoricProcessInstance(processDefinitionName, startUser, beginDate, endDate, pageParam, projectId);
        List<String> instanceIds = Lists.newArrayList();
        List<Map<String, Object>> dataList = myPageDataResponseResult.getData().getDataList();
        if (Objects.nonNull(dataList)) {
            dataList.forEach(data -> {
                instanceIds.add(String.valueOf(data.get("processInstanceId")));
            });
        }
        return ResponseBase.success(instanceIds);
    }


    /**
     * 查看指定流程变量对象详情。
     *
     * @param processInstanceId 指定对象主键Id。
     * @return 应答结果对象，包含对象详情。
     */
    @GetMapping("/getRunVariables")
    public ResponseBase getRunVariables(String processInstanceId, String flowKey, Integer projectId, Integer buildSection) {
        //获取流程的人员变量
        List<String> userVariables = flowAuditEntryService.getUSerVariable(flowKey, projectId, buildSection);
        //获取运行的变量
        Map<String, Object> variables = new HashMap<>();
        try {
            variables = runtimeService.getVariables(processInstanceId);
        }catch (Exception e){
            //流程结束，历史变量
            List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(processInstanceId).list();
            if (Objects.nonNull(historicVariableInstances) && !historicVariableInstances.isEmpty()){
                for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
                    variables.put(historicVariableInstance.getVariableName(), historicVariableInstance.getValue());
                }
            }
            System.out.println(variables);
        }
        if (Objects.isNull(variables) || variables.isEmpty()) {
            return ResponseBase.success(variables);
        }
        //循环人员变量 获取实际处理人
        for (String userVariable : Objects.requireNonNull(userVariables)) {
            //运行实际人
            Object o = variables.get(userVariable);
            //判断对象类型
            if (o instanceof List) {
                List<String> userNames = (List<String>) o;
                //会签类型
                Map<String, String> names = userService.getNamesByUserName(userNames);
                List<String> name = new ArrayList<>();
                Objects.requireNonNull(names).forEach((k, v) -> {
                    name.add(v);
                });
                variables.put(userVariable + "Str", name);
            }
            if (o instanceof String) {
                List<String> username = new ArrayList<>();
                username.add((String) o);
                Map<String, String> names = userService.getNamesByUserName(username);
                List<String> name = new ArrayList<>();
                Objects.requireNonNull(names).forEach((k, v) -> {
                    name.add(v);
                });
                variables.put(userVariable + "Str", name);
            }
        }
        //流程初始化人
        Object initiator = variables.get("initiator");
        if (Objects.nonNull(initiator)){
            List<String> username = new ArrayList<>();
            username.add((String) initiator);
            Map<String, String> names = userService.getNamesByUserName(username);
            List<String> name = new ArrayList<>();
            Objects.requireNonNull(names).forEach((k, v) -> {
                name.add(v);
            });
            variables.put("initiatorStr", name);
        }
        return ResponseBase.success(variables);
    }

}
