package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.service.EquipmentEnterService;
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
public class EquipmentEnterListener implements ExecutionListener {

    @Autowired
    private EquipmentEnterService equipmentEnterService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (equipmentEnterService == null) {
            equipmentEnterService = ApplicationContextHolder.getBean(EquipmentEnterService.class);
        }
        EquipmentEnter equipmentEnter = equipmentEnterService.getById(businessKey);
        if (Objects.nonNull(equipmentEnter)) {
            equipmentEnter.setStatus(1);
            this.equipmentEnterService.updateById(equipmentEnter);
        }
    }
}
