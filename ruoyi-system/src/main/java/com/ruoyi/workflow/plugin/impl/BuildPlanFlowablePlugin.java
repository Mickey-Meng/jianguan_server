package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.BuildPlan;
import com.ruoyi.jianguan.business.quality.domain.vo.BuildPlanDetailVo;
import com.ruoyi.jianguan.business.quality.service.BuildPlanService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 施工方案工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("shigongfangan")
public class BuildPlanFlowablePlugin implements FlowablePlugin {

    @Autowired
    private BuildPlanService buildPlanService;

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
        BuildPlanDetailVo buildPlanDetailVo = buildPlanService.getInfoById(Long.parseLong(businessKey));
        log.info("BuildPlanFlowablePlugin.buildPlanDetailVo: {}", buildPlanDetailVo);
        if (Objects.nonNull(buildPlanDetailVo)) {
            buildPlanDetailVo.setStatus(status);
            BuildPlan buildPlan = new BuildPlan();
            BeanUtil.copyProperties(buildPlanDetailVo, buildPlan, false);
            //专项施工方案
            buildPlan.setBuildPlanAttachment(null);
            //专家论证会议纪要
            buildPlan.setExpertMeetingAttachment(null);
            //整改回复上传
            buildPlan.setReplyAttachment(null);
            buildPlanService.updateById(buildPlan);
        }
    }
}
