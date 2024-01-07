package com.ruoyi.workflow.front.plugin;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Component
public class FrontFlowablePluginExecutor {

    @Resource
    private Map<String, FrontFlowablePlugin> flowablePluginMap;


    @Resource
    protected RuntimeService runtimeService;

    /**
     *
     * 0: 审批中
     * 1：审批完成
     * 2：驳回
     *
     */

    public void executeApply(ProcessInstance processInstance) {

    }

    public void executeRejectToStart(String processInstanceId) {

    }

    public void executeStop(String processInstanceId) {
        log.info("FrontFlowablePluginExecutor.executeStop.processInstanceId: {}", processInstanceId);
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        log.info("FrontFlowablePluginExecutor.executeStop.processInstance: {}", processInstance);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        flowablePluginMap.forEach((key, plugin) -> {
            if(key.equals(processDefinitionKey)) {
                plugin.stop(processInstance);
            }
        });
    }
}
