package com.ruoyi.workflow.plugin.impl;

import com.ruoyi.common.core.domain.dto.PersonDTO;
import com.ruoyi.jianguan.business.contract.dao.PersonDAO;
import com.ruoyi.jianguan.business.contract.service.ContractPaymentService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 全咨单位合同人员报审
 * @author G.X.L
 */
@Slf4j
@Component("qzdwhtrybs")
public class ConsultingUnitFlowablePlugin implements FlowablePlugin {

    @Autowired
    private ContractPaymentService contractPaymentService;

    @Autowired
    private PersonDAO personDAO;


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
        PersonDTO personDTO = personDAO.getByBusinessKey(businessKey);
        log.info("PersonFlowablePlugin.personDTO: {}", personDTO);
        if (Objects.nonNull(personDTO)) {
            personDTO.setStatus(status);
            personDAO.updateContract(personDTO);
        }
    }
}
