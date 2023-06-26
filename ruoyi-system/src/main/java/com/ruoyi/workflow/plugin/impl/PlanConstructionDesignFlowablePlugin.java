package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.constructionDesign.domain.entity.ConstructionDesign;
import com.ruoyi.jianguan.business.constructionDesign.domain.vo.PlanConstructionDesignVo;
import com.ruoyi.jianguan.business.constructionDesign.service.ConstructionDesignService;
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
@Component("planConstructionDesign")
public class PlanConstructionDesignFlowablePlugin implements FlowablePlugin {

    @Autowired
    private ConstructionDesignService constructionDesignService;


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
        PlanConstructionDesignVo planConstructionDesignVo = constructionDesignService.getPlanInfoById(Long.parseLong(businessKey));
        log.info("PlanConstructionDesignFlowablePlugin.planConstructionDesignVo: {}", planConstructionDesignVo);
        if (Objects.nonNull(planConstructionDesignVo)) {
            ConstructionDesign constructionDesign = new ConstructionDesign();
            BeanUtil.copyProperties(planConstructionDesignVo, constructionDesign, false);
            constructionDesign.setPlanStatus(0);
            //合同信息
            constructionDesign.setAttachment(null);
            constructionDesignService.updateById(constructionDesign);
        }
    }
}
