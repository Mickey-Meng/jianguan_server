package com.ruoyi.flowable.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.object.ResponseResult;
import com.ruoyi.flowable.domain.dto.FlowTaskCommentDto;
import com.ruoyi.flowable.model.FlowTaskComment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;

import java.util.Map;

/**
 * @Author: lpeng
 * @Date: 2022-04-03 09:41
 * @Description:
 */
public interface FlowStaticPageService {


    /**
     * 提交用户Task
     * @param processInstanceId
     * @param taskId
     * @param flowTaskCommentDto
     * @param taskVariableData
     * @param masterData
     * @param slaveData
     * @param copyData
     * @return
     */
    ResponseResult<String> submitUserTask(String processInstanceId, String taskId, FlowTaskCommentDto flowTaskCommentDto, JSONObject taskVariableData, JSONObject masterData, JSONObject slaveData, JSONObject copyData);

    /**
     * 主动驳回
     * @param processInstanceId
     * @param taskId
     * @param comment
     * @return
     */
    ResponseResult<Void> rejectRuntimeTask(String processInstanceId, String taskId, String comment);


    /**
     * 发起任务并完成第一个节点
     * @param processDefinitionKey
     * @param flowTaskCommentDto
     * @param taskVariableData
     * @param masterData
     * @param slaveData
     * @param copyData
     * @param businessKey
     * @return
     */
    ResponseResult<Void> startAndTakeUserTask(String processDefinitionKey, FlowTaskCommentDto flowTaskCommentDto, JSONObject taskVariableData,
                                              JSONObject masterData, JSONObject slaveData, JSONObject copyData, String businessKey);


    /**
     * 暂停任务
     * @param processInstanceId
     * @return
     */
    ResponseResult<Void> suspendUserTask(String processInstanceId,String taskId);

    /**
     * 重新启动任务
     * @param processInstanceId
     * @return
     */
    ResponseResult<Void> activateUserTask(String processInstanceId,String taskId);


    /**
     * 撤回任务
     * @param processDefinitionKey
     * @param businessKey
     * @return
     */
    ResponseResult<Void> withdrawUserTask(String processDefinitionKey,String businessKey,String reason);


    //------------------以下属于内部调用------------------

    /**
     * 启动流程，生成一个新的流程实例
     * @param processKey  流程key
     * @param businessKey
     */
    ProcessInstance startProcess(String processKey, String businessKey, Long userId, Map<String, Object> variables) throws Exception;

    /**
     * 启动流程实例，如果当前登录用户为第一个用户任务的指派者，或者Assginee为流程启动人变量时，
     * 则自动完成第一个用户任务。
     *
     * @param processDefinitionId 流程定义Id。
     * @param dataId              当前流程主表的主键数据。
     * @param flowTaskComment     审批对象。
     * @param taskVariableData    流程任务的变量数据。
     * @return 新启动的流程实例。
     */
    ProcessInstance startAndTakeFirst(String processDefinitionId, Object dataId, FlowTaskComment flowTaskComment, JSONObject taskVariableData);

    /**
     *
     * @param processKey
     * @param businessKey
     * @param flowTaskComment
     * @param taskVariableData
     * @return
     */
    ProcessInstance startAndTakeFirstByProcessKey(String processKey, String businessKey , FlowTaskComment flowTaskComment, JSONObject taskVariableData);
    /**
     * 多实例加签。
     *
     * @param startTaskInstance       会签对象的发起任务实例。
     * @param multiInstanceActiveTask 正在执行的多实例任务对象。
     * @param newAssignees            新指派人，多个指派人之间逗号分隔。
     */
    void submitConsign(HistoricTaskInstance startTaskInstance, Task multiInstanceActiveTask, String newAssignees);

    /**
     * 完成任务，同时提交审批数据。
     *
     * @param task             工作流任务对象。
     * @param flowTaskComment  审批对象。
     * @param taskVariableData 流程任务的变量数据。
     */
    void completeTask(Task task, FlowTaskComment flowTaskComment, JSONObject taskVariableData);


    /**
     *
     * @param processInstanceId
     * @param taskId
     * @param businessId
     * @param flowTaskComment
     * @param taskVariableData
     */
    void saveNewAndTakeTask(String processInstanceId,String taskId,String businessId,FlowTaskComment flowTaskComment, JSONObject taskVariableData );
    /**
     * 初始化并返回流程实例的变量Map。
     * @param processDefinitionId 流程定义Id。
     * @return 初始化后的流程实例变量Map。
     */
    Map<String, Object> initAndGetProcessInstanceVariables(String processDefinitionId);


    /**
     * 获取Task
     * @param taskId
     * @return
     */
    Task getTaskById(String taskId);

}
