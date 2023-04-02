package com.ruoyi.flowable.listener;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.flowable.common.constant.FlowTaskStatus;
import com.ruoyi.flowable.factory.FlowCustomExtFactory;
import com.ruoyi.flowable.model.FlowWorkOrder;
import com.ruoyi.flowable.service.FlowWorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

/**
 * 流程实例监听器，在流程实例结束的时候，需要完成一些自定义的业务行为。如：
 * 1. 更新流程工单表的审批状态字段。
 * 2. 业务数据同步。
 *
 * @author Jerry
 * @date 2021-06-06
 */
@Slf4j
public class FlowFinishedListener implements ExecutionListener {

    private final FlowWorkOrderService flowWorkOrderService =
            ApplicationContextHolder.getBean(FlowWorkOrderService.class);
    private final FlowCustomExtFactory flowCustomExtFactory =
            ApplicationContextHolder.getBean(FlowCustomExtFactory.class);

    @Override
    public void notify(DelegateExecution execution) {
        if (!StrUtil.equals("end", execution.getEventName())) {
            return;
        }
        String processInstanceId = execution.getProcessInstanceId();
        flowWorkOrderService.updateFlowStatusByProcessInstanceId(processInstanceId, FlowTaskStatus.FINISHED);
        String businessKey = execution.getProcessInstanceBusinessKey();
        FlowWorkOrder workOrder = flowWorkOrderService.getFlowWorkOrderByProcessInstanceId(processInstanceId);
        String rocessDefinitionKey = "";
        if (workOrder != null) {
            rocessDefinitionKey = workOrder.getProcessDefinitionKey();
        }
        flowCustomExtFactory.getBusinessDataExtHelper()
                .triggerSync(rocessDefinitionKey, processInstanceId, businessKey);
    }
}
