package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.contract.domain.entity.ComeGoMoney;
import com.ruoyi.jianguan.business.contract.service.ComeGoMoneyService;
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
public class ComeGoMoneyListener implements ExecutionListener {

    @Autowired
    private ComeGoMoneyService comeGoMoneyService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (comeGoMoneyService == null) {
            comeGoMoneyService = ApplicationContextHolder.getBean(ComeGoMoneyService.class);
        }
        ComeGoMoney comeGoMoney = comeGoMoneyService.getById(businessKey);
        if (Objects.nonNull(comeGoMoney)){
            comeGoMoney.setStatus(1);
            this.comeGoMoneyService.updateById(comeGoMoney);
        }
    }
}
