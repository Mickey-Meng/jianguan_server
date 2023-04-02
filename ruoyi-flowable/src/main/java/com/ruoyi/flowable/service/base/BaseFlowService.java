package com.ruoyi.flowable.service.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.object.MyRelationParam;
import com.ruoyi.common.core.service.BaseService;
import com.ruoyi.common.utils.MyDateUtil;
import com.ruoyi.flowable.common.constant.FlowApprovalType;
import com.ruoyi.flowable.common.constant.FlowTaskStatus;
import com.ruoyi.flowable.model.FlowTaskComment;
import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.flowable.service.FlowWorkOrderService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class BaseFlowService<M, K extends Serializable> extends BaseService<M, K> {

    @Autowired
    private FlowApiService flowApiService;
    @Autowired
    private FlowWorkOrderService flowWorkOrderService;

    public void startWithBusinessKey(String processDefinitionId, K dataId) {
        ProcessInstance instance = flowApiService.start(processDefinitionId, dataId);
        flowWorkOrderService.saveNew(instance, dataId, null, super.tableName);
    }

    public void startAndTakeFirst(
        String processDefinitionId, K dataId, FlowTaskComment comment, JSONObject variables) {
        ProcessInstance instance = flowApiService.startAndTakeFirst(
                processDefinitionId, dataId, comment, variables);
        flowWorkOrderService.saveNew(instance, dataId, null, super.tableName);
    }

    public void takeFirstTask(
            String processInstanceId, String taskId, K dataId, FlowTaskComment comment, JSONObject variables) {
        Task task = flowApiService.getProcessInstanceActiveTask(processInstanceId, taskId);
        flowApiService.setBusinessKeyForProcessInstance(processInstanceId, dataId);
        flowApiService.completeTask(task, comment, variables);
        ProcessInstance instance = flowApiService.getProcessInstance(processInstanceId);
        flowWorkOrderService.saveNew(instance, dataId, null, super.tableName);
    }

    public void takeTask(Task task, K dataId, FlowTaskComment comment, JSONObject variables) {
        int flowStatus = FlowTaskStatus.APPROVING;
        if (comment.getApprovalType().equals(FlowApprovalType.REFUSE)) {
            flowStatus = FlowTaskStatus.REFUSED;
        }
        flowWorkOrderService.updateFlowStatusByProcessInstanceId(task.getProcessInstanceId(), flowStatus);
        flowApiService.completeTask(task, comment, variables);
    }

    /**
     * 是否支持业务数据同步。每个子类需要根据实际情况判断是否需要支持。
     *
     * @return true支持，否则false。
     */
    public boolean supportSyncBusinessData() {
        return false;
    }

    /**
     * 在流程实例审批结束后，需要进行审批表到发布表数据同步的服务实现子类，需要实现该方法。
     *
     * @param processInstanceId 流程实例Id。
     * @param businessKey       业务主键Id。如果与实际主键值类型不同，需要在子类中自行完成类型转换。
     */
    public void syncBusinessData(String processInstanceId, String businessKey) {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取业务详情数据。
     *
     * @param processInstanceId 流程实例Id。
     * @param businessKey       业务主键Id。如果与实际主键值类型不同，需要在子类中自行完成类型转换。
     * @return 业务主表数据，以及关联从表数据。
     */
    @SuppressWarnings("unchecked")
    public String getBusinessData(String processInstanceId, String businessKey) {
        M data;
        if (idFieldClass.equals(Long.class)) {
            Long dataId = Long.valueOf(businessKey);
            data = this.getByIdWithRelation((K) dataId, MyRelationParam.full());
        } else if (idFieldClass.equals(Integer.class)) {
            Integer dataId = Integer.valueOf(businessKey);
            data = this.getByIdWithRelation((K) dataId, MyRelationParam.full());
        } else {
            data = this.getByIdWithRelation((K) businessKey, MyRelationParam.full());
        }
        return JSON.toJSONStringWithDateFormat(data, MyDateUtil.COMMON_SHORT_DATETIME_FORMAT);
    }
}
