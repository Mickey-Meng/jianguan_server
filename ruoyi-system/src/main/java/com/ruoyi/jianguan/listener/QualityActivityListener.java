package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityActivity;
import com.ruoyi.jianguan.business.quality.service.QualityActivityService;
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
public class QualityActivityListener implements ExecutionListener {

    @Autowired
    private QualityActivityService qualityActivityService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (qualityActivityService == null) {
            qualityActivityService = ApplicationContextHolder.getBean(QualityActivityService.class);
        }
        QualityActivity qualityActivity = qualityActivityService.getById(businessKey);
        if (Objects.nonNull(qualityActivity)) {
            qualityActivity.setStatus(1);
            this.qualityActivityService.updateById(qualityActivity);
        }
    }
}
