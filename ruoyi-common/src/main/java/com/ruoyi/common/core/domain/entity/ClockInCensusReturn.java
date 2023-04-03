package com.ruoyi.common.core.domain.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author : Chen_ZhiWei
 * @Date : Create file in 2022/9/2 14:57
 * @Version : 1.0
 * @Description :
 **/
public class ClockInCensusReturn {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户名(名字)")
    private String userName;

    @ApiModelProperty(value = "用户账号状态: 0-冻结, 1-启用")
    private Integer ststate;

    @ApiModelProperty(value = "单位id")
    private Integer unitId;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "已打卡天数")
    private Integer clockInDay;

    @ApiModelProperty(value = "未打卡天数")
    private Integer notClockInDay;

    @ApiModelProperty(value = "请假天数")
    private Double leaveDay;

    @ApiModelProperty(value = "考勤状态: 1-已打卡, 2-未打卡, 3-请休假")
    private Integer clockInState;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStstate() {
        return ststate;
    }

    public void setStstate(Integer ststate) {
        this.ststate = ststate;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getClockInDay() {
        return clockInDay;
    }

    public void setClockInDay(Integer clockInDay) {
        this.clockInDay = clockInDay;
    }

    public Integer getNotClockInDay() {
        return notClockInDay;
    }

    public void setNotClockInDay(Integer notClockInDay) {
        this.notClockInDay = notClockInDay;
    }

    public Double getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Double leaveDay) {
        this.leaveDay = leaveDay;
    }

    public Integer getClockInState() {
        return clockInState;
    }

    public void setClockInState(Integer clockInState) {
        this.clockInState = clockInState;
    }

    @Override
    public String toString() {
        return "ClockInCensusReturn{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", ststate=" + ststate +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", clockInDay=" + clockInDay +
                ", notClockInDay=" + notClockInDay +
                ", leaveDay=" + leaveDay +
                ", clockInState=" + clockInState +
                '}';
    }
}
