package com.ruoyi.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author ChenZhiWei
 * @since 2022-05-26
 */
@Data
public class ZjPersonChange implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    /**
     * 项目名
     */
    @ApiModelProperty(value = "项目名")
    private String projectName;

    /**
     * 项目标段id
     */
    @ApiModelProperty(value = "项目标段id")
    private Integer projectChildId;

    /**
     * 项目标段名
     */
    @ApiModelProperty(value = "项目标段名")
    private String projectChildName;

    /**
     * 变更发起人id
     */
    @ApiModelProperty(value = "变更发起人id")
    private Integer recordId;

    /**
     * 变更发起人名
     */
    @ApiModelProperty(value = "变更发起人名")
    private String recorder;

    /**
     * 变更发起时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "变更发起时间")
    private Date subDate;

    /**
     * 是否是合同类型
     */
    @ApiModelProperty(value = "是否是合同类型")
    private Integer isContract;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Integer changeType;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String changeTypeName;

    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Integer changeUnit;

    /**
     * 组织名
     */
    @ApiModelProperty(value = "组织名")
    private String changeUnitName;

    /**
     * 变更岗位id
     */
    @ApiModelProperty(value = "变更岗位id")
    private Integer changePost;

    /**
     * 变更岗位名
     */
    @ApiModelProperty(value = "变更岗位名")
    private String changePostName;

    /**
     * 变更前人员id
     */
    @ApiModelProperty(value = "变更前人员id")
    private Integer beforePersonId;

    /**
     * 变更前人员名
     */
    @ApiModelProperty(value = "变更前人员名")
    private String beforePerson;

    /**
     * 变更前（执业）资格证书号
     */
    @ApiModelProperty(value = "变更前（执业）资格证书号")
    private String beforeCode;

    /**
     * 变更后人员id
     */
    @ApiModelProperty(value = "变更后人员id")
    private Integer afterPersonId;

    /**
     * 变更后人员名
     */
    @ApiModelProperty(value = "变更后人员名")
    private String afterPerson;

    /**
     * 变更后（执业）资格证书号
     */
    @ApiModelProperty(value = "变更后（执业）资格证书号")
    private String afterCode;

    /**
     * 变更原因
     */
    @ApiModelProperty(value = "变更原因")
    private String changeReason;

    /**
     * 附件集合，以逗号分开
     */
    @ApiModelProperty(value = "附件集合，以逗号分开")
    private List<FileModel> files;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer order;

    /**
     * processInstanceId
     */
    @ApiModelProperty(value = "processInstanceId")
    private String processInstanceId;
    @ApiModelProperty(value = "taskId")
    private String taskId;
    @ApiModelProperty(value = "processDefinitionId")
    private String processDefinitionId;

    @ApiModelProperty(value = "模板名称")
    private String processDefinitionKey;
    @ApiModelProperty(value = "下级审批人")
    private String variables;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "审核完成时间")
    private Date approvalTime;


    /**
     * 附件集合，以逗号分开
     */
    @ApiModelProperty(value = "附件集合，以逗号分开")
    private List<ZjPersonChangeFile> changeFiles;


    @ApiModelProperty(value = "businessKey")
    private String businessKey;

    @ApiModelProperty(value = "流程key")
    private String flowKey;

    /**
     * 附件
     */
    private String attachment;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProjectChildId() {
        return projectChildId;
    }

    public void setProjectChildId(Integer projectChildId) {
        this.projectChildId = projectChildId;
    }

    public String getProjectChildName() {
        return projectChildName;
    }

    public void setProjectChildName(String projectChildName) {
        this.projectChildName = projectChildName;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public Date getSubDate() {
        return subDate;
    }

    public void setSubDate(Date subDate) {
        this.subDate = subDate;
    }

    public Integer getIsContract() {
        return isContract;
    }

    public void setIsContract(Integer isContract) {
        this.isContract = isContract;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public String getChangeTypeName() {
        return changeTypeName;
    }

    public void setChangeTypeName(String changeTypeName) {
        this.changeTypeName = changeTypeName;
    }

    public Integer getChangeUnit() {
        return changeUnit;
    }

    public void setChangeUnit(Integer changeUnit) {
        this.changeUnit = changeUnit;
    }

    public String getChangeUnitName() {
        return changeUnitName;
    }

    public void setChangeUnitName(String changeUnitName) {
        this.changeUnitName = changeUnitName;
    }

    public Integer getChangePost() {
        return changePost;
    }

    public void setChangePost(Integer changePost) {
        this.changePost = changePost;
    }

    public String getChangePostName() {
        return changePostName;
    }

    public void setChangePostName(String changePostName) {
        this.changePostName = changePostName;
    }

    public Integer getBeforePersonId() {
        return beforePersonId;
    }

    public void setBeforePersonId(Integer beforePersonId) {
        this.beforePersonId = beforePersonId;
    }

    public String getBeforePerson() {
        return beforePerson;
    }

    public void setBeforePerson(String beforePerson) {
        this.beforePerson = beforePerson;
    }

    public String getBeforeCode() {
        return beforeCode;
    }

    public void setBeforeCode(String beforeCode) {
        this.beforeCode = beforeCode;
    }

    public Integer getAfterPersonId() {
        return afterPersonId;
    }

    public void setAfterPersonId(Integer afterPersonId) {
        this.afterPersonId = afterPersonId;
    }

    public String getAfterPerson() {
        return afterPerson;
    }

    public void setAfterPerson(String afterPerson) {
        this.afterPerson = afterPerson;
    }

    public String getAfterCode() {
        return afterCode;
    }

    public void setAfterCode(String afterCode) {
        this.afterCode = afterCode;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZjPersonChange)) return false;
        ZjPersonChange that = (ZjPersonChange) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getProjectId(), that.getProjectId()) &&
                Objects.equals(getProjectName(), that.getProjectName()) &&
                Objects.equals(getProjectChildId(), that.getProjectChildId()) &&
                Objects.equals(getProjectChildName(), that.getProjectChildName()) &&
                Objects.equals(getRecordId(), that.getRecordId()) &&
                Objects.equals(getRecorder(), that.getRecorder()) &&
                Objects.equals(getSubDate(), that.getSubDate()) &&
                Objects.equals(getIsContract(), that.getIsContract()) &&
                Objects.equals(getChangeType(), that.getChangeType()) &&
                Objects.equals(getChangeTypeName(), that.getChangeTypeName()) &&
                Objects.equals(getChangeUnit(), that.getChangeUnit()) &&
                Objects.equals(getChangeUnitName(), that.getChangeUnitName()) &&
                Objects.equals(getChangePost(), that.getChangePost()) &&
                Objects.equals(getChangePostName(), that.getChangePostName()) &&
                Objects.equals(getBeforePersonId(), that.getBeforePersonId()) &&
                Objects.equals(getBeforePerson(), that.getBeforePerson()) &&
                Objects.equals(getBeforeCode(), that.getBeforeCode()) &&
                Objects.equals(getAfterPersonId(), that.getAfterPersonId()) &&
                Objects.equals(getAfterPerson(), that.getAfterPerson()) &&
                Objects.equals(getAfterCode(), that.getAfterCode()) &&
                Objects.equals(getChangeReason(), that.getChangeReason()) &&
                Objects.equals(getFiles(), that.getFiles()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getOrder(), that.getOrder()) &&
                Objects.equals(getProcessInstanceId(), that.getProcessInstanceId()) &&
                Objects.equals(getTaskId(), that.getTaskId()) &&
                Objects.equals(getProcessDefinitionId(), that.getProcessDefinitionId()) &&
                Objects.equals(getProcessDefinitionKey(), that.getProcessDefinitionKey()) &&
                Objects.equals(getVariables(), that.getVariables());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProjectId(), getProjectName(), getProjectChildId(), getProjectChildName(), getRecordId(), getRecorder(), getSubDate(), getIsContract(), getChangeType(), getChangeTypeName(), getChangeUnit(), getChangeUnitName(), getChangePost(), getChangePostName(), getBeforePersonId(), getBeforePerson(), getBeforeCode(), getAfterPersonId(), getAfterPerson(), getAfterCode(), getChangeReason(), getFiles(), getStatus(), getOrder(), getProcessInstanceId(), getTaskId(), getProcessDefinitionId(), getProcessDefinitionKey(), getVariables());
    }

    @Override
    public String toString() {
        return "ZjPersonChange{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectChildId=" + projectChildId +
                ", projectChildName='" + projectChildName + '\'' +
                ", recordId=" + recordId +
                ", recorder='" + recorder + '\'' +
                ", subDate=" + subDate +
                ", isContract=" + isContract +
                ", changeType=" + changeType +
                ", changeTypeName='" + changeTypeName + '\'' +
                ", changeUnit=" + changeUnit +
                ", changeUnitName='" + changeUnitName + '\'' +
                ", changePost=" + changePost +
                ", changePostName='" + changePostName + '\'' +
                ", beforePersonId=" + beforePersonId +
                ", beforePerson='" + beforePerson + '\'' +
                ", beforeCode='" + beforeCode + '\'' +
                ", afterPersonId=" + afterPersonId +
                ", afterPerson='" + afterPerson + '\'' +
                ", afterCode='" + afterCode + '\'' +
                ", changeReason='" + changeReason + '\'' +
                ", files=" + files +
                ", status=" + status +
                ", order=" + order +
                ", processInstanceId='" + processInstanceId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", processDefinitionId='" + processDefinitionId + '\'' +
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

    public List<ZjPersonChangeFile> getChangeFiles() {
        return changeFiles;
    }

    public void setChangeFiles(List<ZjPersonChangeFile> changeFiles) {
        this.changeFiles = changeFiles;
    }
}
