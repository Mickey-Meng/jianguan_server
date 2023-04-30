package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.FirstAccept;
import com.ruoyi.jianguan.business.quality.service.FirstAcceptService;
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
public class FirstAcceptListener implements ExecutionListener {

    @Autowired
    private FirstAcceptService firstAcceptService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (firstAcceptService == null) {
            firstAcceptService = ApplicationContextHolder.getBean(FirstAcceptService.class);
        }
        FirstAccept firstAccept = firstAcceptService.getById(businessKey);
        if (Objects.nonNull(firstAccept)) {
            firstAccept.setStatus(1);
            this.firstAcceptService.updateById(firstAccept);
        }
    }
}
