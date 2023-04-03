package com.ruoyi.czjg.zjrw.domain.entity;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/25 14:41
 * @Version : 1.0
 * @Description :
 **/
public class ProcessReturn {

//                "businessKey": "788c8433-ca98-4eb7-aabd-c80a38d7d172",
//                "entryId": "1532261048160096256",
//                "processDefinitionId": "sgdwhtrybs:1:9a4b9c4d-e245-11ec-ab37-00ff33f70615",
//                "processDefinitionKey": "sgdwhtrybs",
//                "processDefinitionName": "施工单位合同人员报审",
//                "processDefinitionVersion": 1,
//                "processInstanceId": "57e67462-e24e-11ec-bd33-00163e1474aa",
//                "processInstanceInitiator": "18206100812",
//                "processInstanceStartTime": "2022-06-02 16:31:05",
//                "taskFormKey": "{\"routerName\":\"sgdwhtrybs\",\"readOnly\":true,\"groupType\":\"ASSIGNEE\"}",
//                "taskId": "5858be42-e24e-11ec-bd33-00163e1474aa",
//                "taskKey": "Activity_0sjwq4d",
//                "taskName": "项目经理审批"

    private String businessKey;

    private String entryId;

    private String processDefinitionId;

    private String processDefinitionKey;

    private String processDefinitionName;

    private Integer processDefinitionVersion;

    private String processInstanceId;

    private String processInstanceInitiator;

    private String processInstanceStartTime;

    private String taskFormKey;

    private String taskId;

    private String taskKey;

    private String taskName;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    public Integer getProcessDefinitionVersion() {
        return processDefinitionVersion;
    }

    public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
        this.processDefinitionVersion = processDefinitionVersion;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceInitiator() {
        return processInstanceInitiator;
    }

    public void setProcessInstanceInitiator(String processInstanceInitiator) {
        this.processInstanceInitiator = processInstanceInitiator;
    }

    public String getProcessInstanceStartTime() {
        return processInstanceStartTime;
    }

    public void setProcessInstanceStartTime(String processInstanceStartTime) {
        this.processInstanceStartTime = processInstanceStartTime;
    }

    public void setTaskFormKey(String taskFormKey) {
        this.taskFormKey = taskFormKey;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
