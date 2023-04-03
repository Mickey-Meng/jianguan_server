package com.ruoyi.czjg.zjrw.domain.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Chen_ZhiWei
 * @since 2022-06-09
 */
public class ZjPersonClockin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 项目（标段）id
     */
    @ApiModelProperty(value = "项目（标段）id")
    private Integer projectId;

    /**
     * 项目（标段）名称
     */
    @ApiModelProperty(value = "项目（标段）名称")
    private String projectName;

    /**
     * 电子围栏id
     */
    @ApiModelProperty(value = "电子围栏id", required = false)
    private Integer gid;

    @ApiModelProperty(value = "电子围栏名称", required = false)
    private String fenceAddrName;

    public String getFenceAddrName() {
        return fenceAddrName;
    }

    public void setFenceAddrName(String fenceAddrName) {
        this.fenceAddrName = fenceAddrName;
    }

    /**
     * 考勤人员id
     */
    @ApiModelProperty(value = "考勤人员id")
    private Integer attendancePersonId;

    /**
     * 考勤人员名称
     */
    @ApiModelProperty(value = "考勤人员名称")
    private String attendancePersonName;

    /**
     * 所在部门id
     */
    @ApiModelProperty(value = "所在部门id")
    private Integer groupId;

    /**
     * 所在部门名称
     */
    @ApiModelProperty(value = "所在部门名称")
    private String groupName;

    @ApiModelProperty(value = "打卡时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date clockTime;

    /**
     * 打卡位置（名称）
     */
    @ApiModelProperty(value = "打卡位置（名称）")
    private String clockAddr;

    /**
     * 打卡位置（坐标）
     */
    @ApiModelProperty(value = "打卡位置（坐标）")
    private String clockCoordinate;

    /**
     * 打卡照片
     */
    @ApiModelProperty(value = "打卡照片")
    private String clockPic;

    /**
     * 状态：0-无效， 1-有效
     */
    @ApiModelProperty(value = "状态：0-无效， 1-有效")
    private Integer state;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer order;

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

    public Integer getAttendancePersonId() {
        return attendancePersonId;
    }
    public void setAttendancePersonId(Integer attendancePersonId) {
        this.attendancePersonId = attendancePersonId;
    }

    public String getAttendancePersonName() {
        return attendancePersonName;
    }
    public void setAttendancePersonName(String attendancePersonName) {
        this.attendancePersonName = attendancePersonName;
    }

    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getClockTime() {
        return clockTime;
    }
    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
    }


    public String getClockAddr() {
        return clockAddr;
    }
    public void setClockAddr(String clockAddr) {
        this.clockAddr = clockAddr;
    }

    public String getClockCoordinate() {
        return clockCoordinate;
    }
    public void setClockCoordinate(String clockCoordinate) {
        this.clockCoordinate = clockCoordinate;
    }

    public String getClockPic() {
        return clockPic;
    }

    public void setClockPic(String clockPic) {
        this.clockPic = clockPic;
    }

    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "ZjPersonClockin{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", gid=" + gid +
                ", fenceAddrName='" + fenceAddrName + '\'' +
                ", attendancePersonId=" + attendancePersonId +
                ", attendancePersonName='" + attendancePersonName + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", clockTime=" + clockTime +
                ", clockAddr='" + clockAddr + '\'' +
                ", clockCoordinate='" + clockCoordinate + '\'' +
                ", clockPic='" + clockPic + '\'' +
                ", state=" + state +
                ", order=" + order +
                '}';
    }
}
