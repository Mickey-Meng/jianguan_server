package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.contract.service.EquipmentEnterService;
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
@Component("shebeijinchangbaoyan")
public class EquipmentEnterFlowablePlugin implements FlowablePlugin {

    @Autowired
    private EquipmentEnterService equipmentEnterService;

    @Override
    public void approved(ProcessInstance processInstance) {
        updateStatus(processInstance, 1);
    }

    @Override
    public void apply(ProcessInstance processInstance) {
        updateStatus(processInstance, 0);
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
        EquipmentEnterDetailVo equipmentEnterDetailVo = equipmentEnterService.getInfoById(Long.parseLong(businessKey));
        log.info("EquipmentEnterFlowablePlugin.rejectToStart.equipmentEnterDetailVo: {}", equipmentEnterDetailVo);
        if (Objects.nonNull(equipmentEnterDetailVo)) {
            equipmentEnterDetailVo.setStatus(status);
            EquipmentEnter equipmentEnter = new EquipmentEnter();
            BeanUtil.copyProperties(equipmentEnterDetailVo, equipmentEnter, false);
            equipmentEnter.setEquipmentInfo(null);
            equipmentEnter.setAttachment(null);
            equipmentEnterService.updateById(equipmentEnter);
        }
    }
}
