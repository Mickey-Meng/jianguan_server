package com.ruoyi.czjg.zjrw.domain.entity;

import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/13 14:10
 * @Version : 1.0
 * @Description :
 **/
public class ProcessListHistoryReturn {

    private String executionId;

    private String processInstanceId;

    private String processDefinitionId;

    private String createTime;

    private String endTime;

    private Integer durationInMillis;

    private String name;

    private String assignee;

    private String taskDefinitionKey;

    private String formKey;

    private Integer priority;

    private String tenantId;

    private String lastUpdateTime;

    private List<Object> queryIdentityLinks;

    private List<Object> identityLinks;

    private String id;

    private Integer revision;

    private boolean isInserted;

    private boolean isUpdated;

    private boolean isDeleted;

    private OriginalPersistentState originalPersistentState;

    private String processDefinitionKey;

    private String processDefinitionName;

    private String startUser;

    private String businessKey;

    private String approvalType;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getDurationInMillis() {
        return durationInMillis;
    }

    public void setDurationInMillis(Integer durationInMillis) {
        this.durationInMillis = durationInMillis;
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

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<Object> getQueryIdentityLinks() {
        return queryIdentityLinks;
    }

    public void setQueryIdentityLinks(List<Object> queryIdentityLinks) {
        this.queryIdentityLinks = queryIdentityLinks;
    }

    public List<Object> getIdentityLinks() {
        return identityLinks;
    }

    public void setIdentityLinks(List<Object> identityLinks) {
        this.identityLinks = identityLinks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public boolean isInserted() {
        return isInserted;
    }

    public void setInserted(boolean inserted) {
        isInserted = inserted;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public OriginalPersistentState getOriginalPersistentState() {
        return originalPersistentState;
    }

    public void setOriginalPersistentState(OriginalPersistentState originalPersistentState) {
        this.originalPersistentState = originalPersistentState;
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

    public String getStartUser() {
        return startUser;
    }

    public void setStartUser(String startUser) {
        this.startUser = startUser;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    @Override
    public String toString() {
        return "ProcessListHistoryReturn{" +
                "executionId='" + executionId + '\'' +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", processDefinitionId='" + processDefinitionId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", durationInMillis=" + durationInMillis +
                ", name='" + name + '\'' +
                ", assignee='" + assignee + '\'' +
                ", taskDefinitionKey='" + taskDefinitionKey + '\'' +
                ", formKey='" + formKey + '\'' +
                ", priority=" + priority +
                ", tenantId='" + tenantId + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", queryIdentityLinks=" + queryIdentityLinks +
                ", identityLinks=" + identityLinks +
                ", id='" + id + '\'' +
                ", revision=" + revision +
                ", isInserted=" + isInserted +
                ", isUpdated=" + isUpdated +
                ", isDeleted=" + isDeleted +
                ", originalPersistentState=" + originalPersistentState +
                ", processDefinitionKey='" + processDefinitionKey + '\'' +
                ", processDefinitionName='" + processDefinitionName + '\'' +
                ", startUser='" + startUser + '\'' +
                ", businessKey='" + businessKey + '\'' +
                ", approvalType='" + approvalType + '\'' +
                '}';
    }
}
