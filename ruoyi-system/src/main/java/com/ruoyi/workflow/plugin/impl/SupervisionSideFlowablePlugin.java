package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionSide;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionSideDetailVo;
import com.ruoyi.jianguan.business.quality.service.SupervisionSideService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 监理旁站工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("jianlipangzhan")
public class SupervisionSideFlowablePlugin implements FlowablePlugin {

    @Autowired
    private SupervisionSideService supervisionSideService;

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
        SupervisionSideDetailVo supervisionSideDetailVo = supervisionSideService.getInfoById(Long.parseLong(businessKey));
        log.info("SupervisionSideFlowablePlugin.supervisionSideDetailVo: {}", supervisionSideDetailVo);
        if (Objects.nonNull(supervisionSideDetailVo)) {
            supervisionSideDetailVo.setStatus(status);
            SupervisionSide supervisionSide = new SupervisionSide();
            BeanUtil.copyProperties(supervisionSideDetailVo, supervisionSide, false);
            //旁站现场照片
            supervisionSide.setScenePhotoAttachment(null);
            //实测实量照片
            supervisionSide.setActualCheckAttachment(null);
            //视频上传
            supervisionSide.setVideo(null);
            //附件上传
            supervisionSide.setAttachment(null);
            supervisionSideService.updateById(supervisionSide);
        }
    }
}
