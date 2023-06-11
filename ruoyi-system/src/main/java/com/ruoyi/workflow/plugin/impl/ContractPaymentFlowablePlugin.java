package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.ContractPayment;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentDetailVo;
import com.ruoyi.jianguan.business.contract.service.BuildContractService;
import com.ruoyi.jianguan.business.contract.service.ContractPaymentService;
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
@Component("contractPayment")
public class ContractPaymentFlowablePlugin implements FlowablePlugin {

    @Autowired
    private BuildContractService buildContractService;

    @Autowired
    private ContractPaymentService contractPaymentService;


    @Override
    public void approved(ProcessInstance processInstance) {
        updateStatus(processInstance, 1);
    }

    @Override
    public void apply(ProcessInstance processInstance) {

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

        ContractPaymentDetailVo contractPaymentDetailVo = contractPaymentService.getInfoById(Long.parseLong(businessKey));
        log.info("ContractPaymentFlowablePlugin.contractPaymentDetailVo: {}", contractPaymentDetailVo);
        if (Objects.nonNull(contractPaymentDetailVo)) {
            contractPaymentDetailVo.setStatus(status);
            ContractPayment contractPayment = new ContractPayment();
            BeanUtil.copyProperties(contractPaymentDetailVo, contractPayment, false);
            //合同信息
            contractPayment.setAttachment(null);
            contractPaymentService.updateById(contractPayment);
        }
    }
}
