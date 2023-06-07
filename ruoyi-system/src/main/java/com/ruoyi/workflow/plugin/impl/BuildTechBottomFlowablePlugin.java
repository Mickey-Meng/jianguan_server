package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.BuildTechBottom;
import com.ruoyi.jianguan.business.quality.domain.vo.BuildTechBottomDetailVo;
import com.ruoyi.jianguan.business.quality.service.BuildTechBottomService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 施工技术交底工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("shigongjishujiaodi")
public class BuildTechBottomFlowablePlugin implements FlowablePlugin {

    @Autowired
    private BuildTechBottomService buildTechBottomService;

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
        BuildTechBottomDetailVo buildTechBottomDetailVo = buildTechBottomService.getInfoById(Long.parseLong(businessKey));
        log.info("BuildTechBottomFlowablePlugin.buildTechBottomDetailVo: {}", buildTechBottomDetailVo);
        if (Objects.nonNull(buildTechBottomDetailVo)) {
            buildTechBottomDetailVo.setStatus(status);
            BuildTechBottom buildTechBottom = new BuildTechBottom();
            BeanUtil.copyProperties(buildTechBottomDetailVo, buildTechBottom, false);
            buildTechBottom.setAttachment(null);
            buildTechBottomService.updateById(buildTechBottom);
        }
    }
}
