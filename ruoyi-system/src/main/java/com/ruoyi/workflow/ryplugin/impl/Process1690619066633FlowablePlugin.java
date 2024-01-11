package com.ruoyi.workflow.ryplugin.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.ApprovalStatusEnum;
import com.ruoyi.jianguan.business.contract.service.EquipmentEnterService;
import com.ruoyi.project.ledgerChange.domain.bo.MeaLedgerChangeBo;
import com.ruoyi.project.ledgerChange.domain.vo.MeaLedgerChangeVo;
import com.ruoyi.project.ledgerChange.service.IMeaLedgerChangeService;
import com.ruoyi.project.measurementDocuments.domain.MeaMeasurementDocuments;
import com.ruoyi.project.measurementDocuments.domain.bo.MeaMeasurementDocumentsBo;
import com.ruoyi.project.measurementDocuments.domain.vo.MeaMeasurementDocumentsVo;
import com.ruoyi.project.measurementDocuments.service.IMeaMeasurementDocumentsService;
import com.ruoyi.workflow.ryplugin.RYFlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 计量台账工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("Process_1690619066633")
public class Process1690619066633FlowablePlugin implements RYFlowablePlugin {

    @Autowired
    private EquipmentEnterService equipmentEnterService;

    @Autowired
    private IMeaMeasurementDocumentsService iMeaMeasurementDocumentsService;


    @Override
    public void approved(ProcessInstance processInstance) {
        updateStatus(processInstance, ApprovalStatusEnum.APPROVED.name());
    }

    @Override
    public void apply(ProcessInstance processInstance) {
        updateStatus(processInstance, ApprovalStatusEnum.APPROVING.name());
    }

    @Override
    public void reject(ProcessInstance processInstance) {
        updateStatus(processInstance, ApprovalStatusEnum.REJECT.name());
    }

    @Override
    public void terminate(ProcessInstance processInstance) {
        delete(processInstance);
    }


    private void delete(ProcessInstance processInstance) {
        String businessKey = processInstance.getBusinessKey();
        MeaMeasurementDocumentsBo meaMeasurementDocumentsBo = new MeaMeasurementDocumentsBo();
        meaMeasurementDocumentsBo.setId(businessKey);
        List<MeaMeasurementDocumentsVo> meaMeasurementDocumentsVos = iMeaMeasurementDocumentsService.queryList(meaMeasurementDocumentsBo);
        if(CollUtil.isEmpty(meaMeasurementDocumentsVos)) {
            return;
        }
        String id = meaMeasurementDocumentsVos.get(0).getId();
        iMeaMeasurementDocumentsService.deleteWithValidByIds(CollUtil.newArrayList(id), false);
    }

    /**
     * 驳回
     * @param processInstance
     */
    private void updateStatus(ProcessInstance processInstance, String status) {
        String businessKey = processInstance.getBusinessKey();
        MeaMeasurementDocumentsBo meaMeasurementDocumentsBo = new MeaMeasurementDocumentsBo();
            meaMeasurementDocumentsBo.setId(businessKey);
        List<MeaMeasurementDocumentsVo> meaMeasurementDocumentsVos = iMeaMeasurementDocumentsService.queryList(meaMeasurementDocumentsBo);
        if(CollUtil.isEmpty(meaMeasurementDocumentsVos)) {
            return;
        }
        log.info("Process1690622335686FlowablePlugin.meaLedgerChangeVos: {}", JSON.toJSONString(meaMeasurementDocumentsVos));
        MeaMeasurementDocumentsVo meaMeasurementDocumentsVo = meaMeasurementDocumentsVos.get(0);
        if (Objects.nonNull(meaMeasurementDocumentsVo)) {
            meaMeasurementDocumentsVo.setReviewCode(status);
            MeaMeasurementDocumentsBo measurementDocumentsBo = new MeaMeasurementDocumentsBo();
            BeanUtil.copyProperties(meaMeasurementDocumentsVo, measurementDocumentsBo, false);
            iMeaMeasurementDocumentsService.updateByBo(measurementDocumentsBo);
        }
    }
}
