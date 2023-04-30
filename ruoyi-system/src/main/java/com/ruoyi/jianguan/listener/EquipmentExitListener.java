package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentExit;
import com.ruoyi.jianguan.business.contract.service.EquipmentExitService;
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
public class EquipmentExitListener implements ExecutionListener {

    @Autowired
    private EquipmentExitService equipmentExitService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (equipmentExitService == null) {
            equipmentExitService = ApplicationContextHolder.getBean(EquipmentExitService.class);
        }
        EquipmentExit equipmentExit = equipmentExitService.getById(businessKey);
        if (Objects.nonNull(equipmentExit)) {
            equipmentExit.setStatus(1);
            this.equipmentExitService.updateById(equipmentExit);
        }
    }
}
