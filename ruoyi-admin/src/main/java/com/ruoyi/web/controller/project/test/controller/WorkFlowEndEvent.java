package com.ruoyi.web.controller.project.test.controller;

import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @author jing-zhang
 * @version 1.0.0
 * @date 2022/12/2 18:30
 */
@Component
public class WorkFlowEndEvent implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("delegateTask"+delegateTask);
    }
}
