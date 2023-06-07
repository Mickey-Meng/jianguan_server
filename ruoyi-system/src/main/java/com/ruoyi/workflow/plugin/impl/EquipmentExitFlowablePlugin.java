package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentExit;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentExitDetailVo;
import com.ruoyi.jianguan.business.contract.service.EquipmentEnterService;
import com.ruoyi.jianguan.business.contract.service.EquipmentExitService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 计量台账工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("shebeituichangbaoyan")
public class EquipmentExitFlowablePlugin implements FlowablePlugin {

    @Autowired
    private EquipmentExitService equipmentExitService;

    @Override
    public void approved(ProcessInstance processInstance) {
        updateStatus(processInstance, 1);
    }

    @Override
    public void apply(ProcessInstance processInstance) {

    }

    @Override
    public void rejectToStart(ProcessInstance processInstance) {
        updateStatus(processInstance, 2);
    }

    @Override
    public void stop(ProcessInstance processInstance) {
        updateStatus(processInstance, 2);
    }

    /**
     * 驳回
     * @param processInstance
     */
    private void updateStatus(ProcessInstance processInstance, Integer status) {
        String businessKey = processInstance.getBusinessKey();
        EquipmentExitDetailVo equipmentExitDetailVo = equipmentExitService.getInfoById(Long.parseLong(businessKey));
        log.info("EquipmentExitFlowablePlugin.rejectToStart.equipmentExitDetailVo: {}", equipmentExitDetailVo);
        if (Objects.nonNull(equipmentExitDetailVo)) {
            equipmentExitDetailVo.setStatus(status);
            EquipmentExit equipmentExit = new EquipmentExit();
            BeanUtil.copyProperties(equipmentExitDetailVo, equipmentExit, false);
            equipmentExit.setEquipmentInfo(null);
            equipmentExit.setAttachment(null);
            equipmentExitService.updateById(equipmentExit);
        }
    }
}
