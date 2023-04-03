package com.ruoyi.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author ChenZhiWei
 * @since 2022-05-27
 */
public class ZjPersonLeave implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 请假人id
     */
    @ApiModelProperty(value = "请假人id")
    private Integer leavePersonId;

    /**
     * 请假人名
     */
    @ApiModelProperty(value = "请假人名")
    private String leaverPersonName;

    /**
     * 请假类型
     */
    @ApiModelProperty(value = "请假类型")
    private String leaverType;

    /**
     * 填报时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "填报时间")
    private Date subDate;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    /**
     * 请假天数
     */
    @ApiModelProperty(value = "请假天数")
    private Integer leaveDay;

    /**
     * 工作交接人id
     */
    @ApiModelProperty(value = "工作交接人id")
    private Integer handoffPersonId;

    /**
     * 工作交接人
     */
    @ApiModelProperty(value = "工作交接人")
    private String handoffPerson;

    /**
     * 请假原因
     */
    @ApiModelProperty(value = "请假原因")
    private String leaveReason;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 对应processInstanceId
     */
    @ApiModelProperty(value = "对应processInstanceId")
    private String processInstanceId;
    @ApiModelProperty(value = "taskId")
    private String taskId;
    @ApiModelProperty(value = "processDefinitionId")
    private String processDefinitionId;

    @ApiModelProperty(value = "项目id")
    private Integer projectId;
    @ApiModelProperty(value = "模板名称")
    private String processDefinitionKey;
    @ApiModelProperty(value = "下级审批人")
    private String variables;




    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "审核完成时间")
    private Date approvalTime;


    @ApiModelProperty(value = "businessKey")
    private String businessKey;

    @ApiModelProperty(value = "流程key")
    private String flowKey;

    private Map<String, Object> auditUser;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeavePersonId() {
        return leavePersonId;
    }
    public void setLeavePersonId(Integer leavePersonId) {
        this.leavePersonId = leavePersonId;
    }

    public String getLeaverPersonName() {
        return leaverPersonName;
    }
    public void setLeaverPersonName(String leaverPersonName) {
        this.leaverPersonName = leaverPersonName;
    }

    public String getLeaverType() {
        return leaverType;
    }
    public void setLeaverType(String leaverType) {
        this.leaverType = leaverType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getLeaveDay() {
        return leaveDay;
    }
    public void setLeaveDay(Integer leaveDay) {
        this.leaveDay = leaveDay;
    }

    public Integer getHandoffPersonId() {
        return handoffPersonId;
    }
    public void setHandoffPersonId(Integer handoffPersonId) {
        this.handoffPersonId = handoffPersonId;
    }

    public String getHandoffPerson() {
        return handoffPerson;
    }
    public void setHandoffPerson(String handoffPerson) {
        this.handoffPerson = handoffPerson;
    }

    public String getLeaveReason() {
        return leaveReason;
    }
    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Date getSubDate() {
        return subDate;
    }

    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZjPersonLeave)) return false;
        ZjPersonLeave that = (ZjPersonLeave) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getLeavePersonId(), that.getLeavePersonId()) &&
                Objects.equals(getLeaverPersonName(), that.getLeaverPersonName()) &&
                Objects.equals(getLeaverType(), that.getLeaverType()) &&
                Objects.equals(getSubDate(), that.getSubDate()) &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getLeaveDay(), that.getLeaveDay()) &&
                Objects.equals(getHandoffPersonId(), that.getHandoffPersonId()) &&
                Objects.equals(getHandoffPerson(), that.getHandoffPerson()) &&
                Objects.equals(getLeaveReason(), that.getLeaveReason()) &&
                Objects.equals(getRemark(), that.getRemark()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getOrder(), that.getOrder()) &&
                Objects.equals(getProcessInstanceId(), that.getProcessInstanceId()) &&
                Objects.equals(getTaskId(), that.getTaskId()) &&
                Objects.equals(getProcessDefinitionId(), that.getProcessDefinitionId()) &&
                Objects.equals(getProjectId(), that.getProjectId()) &&
                Objects.equals(getProcessDefinitionKey(), that.getProcessDefinitionKey()) &&
                Objects.equals(getVariables(), that.getVariables());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLeavePersonId(), getLeaverPersonName(), getLeaverType(), getSubDate(), getStartTime(), getEndTime(), getLeaveDay(), getHandoffPersonId(), getHandoffPerson(), getLeaveReason(), getRemark(), getStatus(), getOrder(), getProcessInstanceId(), getTaskId(), getProcessDefinitionId(), getProjectId(), getProcessDefinitionKey(), getVariables());
    }

    @Override
    public String toString() {
        return "ZjPersonLeave{" +
                "id=" + id +
                ", leavePersonId=" + leavePersonId +
                ", leaverPersonName='" + leaverPersonName + '\'' +
                ", leaverType='" + leaverType + '\'' +
                ", subDate=" + subDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", leaveDay=" + leaveDay +
                ", handoffPersonId=" + handoffPersonId +
                ", handoffPerson='" + handoffPerson + '\'' +
                ", leaveReason='" + leaveReason + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", order=" + order +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", processDefinitionId='" + processDefinitionId + '\'' +
                ", projectId=" + projectId +
                ", processDefinitionKey='" + processDefinitionKey + '\'' +
                ", variables='" + variables + '\'' +
                '}';
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public Map<String, Object> getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(Map<String, Object> auditUser) {
        this.auditUser = auditUser;
    }
}
