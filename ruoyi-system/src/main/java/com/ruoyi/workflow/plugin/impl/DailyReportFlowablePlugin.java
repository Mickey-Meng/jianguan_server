package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.DailyReport;
import com.ruoyi.jianguan.business.contract.domain.vo.DailyReportDetailVo;
import com.ruoyi.jianguan.business.contract.service.DailyReportService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component("dailyReport")
public class DailyReportFlowablePlugin implements FlowablePlugin {


    @Autowired
    private DailyReportService dailyReportService;


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

        DailyReportDetailVo dailyReportDetailVo = dailyReportService.getInfoById(Long.parseLong(businessKey));
        log.info("ContractPaymentFlowablePlugin.contractPaymentDetailVo: {}", dailyReportDetailVo);
        if (Objects.nonNull(dailyReportDetailVo)) {
            dailyReportDetailVo.setStatus(status);
            DailyReport dailyReport = new DailyReport();
            BeanUtil.copyProperties(dailyReportDetailVo, dailyReport, false);
            //合同信息
            dailyReport.setAttachment(null);
            dailyReportService.updateById(dailyReport);
        }
    }
}
