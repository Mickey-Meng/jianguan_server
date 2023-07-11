package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPlan;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPlanDetailVo;
import com.ruoyi.jianguan.business.contract.service.ConstructionPlanService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component("constructionPlanReport")
public class ConstructionPlanReportFlowablePlugin implements FlowablePlugin {


    @Autowired
    private ConstructionPlanService constructionPlanService;


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

        ConstructionPlanDetailVo constructionPlanDetailVo = constructionPlanService.getInfoById(Long.parseLong(businessKey));
        log.info("ContractPaymentFlowablePlugin.contractPaymentDetailVo: {}", constructionPlanDetailVo);
        if (Objects.nonNull(constructionPlanDetailVo)) {
            constructionPlanDetailVo.setReportStatus(status);
            ConstructionPlan constructionPlan = new ConstructionPlan();
            BeanUtil.copyProperties(constructionPlanDetailVo, constructionPlan, false);
            constructionPlan.setAttachment(null);
            constructionPlanService.updateById(constructionPlan);
        }
    }
}
