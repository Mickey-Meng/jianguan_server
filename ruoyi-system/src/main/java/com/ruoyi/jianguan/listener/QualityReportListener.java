package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityReport;
import com.ruoyi.jianguan.business.quality.service.QualityReportService;
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
public class QualityReportListener implements ExecutionListener {

    @Autowired
    private QualityReportService qualityReportService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (qualityReportService == null) {
            qualityReportService = ApplicationContextHolder.getBean(QualityReportService.class);
        }
        QualityReport qualityReport = qualityReportService.getById(businessKey);
        if (Objects.nonNull(qualityReport)) {
            qualityReport.setStatus(1);
            this.qualityReportService.updateById(qualityReport);
        }
    }
}
