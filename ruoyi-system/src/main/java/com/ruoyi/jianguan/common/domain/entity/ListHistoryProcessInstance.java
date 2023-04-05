package com.ruoyi.jianguan.common.domain.entity;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/6/13 14:42
 * @Version : 1.0
 * @Description :
 **/
public class ListHistoryProcessInstance {

    private String endActivityId;

    private String businessKey;

    private String startUserId;

    private String startActivityId;

    private String tenantId;

    private String processDefinitionKey;

    private String processDefinitionName;

    private Integer processDefinitionVersion;

    private String deploymentId;

    private String processInstanceId;

    private String processDefinitionId;

    private String startTime;

    private String endTime;

    private Integer durationInMillis;

    private String id;

    private Integer revision;

    private boolean isInserted;

    private boolean isUpdated;

    private boolean isDeleted;

    private OriginalPersistentState originalPersistentState;

    public String getEndActivityId() {
        return endActivityId;
    }

    public void setEndActivityId(String endActivityId) {
        this.endActivityId = endActivityId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    public String getStartActivityId() {
        return startActivityId;
    }

    public void setStartActivityId(String startActivityId) {
        this.startActivityId = startActivityId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    @Override
    public String toString() {
        return "ListHistoryProcessInstance{" +
                "endActivityId='" + endActivityId + '\'' +
                ", businessKey='" + businessKey + '\'' +
                ", startUserId='" + startUserId + '\'' +
                ", startActivityId='" + startActivityId + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", processDefinitionKey='" + processDefinitionKey + '\'' +
                ", processDefinitionName='" + processDefinitionName + '\'' +
                ", processDefinitionVersion=" + processDefinitionVersion +
                ", deploymentId='" + deploymentId + '\'' +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", processDefinitionId='" + processDefinitionId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", durationInMillis=" + durationInMillis +
                ", id='" + id + '\'' +
                ", revision=" + revision +
                ", isInserted=" + isInserted +
                ", isUpdated=" + isUpdated +
                ", isDeleted=" + isDeleted +
                ", originalPersistentState=" + originalPersistentState +
                '}';
    }
}
