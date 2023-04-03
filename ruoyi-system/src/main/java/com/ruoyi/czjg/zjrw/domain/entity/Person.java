package com.ruoyi.czjg.zjrw.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/9 13:43
 * @Version : 1.0
 * @Description :
 **/
public class Person implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 项目id(省平台: 标段)
     */
    @ApiModelProperty(value = "项目id(省平台: 标段)")
    private Integer projectId;
    /**
     * 记录人id
     */
    @ApiModelProperty(value = "记录人id")
    private Integer recordId;
    /**
     * 记录人名称
     */
    @ApiModelProperty(value = "记录人名称")
    private String recorder;
    /**
     * 报审日期
     */
    @ApiModelProperty(value = "报审日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date subDate;

    /**
     * 是否是合同人员: 0-不是,1-是
     */
    @ApiModelProperty(value = "是否是合同人员: 0-不是,1-是")
    private Integer isContract;
    /**
     * 处理人id
     */
    @ApiModelProperty(value = "处理人id")
    private Integer handle;
    /**
     * 处理意见
     */
    @ApiModelProperty(value = "处理意见")
    private String resolution;
    /**
     * 处理时间
     */
    @ApiModelProperty(value = "处理时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handlingTime;
    /**
     * 处理人id(为人员合同时,全资处理人的id)
     */
    @ApiModelProperty(value = "处理人id(为人员合同时,全资处理人的id)")
    private Integer handle1;
    /**
     * 处理意见(为人员合同时,全资处理人的处理意见)
     */
    @ApiModelProperty(value = "处理意见(为人员合同时,全资处理人的处理意见)")
    private String resolution1;
    /**
     * 处理时间(为人员合同时,全资处理人的处理时间)
     */
    @ApiModelProperty(value = "处理时间(为人员合同时,全资处理人的处理时间)")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handlingTime1;

    /**
     * 状态：0-草稿，1-提交，2-同意，3-驳回
     */
    @ApiModelProperty(value = "状态：0-草稿,1-提交,2-同意，3-驳回,4-监理提交全资审批,5-全资审批通过,6-全资审批驳回")
    private Integer status;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer order;
    /**
     * 发起流程时的id
     */
    private String taskId;

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public Integer getHandle1() {
        return handle1;
    }

    public void setHandle1(Integer handle1) {
        this.handle1 = handle1;
    }

    public String getResolution1() {
        return resolution1;
    }

    public void setResolution1(String resolution1) {
        this.resolution1 = resolution1;
    }

    public Date getHandlingTime1() {
        return handlingTime1;
    }

    public void setHandlingTime1(Date handlingTime1) {
        this.handlingTime1 = handlingTime1;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) &&
                Objects.equals(getProjectId(), person.getProjectId()) &&
                Objects.equals(getRecordId(), person.getRecordId()) &&
                Objects.equals(getRecorder(), person.getRecorder()) &&
                Objects.equals(getSubDate(), person.getSubDate()) &&
                Objects.equals(getIsContract(), person.getIsContract()) &&
                Objects.equals(getHandle(), person.getHandle()) &&
                Objects.equals(getResolution(), person.getResolution()) &&
                Objects.equals(getHandlingTime(), person.getHandlingTime()) &&
                Objects.equals(getHandle1(), person.getHandle1()) &&
                Objects.equals(getResolution1(), person.getResolution1()) &&
                Objects.equals(getHandlingTime1(), person.getHandlingTime1()) &&
                Objects.equals(getStatus(), person.getStatus()) &&
                Objects.equals(getOrder(), person.getOrder()) &&
                Objects.equals(getTaskId(), person.getTaskId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProjectId(), getRecordId(), getRecorder(), getSubDate(), getIsContract(), getHandle(), getResolution(), getHandlingTime(), getHandle1(), getResolution1(), getHandlingTime1(), getStatus(), getOrder(), getTaskId());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", recordId=" + recordId +
                ", recorder='" + recorder + '\'' +
                ", subDate=" + subDate +
                ", isContract=" + isContract +
                ", handle=" + handle +
                ", resolution='" + resolution + '\'' +
                ", handlingTime=" + handlingTime +
                ", handle1=" + handle1 +
                ", resolution1='" + resolution1 + '\'' +
                ", handlingTime1=" + handlingTime1 +
                ", status=" + status +
                ", order=" + order +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
