package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentExit;
import com.ruoyi.jianguan.business.contract.domain.entity.LaborContract;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentExitDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.LaborContractDetailVo;
import com.ruoyi.jianguan.business.contract.service.LaborContractService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 计量台账工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("laowufenbaohetong")
public class LaborContractFlowablePlugin implements FlowablePlugin {

    @Autowired
    private LaborContractService laborContractService;

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
        LaborContractDetailVo laborContractDetailVo = laborContractService.getInfoById(Long.parseLong(businessKey));
        log.info("LaborContractFlowablePlugin.rejectToStart.laborContractDetailVo: {}", laborContractDetailVo);
        if (Objects.nonNull(laborContractDetailVo)) {
            laborContractDetailVo.setStatus(status);
            LaborContract laborContract = new LaborContract();
            BeanUtil.copyProperties(laborContractDetailVo, laborContract, false);
            laborContract.setInformation(null);
            laborContract.setAttachment(null);
            laborContractService.updateById(laborContract);
        }
    }
}
