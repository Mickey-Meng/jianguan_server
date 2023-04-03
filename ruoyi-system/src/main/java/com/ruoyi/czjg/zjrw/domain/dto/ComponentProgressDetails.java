package com.ruoyi.czjg.zjrw.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : chenzhiwei
 * @Date : Create file in 2022/3/30 09:39
 * @Version : 1.0
 * @Description : 构件进度详细信息
 **/
public class ComponentProgressDetails implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "计划开始时间")
    private Date planStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "计划结束时间")
    private Date planEndTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "实际开始时间")
    private Date actualStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "实际结束时间")
    private Date actualEndTime;

    @ApiModelProperty(value = "施工状态：0-未开工，1-开工未完成，2-已完成")
    private Integer status;

    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

//    @Override
//    public String toString() {
//        return "ComponentProgressDetails{" +
//                "planStartTime=" + planStartTime +
//                ", planEndTime=" + planEndTime +
//                ", actualStartTime=" + actualStartTime +
//                ", actualEndTime=" + actualEndTime +
//                ", status=" + status +
//                '}';
//    }
}
