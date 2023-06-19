package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.ContractPayment;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentDetailVo;
import com.ruoyi.jianguan.business.contract.service.ContractPaymentService;
import com.ruoyi.jianguan.common.domain.entity.ZjFile;
import com.ruoyi.jianguan.common.service.FileService;
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
@Component("DSFJCDWZLGL")
public class DSFJCDWZLGLFlowablePlugin implements FlowablePlugin {

    @Autowired
    private FileService fileService;


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
        ZjFile zjFile = fileService.selectByPrimaryKey(Integer.parseInt(businessKey));
        log.info("DSFJCDWZLGLFlowablePlugin.contractPaymentDetailVo: {}", zjFile);
        if (Objects.nonNull(zjFile)) {
            zjFile.setStatus(status);
            //合同信息
            zjFile.setFileurl(null);
            fileService.updateByPrimaryKeySelective(zjFile);
        }
    }
}
