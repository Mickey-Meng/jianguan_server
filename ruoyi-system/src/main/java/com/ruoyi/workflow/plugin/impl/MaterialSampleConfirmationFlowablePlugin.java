package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.MaterialBrandReport;
import com.ruoyi.jianguan.business.contract.domain.vo.MaterialBrandReportDetailVo;
import com.ruoyi.jianguan.business.contract.service.MaterialBrandReportService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j//
@Component("materialSampleConfirmation")
public class MaterialSampleConfirmationFlowablePlugin implements FlowablePlugin {


    @Autowired
    private MaterialBrandReportService materialBrandReportService;


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
     *
     * @param processInstance
     */
    private void updateStatus(ProcessInstance processInstance, Integer status) {
        String businessKey = processInstance.getBusinessKey();

        MaterialBrandReportDetailVo materialBrandReportDetailVo = materialBrandReportService.getInfoById(Long.parseLong(businessKey));
        if (Objects.nonNull(materialBrandReportDetailVo)) {
            materialBrandReportDetailVo.setStatus1(status);
            MaterialBrandReport materialBrandReport = new MaterialBrandReport();
            BeanUtil.copyProperties(materialBrandReportDetailVo, materialBrandReport, false);
            materialBrandReport.setSamplePhoto(null);
            materialBrandReport.setAttachment(null);
            materialBrandReport.setAttachment1(null);
            materialBrandReport.setAttachment2(null);
            materialBrandReportService.updateById(materialBrandReport);
        }
    }
}
