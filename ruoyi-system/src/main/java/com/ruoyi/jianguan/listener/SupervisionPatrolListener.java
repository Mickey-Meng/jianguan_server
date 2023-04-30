package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionPatrol;
import com.ruoyi.jianguan.business.quality.service.SupervisionPatrolService;
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
public class SupervisionPatrolListener implements ExecutionListener {

    @Autowired
    private SupervisionPatrolService supervisionPatrolService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (supervisionPatrolService == null) {
            supervisionPatrolService = ApplicationContextHolder.getBean(SupervisionPatrolService.class);
        }
        SupervisionPatrol supervisionPatrol = supervisionPatrolService.getById(businessKey);
        if (Objects.nonNull(supervisionPatrol)) {
            supervisionPatrol.setStatus(1);
            this.supervisionPatrolService.updateById(supervisionPatrol);
        }
    }
}
