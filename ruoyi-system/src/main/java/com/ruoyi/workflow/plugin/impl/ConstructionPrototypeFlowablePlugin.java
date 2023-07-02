package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.ConstructionPrototype;
import com.ruoyi.jianguan.business.contract.domain.vo.ConstructionPrototypeDetailVo;
import com.ruoyi.jianguan.business.contract.service.ConstructionPrototypeService;
import com.ruoyi.jianguan.business.contract.service.ConstructionPrototypeService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component("constructionPrototype")
public class ConstructionPrototypeFlowablePlugin implements FlowablePlugin {


    @Autowired
    private ConstructionPrototypeService constructionPrototypeService;


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

        ConstructionPrototypeDetailVo constructionPrototypeDetailVo = constructionPrototypeService.getInfoById(Long.parseLong(businessKey));
        log.info("ContractPaymentFlowablePlugin.contractPaymentDetailVo: {}", constructionPrototypeDetailVo);
        if (Objects.nonNull(constructionPrototypeDetailVo)) {
            constructionPrototypeDetailVo.setStatus(status);
            ConstructionPrototype constructionPrototype = new ConstructionPrototype();
            BeanUtil.copyProperties(constructionPrototypeDetailVo, constructionPrototype, false);
            //合同信息
            constructionPrototype.setAttachment(null);
            constructionPrototypeService.updateById(constructionPrototype);
        }
    }
}
