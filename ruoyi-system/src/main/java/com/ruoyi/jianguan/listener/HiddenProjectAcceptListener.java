package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.HiddenProjectAccept;
import com.ruoyi.jianguan.business.quality.service.HiddenProjectAcceptService;
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
public class HiddenProjectAcceptListener implements ExecutionListener {

    @Autowired
    private HiddenProjectAcceptService hiddenProjectAcceptService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (hiddenProjectAcceptService == null) {
            hiddenProjectAcceptService = ApplicationContextHolder.getBean(HiddenProjectAcceptService.class);
        }
        HiddenProjectAccept hiddenProjectAccept = hiddenProjectAcceptService.getById(businessKey);
        if (Objects.nonNull(hiddenProjectAccept)) {
            hiddenProjectAccept.setStatus(1);
            this.hiddenProjectAcceptService.updateById(hiddenProjectAccept);
        }
    }
}
