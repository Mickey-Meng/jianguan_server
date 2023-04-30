package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.contract.domain.entity.BuildContract;
import com.ruoyi.jianguan.business.contract.service.BuildContractService;
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
public class BuildContractListener implements ExecutionListener {

    @Autowired
    private BuildContractService buildContractService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (buildContractService == null) {
            buildContractService = ApplicationContextHolder.getBean(BuildContractService.class);
        }
        BuildContract buildContract = buildContractService.getById(businessKey);
        if (Objects.nonNull(buildContract)){
            buildContract.setStatus(1);
            this.buildContractService.updateById(buildContract);
        }
    }
}
