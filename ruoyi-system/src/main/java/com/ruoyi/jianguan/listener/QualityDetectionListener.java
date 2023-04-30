package com.ruoyi.jianguan.listener;


import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityDetection;
import com.ruoyi.jianguan.business.quality.service.QualityDetectionService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author qiaoxulin
 * @date : 2022/6/15 15:05
 */
@Component
public class QualityDetectionListener implements ExecutionListener {

    @Autowired
    private QualityDetectionService qualityDetectionService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (qualityDetectionService == null) {
            qualityDetectionService = ApplicationContextHolder.getBean(QualityDetectionService.class);
        }
        QualityDetection qualityDetection = qualityDetectionService.getById(businessKey);
        if (Objects.nonNull(qualityDetection)) {
            qualityDetection.setStatus(1);
            this.qualityDetectionService.updateById(qualityDetection);
        }
    }
}
