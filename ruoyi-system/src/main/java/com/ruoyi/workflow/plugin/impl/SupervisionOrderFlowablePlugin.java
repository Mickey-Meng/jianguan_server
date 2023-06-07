package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.jianguan.business.contract.domain.entity.EquipmentEnter;
import com.ruoyi.jianguan.business.contract.domain.vo.EquipmentEnterDetailVo;
import com.ruoyi.jianguan.business.quality.domain.entity.SupervisionOrder;
import com.ruoyi.jianguan.business.quality.domain.vo.SupervisionOrderDetailVo;
import com.ruoyi.jianguan.business.quality.service.SupervisionOrderService;
import com.ruoyi.workflow.plugin.FlowablePlugin;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 监理指令工作流插件
 * @author G.X.L
 */
@Slf4j
@Component("jianlizhiling")
public class SupervisionOrderFlowablePlugin implements FlowablePlugin {

    @Autowired
    private SupervisionOrderService supervisionOrderService;

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
        SupervisionOrderDetailVo supervisionOrderDetailVo = supervisionOrderService.getInfoById(Long.parseLong(businessKey));
        log.info("SupervisionOrderFlowablePlugin.supervisionOrderDetailVo: {}", supervisionOrderDetailVo);
        if (Objects.nonNull(supervisionOrderDetailVo)) {
            supervisionOrderDetailVo.setStatus(status);
            SupervisionOrder supervisionOrder = new SupervisionOrder();
            BeanUtil.copyProperties(supervisionOrderDetailVo, supervisionOrder, false);
            //问题照片
            supervisionOrder.setProblemPhotoAttachment(null);
            //其他附件
            supervisionOrder.setOtherAttachment(null);
            //整改照片
            supervisionOrder.setReplyPhotoAttachment(null);
            //其他附件
            supervisionOrder.setReplyOtherAttachment(null);
            supervisionOrderService.updateById(supervisionOrder);
        }
    }
}
