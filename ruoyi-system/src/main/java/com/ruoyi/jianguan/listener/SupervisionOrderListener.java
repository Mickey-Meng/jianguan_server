package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionOrder;
import com.ruoyi.jianguan.business.quality.service.SupervisionOrderService;
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
public class SupervisionOrderListener implements ExecutionListener {

    @Autowired
    private SupervisionOrderService supervisionOrderService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (supervisionOrderService == null) {
            supervisionOrderService = ApplicationContextHolder.getBean(SupervisionOrderService.class);
        }
        SupervisionOrder supervisionOrder = supervisionOrderService.getById(businessKey);
        if (Objects.nonNull(supervisionOrder)) {
            supervisionOrder.setStatus(1);
            this.supervisionOrderService.updateById(supervisionOrder);
        }
    }
}
