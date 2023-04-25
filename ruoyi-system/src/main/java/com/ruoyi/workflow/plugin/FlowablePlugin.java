package com.ruoyi.workflow.plugin;

import org.flowable.engine.runtime.ProcessInstance;

public interface FlowablePlugin {

    /**
     * 审批通过
     * @param processInstance
     */
    void apply(ProcessInstance processInstance);

    /**
     * 驳回至起点
     * @param processInstance
     */
    void rejectToStart(ProcessInstance processInstance);

    /**
     * 终止流程
     * @param processInstance
     */
    void stop(ProcessInstance processInstance);

}