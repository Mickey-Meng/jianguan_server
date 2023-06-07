package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.jianguan.business.contract.domain.entity.BuildContract;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.BuildContractDetailVo;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.contract.service.BuildContractService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 施工分包合同工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("shigongfenbaohetong")
public class BuildContractFlowablePlugin implements FlowablePlugin {

    @Autowired
    private BuildContractService buildContractService;

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
        BuildContractDetailVo buildContractDetailVo = buildContractService.getInfoById(Long.parseLong(businessKey));
        log.info("BuildContractFlowablePlugin.rejectToStart.buildContractDetailVo: {}", buildContractDetailVo);
        if (Objects.nonNull(buildContractDetailVo)) {
            buildContractDetailVo.setStatus(status);
            BuildContract buildContract = new BuildContract();
            BeanUtil.copyProperties(buildContractDetailVo, buildContract, false);
            //合同信息
            buildContract.setContractInfo(null);
            buildContract.setAttachment(null);
            buildContractService.updateById(buildContract);
        }
    }
}
