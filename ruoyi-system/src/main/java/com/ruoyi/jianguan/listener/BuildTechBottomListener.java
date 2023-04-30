package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.BuildTechBottom;
import com.ruoyi.jianguan.business.quality.service.BuildTechBottomService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author qiaoxulin
 * @date : 2022/6/15 15:17
 */
@Component
public class BuildTechBottomListener implements ExecutionListener {
    @Autowired
    private BuildTechBottomService buildTechBottomService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (buildTechBottomService == null) {
            buildTechBottomService = ApplicationContextHolder.getBean(BuildTechBottomService.class);
        }
        BuildTechBottom buildTechBottom = buildTechBottomService.getById(businessKey);
        if (Objects.nonNull(buildTechBottom)){
            buildTechBottom.setStatus(1);
            this.buildTechBottomService.updateById(buildTechBottom);
        }
    }
}
