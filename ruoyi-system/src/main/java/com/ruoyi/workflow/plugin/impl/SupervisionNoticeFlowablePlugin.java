package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionNotice;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionNoticeDetailVo;
import com.ruoyi.jianguan.business.quality.service.SupervisionNoticeService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 监理通知工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("jianlitongzhi")
public class SupervisionNoticeFlowablePlugin implements FlowablePlugin {

    @Autowired
    private SupervisionNoticeService supervisionNoticeService;

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
        SupervisionNoticeDetailVo supervisionNoticeDetailVo = supervisionNoticeService.getInfoById(Long.parseLong(businessKey));
        log.info("SupervisionNoticeFlowablePlugin.rejectToStart.supervisionNoticeDetailVo: {}", supervisionNoticeDetailVo);
        if (Objects.nonNull(supervisionNoticeDetailVo)) {
            supervisionNoticeDetailVo.setStatus(status);
            SupervisionNotice supervisionNotice = new SupervisionNotice();
            BeanUtil.copyProperties(supervisionNoticeDetailVo, supervisionNotice, false);
            supervisionNoticeService.updateById(supervisionNotice);
        }
    }
}
