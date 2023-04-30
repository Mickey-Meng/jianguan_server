package com.ruoyi.jianguan.listener;

import com.ruoyi.common.utils.ApplicationContextHolder;
import com.ruoyi.jianguan.business.quality.domain.entity.ProjectOpen;
import com.ruoyi.jianguan.business.quality.service.ProjectOpenService;
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
public class ProjectOpenListener implements ExecutionListener {

    @Autowired
    private ProjectOpenService projectOpenService;


    @Override
    public void notify(DelegateExecution delegateExecution) {
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        if (projectOpenService == null) {
            projectOpenService = ApplicationContextHolder.getBean(ProjectOpenService.class);
        }
        ProjectOpen projectOpen = projectOpenService.getById(businessKey);
        if (Objects.nonNull(projectOpen)) {
            projectOpen.setStatus(1);
            this.projectOpenService.updateById(projectOpen);
        }
    }
}
