package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.certificate.domain.entity.CertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.PlanCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.service.CertificatePhotosService;
import com.ruoyi.jianguan.business.contract.domain.entity.ContractPayment;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentDetailVo;
import com.ruoyi.jianguan.business.contract.service.ContractPaymentService;
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
@Component("planCertificatePhotos")
public class PlanCertificatePhotosFlowablePlugin implements FlowablePlugin {

    @Autowired
    private CertificatePhotosService certificatePhotosService;


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

        PlanCertificatePhotosVo planCertificatePhotosVo = certificatePhotosService.getPlanInfoById(Long.parseLong(businessKey));
        log.info("PlanCertificatePhotosFlowablePlugin.planCertificatePhotosVo: {}", planCertificatePhotosVo);
        if (Objects.nonNull(planCertificatePhotosVo)) {
            CertificatePhotos certificatePhotos = new CertificatePhotos();
            BeanUtil.copyProperties(planCertificatePhotosVo, certificatePhotos, false);
            certificatePhotos.setPlanStatus(status);
            //合同信息
            certificatePhotos.setAttachment(null);
            certificatePhotosService.updateById(certificatePhotos);
        }
    }
}
