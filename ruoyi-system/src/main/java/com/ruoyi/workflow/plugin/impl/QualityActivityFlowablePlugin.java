package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityActivity;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityActivityDetailVo;
import com.ruoyi.jianguan.business.quality.service.QualityActivityService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 质量活动工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("zhilianghuodong")
public class QualityActivityFlowablePlugin implements FlowablePlugin {

    @Autowired
    private QualityActivityService qualityActivityService;

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
        QualityActivityDetailVo qualityActivityDetailVo = qualityActivityService.getInfoById(Long.parseLong(businessKey));
        log.info("QualityActivityFlowablePlugin.qualityActivityDetailVo: {}", qualityActivityDetailVo);
        if (Objects.nonNull(qualityActivityDetailVo)) {
            qualityActivityDetailVo.setStatus(status);
            QualityActivity qualityActivity = new QualityActivity();
            BeanUtil.copyProperties(qualityActivityDetailVo, qualityActivity, false);
            qualityActivity.setAttachment(null);
            qualityActivityService.updateById(qualityActivity);
        }
    }
}
