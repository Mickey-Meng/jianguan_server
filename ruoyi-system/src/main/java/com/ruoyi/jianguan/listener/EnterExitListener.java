package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.contract.domain.entity.EnterExit;
import com.ruoyi.jianguan.business.contract.service.EnterExitService;
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
public class EnterExitListener implements ExecutionListener {

    @Autowired
    private EnterExitService enterExitService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (enterExitService == null) {
            enterExitService = ApplicationContextHolder.getBean(EnterExitService.class);
        }
        EnterExit enterExit = enterExitService.getById(businessKey);
        if (Objects.nonNull(enterExit)) {
            enterExit.setStatus(1);
            this.enterExitService.updateById(enterExit);
        }
    }
}
