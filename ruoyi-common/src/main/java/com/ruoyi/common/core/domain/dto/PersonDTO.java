package com.ruoyi.common.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Map;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/10 11:09
 * @Version : 1.0
 * @Description :
 **/
public class PersonDTO {

    /**
     * id
     */
    private Integer id;
    /**
     * 项目(标段)id
     */
    private Integer projectId;
    /**
     * 项目(标段)名
     */
    private String projectName;
    /**
     * 记录人id
     */
    private Integer recordId;
    /**
     * 记录人名称
     */
    private String recorder;
    /**
     * 记录人名称
     */
    private String recodeName;
    /**
     * 报审日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date subDate;

    /**
     * 是否是合同人员: 0-不是,1-是
     */
    private Integer isContract;
    /**
     * 处理人id
     */
    private Integer handle;
    /**
     * 处理人名称
     */
    private String handleName;
    /**
     * 处理意见
     */
    private String resolution;
    /**
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handlingTime;
    /**
     * 状态：0-草稿，1-提交，2-同意，3-驳回
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer order;

    /**
     * 发起流程时的id
     */
    private String taskId;


    /**
     * 审核通过时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date approvalTime;



    @ApiModelProperty(value = "流程实例")
    private String businessKey;

    @ApiModelProperty(value = "流程key")
    private String flowKey;

    /**
     * 更新时间。
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 更新者Id。
     */
    @ApiModelProperty(value = "更新者Id")
    private Long updateUserId;

    /**
     * 创建时间。
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建者Id。
     */
    @ApiModelProperty(value = "创建者Id")
    private Long createUserId;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getRecodeName() {
        return recodeName;
    }

    public void setRecodeName(String recodeName) {
        this.recodeName = recodeName;
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

    public Integer getHandle() {
        return handle;
    }

    public void setHandle(Integer handle) {
        this.handle = handle;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Date getHandlingTime() {
        return handlingTime;
    }

    public void setHandlingTime(Date handlingTime) {
        this.handlingTime = handlingTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", recordId=" + recordId +
                ", recorder='" + recorder + '\'' +
                ", recodeName='" + recodeName + '\'' +
                ", subDate=" + subDate +
                ", isContract=" + isContract +
                ", handle=" + handle +
                ", handleName='" + handleName + '\'' +
                ", resolution='" + resolution + '\'' +
                ", handlingTime=" + handlingTime +
                ", status=" + status +
                ", order=" + order +
                ", taskId='" + taskId + '\'' +
                '}';
    }

    private Map<String, Object> maps;

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
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
}
