package com.ruoyi.workflow.ryplugin.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.ApprovalStatusEnum;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.contract.service.EquipmentEnterService;
import com.ruoyi.project.ledgerChange.domain.bo.MeaLedgerChangeBo;
import com.ruoyi.project.ledgerChange.domain.vo.MeaLedgerChangeVo;
import com.ruoyi.project.ledgerChange.service.IMeaLedgerChangeService;
import com.ruoyi.workflow.ryplugin.RYFlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 计量台账工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("Process_1690622335686")
public class Process1690622335686FlowablePlugin implements RYFlowablePlugin {

    @Autowired
    private EquipmentEnterService equipmentEnterService;

    @Autowired
    private IMeaLedgerChangeService iMeaLedgerChangeService;

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
        MeaLedgerChangeBo meaLedgerChangeBo = new MeaLedgerChangeBo();
        meaLedgerChangeBo.setBgbh(businessKey);
        List<MeaLedgerChangeVo> meaLedgerChangeVos = iMeaLedgerChangeService.queryList(meaLedgerChangeBo);
        if(CollUtil.isEmpty(meaLedgerChangeVos)) {
            return;
        }
        String id = meaLedgerChangeVos.get(0).getId();
        iMeaLedgerChangeService.deleteWithValidByIds(CollUtil.newArrayList(id), false);
    }

    /**
     * 驳回
     * @param processInstance
     */
    private void updateStatus(ProcessInstance processInstance, String status) {
        String businessKey = processInstance.getBusinessKey();
        MeaLedgerChangeBo meaLedgerChangeBo = new MeaLedgerChangeBo();
        meaLedgerChangeBo.setBgbh(businessKey);
        List<MeaLedgerChangeVo> meaLedgerChangeVos = iMeaLedgerChangeService.queryList(meaLedgerChangeBo);
        if(CollUtil.isEmpty(meaLedgerChangeVos)) {
            return;
        }
        log.info("Process1690622335686FlowablePlugin.meaLedgerChangeVos: {}", JSON.toJSONString(meaLedgerChangeVos));
        MeaLedgerChangeVo meaLedgerChangeVo = meaLedgerChangeVos.get(0);
        if (Objects.nonNull(meaLedgerChangeVo)) {
            meaLedgerChangeVo.setStatus(status);
            MeaLedgerChangeBo meaLedgerChange = new MeaLedgerChangeBo();
            BeanUtil.copyProperties(meaLedgerChangeVo, meaLedgerChange, false);
            iMeaLedgerChangeService.updateByBo(meaLedgerChange);
        }
    }
}
