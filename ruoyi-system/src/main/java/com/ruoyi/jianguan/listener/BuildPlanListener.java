package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.BuildPlan;
import com.ruoyi.jianguan.business.quality.service.BuildPlanService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author qiaoxulin
 * @date : 2022/6/15 15:05
 */
@Component
public class BuildPlanListener implements ExecutionListener {

    @Autowired
    private BuildPlanService buildPlanService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (buildPlanService == null) {
            buildPlanService = ApplicationContextHolder.getBean(BuildPlanService.class);
        }
        BuildPlan buildPlan = buildPlanService.getById(businessKey);
        if (Objects.nonNull(buildPlan)){
            buildPlan.setStatus(1);
            this.buildPlanService.updateById(buildPlan);
        }
    }
}
