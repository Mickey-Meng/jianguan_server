package com.ruoyi.workflow.plugin;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.flowable.service.FlowApiService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Component
public class FlowablePluginExecutor {

    @Resource
    private Map<String, FlowablePlugin> flowablePluginMap;

    @Autowired
    private FlowApiService flowApiService;

    public void executeApply(ProcessInstance processInstance) {
        log.info("FlowablePluginExecutor.executeApply.processInstance: {}", processInstance);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        // 流程最后一个节点审批完成
        String nextExecutor = flowApiService.getNextExecutor(processInstance.getProcessInstanceId());
        if(StrUtil.isBlank(nextExecutor)) {
            flowablePluginMap.forEach((key, plugin) -> {
                if(key.equals(processDefinitionKey)) {
                    plugin.approved(processInstance);
                }
            });
            return;
        }
        // 流程中审批完成
        flowablePluginMap.forEach((key, plugin) -> {
            if(key.equals(processDefinitionKey)) {
                plugin.apply(processInstance);
            }
        });
    }

    public void executeRejectToStart(String processInstanceId) {
        log.info("FlowablePluginExecutor.executeRejectToStart.processInstanceId: {}", processInstanceId);
        ProcessInstance processInstance = flowApiService.getProcessInstance(processInstanceId);
        log.info("FlowablePluginExecutor.executeRejectToStart.processInstance: {}", processInstance);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        flowablePluginMap.forEach((key, plugin) -> {
            if(key.equals(processDefinitionKey)) {
                plugin.rejectToStart(processInstance);
            }
        });
    }

    public void executeStop(String processInstanceId) {
        log.info("FlowablePluginExecutor.executeStop.processInstanceId: {}", processInstanceId);
        ProcessInstance processInstance = flowApiService.getProcessInstance(processInstanceId);
        log.info("FlowablePluginExecutor.executeStop.processInstance: {}", processInstance);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        flowablePluginMap.forEach((key, plugin) -> {
            if(key.equals(processDefinitionKey)) {
                plugin.stop(processInstance);
            }
        });
    }
}
