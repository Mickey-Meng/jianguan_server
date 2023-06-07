package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.EnterExit;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EnterExitDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.contract.service.EnterExitService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 进退场管理工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("jintuichangguanli")
public class EnterExitFlowablePlugin implements FlowablePlugin {

    @Autowired
    private EnterExitService enterExitService;

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
        EnterExitDetailVo equipmentEnterDetailVo = enterExitService.getInfoById(Long.parseLong(businessKey));
        log.info("EnterExitFlowablePlugin.equipmentEnterDetailVo: {}", equipmentEnterDetailVo);
        if (Objects.nonNull(equipmentEnterDetailVo)) {
            equipmentEnterDetailVo.setStatus(status);
            EnterExit enterExit = new EnterExit();
            BeanUtil.copyProperties(equipmentEnterDetailVo, enterExit, false);
            enterExitService.updateById(enterExit);
        }
    }
}
