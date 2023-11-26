package com.ruoyi.workflow.ryplugin;

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
public class RYFlowablePluginExecutor {

    @Resource
    private Map<String, RYFlowablePlugin> flowablePluginMap;

    /**
     *
     * REJECT: 驳回, PENDING: 待审批, APPROVING: 审批中, APPROVED: 已审批
     *
     */

    @Autowired
    private FlowApiService flowApiService;

    public void executeApply(String processInstanceId) {
        ProcessInstance processInstance = flowApiService.getProcessInstance(processInstanceId);
        log.info("RYFlowablePluginExecutor.executeApply.processInstance: {}", processInstance);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();

        // 流程中审批完成
        flowablePluginMap.forEach((key, plugin) -> {
            if(key.equals(processDefinitionKey)) {
                plugin.apply(processInstance);
            }
        });
    }

    public void executeRApproveing(String processInstanceId) {
        log.info("RYFlowablePluginExecutor.executeReject.processInstanceId: {}", processInstanceId);
        ProcessInstance processInstance = flowApiService.getProcessInstance(processInstanceId);
        log.info("RYFlowablePluginExecutor.executeReject.processInstance: {}", processInstance);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        flowablePluginMap.forEach((key, plugin) -> {
            if(key.equals(processDefinitionKey)) {
                plugin.reject(processInstance);
            }
        });
    }

    public void executeReject(String processInstanceId) {
        log.info("RYFlowablePluginExecutor.executeReject.processInstanceId: {}", processInstanceId);
        ProcessInstance processInstance = flowApiService.getProcessInstance(processInstanceId);
        log.info("RYFlowablePluginExecutor.executeReject.processInstance: {}", processInstance);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        flowablePluginMap.forEach((key, plugin) -> {
            if(key.equals(processDefinitionKey)) {
                plugin.reject(processInstance);
            }
        });
    }

    public void executeTerminate(String processInstanceId) {
        log.info("FlowablePluginExecutor.executeTerminate.processInstanceId: {}", processInstanceId);
        ProcessInstance processInstance = flowApiService.getProcessInstance(processInstanceId);
        log.info("FlowablePluginExecutor.executeTerminate.processInstance: {}", processInstance);
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        flowablePluginMap.forEach((key, plugin) -> {
            if(key.equals(processDefinitionKey)) {
                plugin.terminate(processInstance);
            }
        });
    }
}
