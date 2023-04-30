package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.SubitemOpen;
import com.ruoyi.jianguan.business.quality.service.SubitemOpenService;
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
public class SubitemOpenListener implements ExecutionListener {

    @Autowired
    private SubitemOpenService subitemOpenService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (subitemOpenService == null) {
            subitemOpenService = ApplicationContextHolder.getBean(SubitemOpenService.class);
        }
        SubitemOpen subitemOpen = subitemOpenService.getById(businessKey);
        if (Objects.nonNull(subitemOpen)) {
            subitemOpen.setStatus(1);
            this.subitemOpenService.updateById(subitemOpen);
        }
    }
}
