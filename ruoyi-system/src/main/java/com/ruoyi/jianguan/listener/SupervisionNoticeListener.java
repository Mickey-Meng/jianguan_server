package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionNotice;
import com.ruoyi.jianguan.business.quality.service.SupervisionNoticeService;
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
public class SupervisionNoticeListener implements ExecutionListener {

    @Autowired
    private SupervisionNoticeService supervisionNoticeService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (supervisionNoticeService == null) {
            supervisionNoticeService = ApplicationContextHolder.getBean(SupervisionNoticeService.class);
        }
        SupervisionNotice supervisionNotice = supervisionNoticeService.getById(businessKey);
        if (Objects.nonNull(supervisionNotice)) {
            supervisionNotice.setStatus(1);
            this.supervisionNoticeService.updateById(supervisionNotice);
        }
    }
}
