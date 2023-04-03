package com.ruoyi.czjg.zjrw.domain.entity;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/13 14:17
 * @Version : 1.0
 * @Description :
 **/
public class OriginalPersistentState {

    private String processDefinitionId;

    private Integer durationInMillis;

    private String formKey;

    private Integer priority;

    private String taskDefinitionKey;

    private String executionId;

    private String name;

    private String assignee;

    private String endTime;

    private String lastUpdateTime;

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public Integer getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(Integer durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "OriginalPersistentState{" +
                "processDefinitionId='" + processDefinitionId + '\'' +
                ", durationInMillis=" + durationInMillis +
                ", formKey='" + formKey + '\'' +
                ", priority=" + priority +
                ", taskDefinitionKey='" + taskDefinitionKey + '\'' +
                ", executionId='" + executionId + '\'' +
                ", name='" + name + '\'' +
                ", assignee='" + assignee + '\'' +
                ", endTime='" + endTime + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                '}';
    }
}
