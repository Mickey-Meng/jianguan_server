package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.quality.domain.entity.SubitemOpen;
import com.ruoyi.jianguan.business.quality.domain.vo.SubitemOpenDetailVo;
import com.ruoyi.jianguan.business.quality.service.SubitemOpenService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 分项开工申请工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("fenxiangkaigongshenqing")
public class SubitemOpenFlowablePlugin implements FlowablePlugin {

    @Autowired
    private SubitemOpenService subitemOpenService;

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
        SubitemOpenDetailVo subitemOpenDetailVo = subitemOpenService.getInfoById(Long.parseLong(businessKey));
        log.info("SubitemOpenFlowablePlugin.subitemOpenDetailVo: {}", subitemOpenDetailVo);
        if (Objects.nonNull(subitemOpenDetailVo)) {
            subitemOpenDetailVo.setStatus(status);
            SubitemOpen subitemOpen = new SubitemOpen();
            BeanUtil.copyProperties(subitemOpenDetailVo, subitemOpen, false);

            //标准试验审批表附件
            subitemOpen.setExperimentAttachment(null);
            //专项施工方案审批表附件
            subitemOpen.setBuildAttachment(null);
            //工艺试验审批表附件
            subitemOpen.setProcessAttachment(null);
            //到场材料审批表附件
            subitemOpen.setMaterialAttachment(null);
            //到场设备审批表附件
            subitemOpen.setEquipmentAttachment(null);
            //到场技术附件
            subitemOpen.setTechAttachment(null);
            //施工方案附件
            subitemOpen.setBuildPlanAttachment(null);
            //安全技术措施附件
            subitemOpen.setSecurityAttachment(null);
            //危险性较大项目附件
            subitemOpen.setRiskAttachment(null);
            //环境保护措施附件
            subitemOpen.setEnvironmentAttachment(null);
            //环保、安全、质量、技术交底材料附件
            subitemOpen.setBottomAttachment(null);

            subitemOpenService.updateById(subitemOpen);
        }
    }
}
