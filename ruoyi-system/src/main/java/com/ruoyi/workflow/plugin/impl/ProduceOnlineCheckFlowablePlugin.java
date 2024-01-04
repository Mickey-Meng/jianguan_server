package com.ruoyi.workflow.plugin.impl;

import com.ruoyi.common.core.domain.entity.Produceandrecode;
import com.ruoyi.jianguan.business.onlineForms.service.IOnlineFormsService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("produceOnlineCheck")
public class ProduceOnlineCheckFlowablePlugin implements FlowablePlugin {


    @Autowired
    private IOnlineFormsService onlineFormsService;


    @Override
    public void approved(ProcessInstance processInstance) {
        updateStatus(processInstance, 1);
    }

    @Override
    public void apply(ProcessInstance processInstance) {
        updateStatus(processInstance, 0);
    }

    @Override
    public void rejectToStart(ProcessInstance processInstance) {
        updateStatus(processInstance, 2);
    }

    @Override
    public void stop(ProcessInstance processInstance) {
        updateStatus(processInstance, 2);
    }

    /**
     * 驳回
     *
     * @param processInstance
     */
    private void updateStatus(ProcessInstance processInstance, Integer status) {
        String businessKey = processInstance.getBusinessKey();
        Produceandrecode produceandrecode = onlineFormsService.updateFlowStatusById(businessKey, status);
        log.info("ProduceOnlineReportFlowablePlugin.produceandrecode: {}", produceandrecode);
    }
}
