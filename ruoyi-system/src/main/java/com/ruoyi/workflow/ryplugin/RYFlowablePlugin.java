package com.ruoyi.workflow.ryplugin;

import org.flowable.engine.runtime.ProcessInstance;

public interface RYFlowablePlugin {

    /**
     * 最后一个节点审批通过
     * @param processInstance
     */
    void approved(ProcessInstance processInstance);

    /**
     * 流程中审批通过
     * @param processInstance
     */
    void apply(ProcessInstance processInstance);

    /**
     * 驳回
     * @param processInstance
     */
    void reject(ProcessInstance processInstance);

    /**
     * 终止
     * @param processInstance
     */
    void terminate(ProcessInstance processInstance);

}
