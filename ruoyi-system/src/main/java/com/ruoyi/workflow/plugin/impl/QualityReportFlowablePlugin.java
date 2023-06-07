package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.flowable.service.FlowApiService;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.QualityReport;
import com.ruoyi.jianguan.business.quality.domain.vo.QualityReportDetailVo;
import com.ruoyi.jianguan.business.quality.service.QualityReportService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 计量台账工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("zhiliangjianbao")
public class QualityReportFlowablePlugin implements FlowablePlugin {

    @Autowired
    private QualityReportService qualityReportService;

    @Resource
    private FlowApiService  flowApiService;

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
        QualityReportDetailVo qualityReportDetailVo = qualityReportService.getInfoById(Long.parseLong(businessKey));
        log.info("QualityReportFlowablePlugin.rejectToStart.qualityReportDetailVo: {}", qualityReportDetailVo);
        if (Objects.nonNull(qualityReportDetailVo)) {
            qualityReportDetailVo.setStatus(status);
            QualityReport qualityReport = new QualityReport();
            BeanUtil.copyProperties(qualityReportDetailVo, qualityReport, false);
            qualityReport.setReportAttachment(null);
            qualityReport.setReplyPhotoAttachment(null);
            qualityReportService.updateById(qualityReport);
        }
    }
}
