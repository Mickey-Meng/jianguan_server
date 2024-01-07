package com.ruoyi.workflow.front.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.project.approval.domain.bo.MeaLedgerApprovalNoBo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalNoVo;
import com.ruoyi.project.approval.service.IMeaLedgerApprovalNoService;
import com.ruoyi.workflow.constant.ProcessKeyConstant;
import com.ruoyi.workflow.front.plugin.FrontFlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 施工分包合同工作流插件
 * @author G.X.L
 */
@Slf4j
@Component(ProcessKeyConstant.PROCESS_1690014418614)
public class MeaLedgerApprovalPlugin implements FrontFlowablePlugin {

    @Autowired
    private IMeaLedgerApprovalNoService iMeaLedgerApprovalNoService;

    @Override
    public void approved(ProcessInstance processInstance) {
    }

    @Override
    public void apply(ProcessInstance processInstance) {
    }

    @Override
    public void rejectToStart(ProcessInstance processInstance) {
    }

    @Override
    public void stop(ProcessInstance processInstance) {
        updateStatus(processInstance, "0");
    }

    /**
     * 驳回
     * @param processInstance
     */
    private void updateStatus(ProcessInstance processInstance, String status) {
        String businessKey = processInstance.getBusinessKey();
        MeaLedgerApprovalNoBo meaLedgerApprovalNoBo = new MeaLedgerApprovalNoBo();
        meaLedgerApprovalNoBo.setSqqc(businessKey);
        List<MeaLedgerApprovalNoVo> meaLedgerApprovalNoVos = iMeaLedgerApprovalNoService.queryList(meaLedgerApprovalNoBo);
        MeaLedgerApprovalNoVo meaLedgerApprovalNoVo = meaLedgerApprovalNoVos.get(0);
        meaLedgerApprovalNoVo.setReviewCode(String.valueOf(status));
        iMeaLedgerApprovalNoService.updateByBo(BeanUtil.copyProperties(meaLedgerApprovalNoVo, MeaLedgerApprovalNoBo.class));
    }
}
