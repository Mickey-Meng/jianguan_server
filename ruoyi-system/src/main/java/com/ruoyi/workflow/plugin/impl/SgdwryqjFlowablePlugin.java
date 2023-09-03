package com.ruoyi.workflow.plugin.impl;

import com.ruoyi.common.core.domain.model.ZjPersonLeave;
import com.ruoyi.jianguan.business.contract.service.ContractPaymentService;
import com.ruoyi.jianguan.common.dao.ZjPersonLeaveDAO;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 施工分包合同工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("sgdwryqj")
public class SgdwryqjFlowablePlugin implements FlowablePlugin {

    @Autowired
    private ContractPaymentService contractPaymentService;

    @Autowired
    private ZjPersonLeaveDAO personLeaveDAO;


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
     * @param processInstance
     */
    private void updateStatus(ProcessInstance processInstance, Integer status) {
        String businessKey = processInstance.getBusinessKey();
        ZjPersonLeave personLeave = personLeaveDAO.getByBusinessKey(businessKey);
        log.info("SgdwryqjFlowablePlugin.personLeave: {}", personLeave);
        if (Objects.nonNull(personLeave)) {
            personLeave.setStatus(status);
            personLeaveDAO.updatePersonLeave(personLeave);
        }
    }
}

