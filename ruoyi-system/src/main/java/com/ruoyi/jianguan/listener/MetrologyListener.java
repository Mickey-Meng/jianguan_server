package com.ruoyi.jianguan.listener;

import com.ruoyi.common.constant.ApprovalStatusEnum;
import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.metrology.domain.entity.Metrology;
import com.ruoyi.jianguan.business.metrology.service.MetrologyService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author qiaoxulin
 * @date : 2022/6/15 15:05
 */
@Slf4j
@Component
public class MetrologyListener implements ExecutionListener {

    @Autowired
    private MetrologyService metrologyService;

    @Override
    public void notify(DelegateExecution delegateExecution) {
        log.info("MetrologyListener.notify.delegateExecution: {}", delegateExecution);
        if (metrologyService == null) {
            metrologyService = ApplicationContextHolder.getBean(MetrologyService.class);
        }
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        Metrology metrology = metrologyService.getById(businessKey);
        if (Objects.nonNull(metrology) && !ApprovalStatusEnum.REJECT.name().equals(metrology.getAuditStatus())){
            metrology.setAuditStatus(ApprovalStatusEnum.APPROVED.name());
            metrologyService.updateById(metrology);
        }
    }
}
