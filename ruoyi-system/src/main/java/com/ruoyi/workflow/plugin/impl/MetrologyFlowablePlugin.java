package com.ruoyi.workflow.plugin.impl;

import com.ruoyi.common.constant.ApprovalStatusEnum;
import com.ruoyi.jianguan.business.metrology.domain.entity.Metrology;
import com.ruoyi.jianguan.business.metrology.service.MetrologyService;
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
@Component("jiliangshenpiv3")
public class MetrologyFlowablePlugin implements FlowablePlugin {



    @Autowired
    private MetrologyService metrologyService;

    @Override
    public void approved(ProcessInstance processInstance) {
        String businessKey = processInstance.getBusinessKey();
        Metrology metrology = metrologyService.getById(businessKey);
        if (Objects.nonNull(metrology)){
            metrology.setAuditStatus(ApprovalStatusEnum.APPROVED.name());
            metrologyService.updateById(metrology);
        }
    }

    @Override
    public void apply(ProcessInstance processInstance) {
        String businessKey = processInstance.getBusinessKey();
        Metrology metrology = metrologyService.getById(businessKey);
        if (Objects.nonNull(metrology)){
            metrology.setAuditStatus(ApprovalStatusEnum.APPROVING.name());
            metrologyService.updateById(metrology);
        }
    }

    @Override
    public void rejectToStart(ProcessInstance processInstance) {
        String businessKey = processInstance.getBusinessKey();
        Metrology metrology = metrologyService.getById(businessKey);
        log.info("MetrologyFlowablePlugin.rejectToStart.metrology: {}", metrology);
        if (Objects.nonNull(metrology)) {
            metrology.setAuditStatus(ApprovalStatusEnum.REJECT.name());
            metrologyService.updateById(metrology);
        }
    }

    @Override
    public void stop(ProcessInstance processInstance) {
        String businessKey = processInstance.getBusinessKey();
        Metrology metrology = metrologyService.getById(businessKey);
        log.info("MetrologyFlowablePlugin.rejectToStart.metrology: {}", metrology);
        if (Objects.nonNull(metrology)) {
            metrology.setAuditStatus(ApprovalStatusEnum.REJECT.name());
            metrologyService.updateById(metrology);
        }
    }
}
