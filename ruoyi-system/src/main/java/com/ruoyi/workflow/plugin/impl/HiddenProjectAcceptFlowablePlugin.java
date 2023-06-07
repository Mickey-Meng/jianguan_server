package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.HiddenProjectAccept;
import com.ruoyi.jianguan.business.quality.domain.vo.HiddenProjectAccepDetailtVo;
import com.ruoyi.jianguan.business.quality.service.HiddenProjectAcceptService;
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
@Component("yinbigongchengguanli")
public class HiddenProjectAcceptFlowablePlugin implements FlowablePlugin {

    @Autowired
    private HiddenProjectAcceptService hiddenProjectAcceptService;

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
        HiddenProjectAccepDetailtVo hiddenProjectAccepDetailtVo = hiddenProjectAcceptService.getInfoById(Long.parseLong(businessKey));
        log.info("HiddenProjectAcceptFlowablePlugin.rejectToStart.hiddenProjectAccepDetailtVo: {}", hiddenProjectAccepDetailtVo);
        if (Objects.nonNull(hiddenProjectAccepDetailtVo)) {
            hiddenProjectAccepDetailtVo.setStatus(status);
            HiddenProjectAccept hiddenProjectAccept = new HiddenProjectAccept();
            BeanUtil.copyProperties(hiddenProjectAccepDetailtVo, hiddenProjectAccept, false);
            hiddenProjectAccept.setAttachment(null);
            hiddenProjectAcceptService.updateById(hiddenProjectAccept);
        }
    }
}
