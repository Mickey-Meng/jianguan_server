package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.contract.domain.entity.LaborContract;
import com.ruoyi.jianguan.business.contract.service.LaborContractService;
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
public class LaborContractListener implements ExecutionListener {

    @Autowired
    private LaborContractService laborContractService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (laborContractService == null) {
            laborContractService = ApplicationContextHolder.getBean(LaborContractService.class);
        }
        LaborContract laborContract = laborContractService.getById(businessKey);
        if (Objects.nonNull(laborContract)) {
            laborContract.setStatus(1);
            this.laborContractService.updateById(laborContract);
        }
    }
}
