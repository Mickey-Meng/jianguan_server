package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionSide;
import com.ruoyi.jianguan.business.quality.service.SupervisionSideService;
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
public class SupervisionSideListener implements ExecutionListener {

    @Autowired
    private SupervisionSideService supervisionSideService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (supervisionSideService == null) {
            supervisionSideService = ApplicationContextHolder.getBean(SupervisionSideService.class);
        }
        SupervisionSide supervisionSide = supervisionSideService.getById(businessKey);
        if (Objects.nonNull(supervisionSide)) {
            supervisionSide.setStatus(1);
            this.supervisionSideService.updateById(supervisionSide);
        }
    }
}
