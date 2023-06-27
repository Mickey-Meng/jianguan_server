package com.ruoyi.workflow.plugin.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.jianguan.business.certificate.domain.entity.CertificatePhotos;
import com.ruoyi.jianguan.business.certificate.domain.vo.ProgressCertificatePhotosVo;
import com.ruoyi.jianguan.business.certificate.service.CertificatePhotosService;
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
@Component("progressCertificatePhotos")
public class ProgressCertificatePhotosFlowablePlugin implements FlowablePlugin {

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

        ProgressCertificatePhotosVo progressCertificatePhotosVo = certificatePhotosService.getProgressInfoById(Long.parseLong(businessKey));
        log.info("ProgressCertificatePhotosFlowablePlugin.progressCertificatePhotosVo: {}", progressCertificatePhotosVo);
        if (Objects.nonNull(progressCertificatePhotosVo)) {
            CertificatePhotos certificatePhotos = new CertificatePhotos();
            BeanUtil.copyProperties(progressCertificatePhotosVo, certificatePhotos, false);
            certificatePhotos.setProgressStatus(status);
            //合同信息
            certificatePhotos.setAttachment(null);
            certificatePhotosService.updateById(certificatePhotos);
        }
    }
}
