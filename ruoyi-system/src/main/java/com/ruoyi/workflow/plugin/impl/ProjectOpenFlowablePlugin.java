package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.ProjectOpen;
import com.ruoyi.jianguan.business.quality.domain.vo.ProjectOpenDetailVo;
import com.ruoyi.jianguan.business.quality.service.ProjectOpenService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 项目开通申请工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("xiangmukaigongshenqing")
public class ProjectOpenFlowablePlugin implements FlowablePlugin {

    @Autowired
    private ProjectOpenService projectOpenService;

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
        ProjectOpenDetailVo projectOpenDetailVo = projectOpenService.getInfoById(Long.parseLong(businessKey));
        log.info("ProjectOpenFlowablePlugin.projectOpenDetailVo: {}", projectOpenDetailVo);
        if (Objects.nonNull(projectOpenDetailVo)) {
            projectOpenDetailVo.setStatus(status);
            ProjectOpen projectOpen = new ProjectOpen();
            BeanUtil.copyProperties(projectOpenDetailVo, projectOpen, false);
            projectOpen.setAttachment(null);
            projectOpenService.updateById(projectOpen);
        }
    }
}
