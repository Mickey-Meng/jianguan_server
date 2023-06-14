package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityDetection;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityDetectionDetailVo;
import com.ruoyi.jianguan.business.quality.service.QualityDetectionService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 质量检测工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("zhiliangjiance")
public class QualityDetectionFlowablePlugin implements FlowablePlugin {

    @Autowired
    private QualityDetectionService qualityDetectionService;

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
        QualityDetectionDetailVo qualityDetectionDetailVo = qualityDetectionService.getInfoById(Long.parseLong(businessKey));
        log.info("QualityDetectionFlowablePlugin.qualityDetectionDetailVo: {}", qualityDetectionDetailVo);
        if (Objects.nonNull(qualityDetectionDetailVo)) {
            qualityDetectionDetailVo.setStatus(status);
            QualityDetection qualityDetection = new QualityDetection();
            BeanUtil.copyProperties(qualityDetectionDetailVo, qualityDetection, false);

            //检测信息
            qualityDetection.setDetectionInfo(null);
            //检测报告
            qualityDetection.setDetectionReport(null);
            //出厂信息
            qualityDetection.setFactoryInfo(null);
            //其他附件
            qualityDetection.setOtherAttachment(null);

            qualityDetectionService.updateById(qualityDetection);
        }
    }
}
