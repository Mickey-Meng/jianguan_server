package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.FirstAccept;
import com.ruoyi.jianguan.business.quality.domain.vo.FirstAcceptDetailVo;
import com.ruoyi.jianguan.business.quality.service.FirstAcceptService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 首件认可工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("shoujianrenke")
public class FirstAcceptFlowablePlugin implements FlowablePlugin {

    @Autowired
    private FirstAcceptService firstAcceptService;

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
        FirstAcceptDetailVo equipmentEnterDetailVo = firstAcceptService.getInfoById(Long.parseLong(businessKey));
        log.info("FirstAcceptFlowablePlugin.equipmentEnterDetailVo: {}", equipmentEnterDetailVo);
        if (Objects.nonNull(equipmentEnterDetailVo)) {
            equipmentEnterDetailVo.setStatus(status);
            FirstAccept firstAccept = new FirstAccept();
            BeanUtil.copyProperties(equipmentEnterDetailVo, firstAccept, false);

            //施工技术、工艺方案说明和图表
            firstAccept.setBuildTechAttachment(null);
            //测量放样资料
            firstAccept.setMeasureAttachment(null);
            //材料出厂保证书、材料检测试验报告
            firstAccept.setMaterialAttachment(null);
            //机械的主要技术标准及最大生产能力
            firstAccept.setMechanicalAttachment(null);
            //批准的标准试验报告
            firstAccept.setTestAttachment(null);
            //开工申请
            firstAccept.setOpenAttachment(null);
            //质量保证资料
            firstAccept.setQualityAttachment(null);
            //影像资料
            firstAccept.setImageVideo(null);
            //首件工程总结
            firstAccept.setFirstProjectVideo(null);

            firstAcceptService.updateById(firstAccept);
        }
    }
}
