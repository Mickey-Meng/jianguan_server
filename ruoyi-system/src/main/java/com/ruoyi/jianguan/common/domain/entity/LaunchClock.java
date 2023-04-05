package com.ruoyi.jianguan.common.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/11 17:12
 * @Version : 1.0
 * @Description :
 **/
public class LaunchClock implements Serializable {

    private Integer id;

    private String projectName;

    private String clockUser;

    private String clockName;

    private String clockAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date clockTime;

    private String clockPic;

    private Integer checkUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date checkTime;

    private Integer status;

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

    public String getClockUser() {
        return clockUser;
    }

    public void setClockUser(String clockUser) {
        this.clockUser = clockUser;
    }

    public Date getClockTime() {
        return clockTime;
    }

    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
    }

    public String getClockName() {
        return clockName;
    }

    public void setClockName(String clockName) {
        this.clockName = clockName;
    }

    public String getClockAddress() {
        return clockAddress;
    }

    public void setClockAddress(String clockAddress) {
        this.clockAddress = clockAddress;
    }

    public String getClockPic() {
        return clockPic;
    }

    public void setClockPic(String clockPic) {
        this.clockPic = clockPic;
    }

    public Integer getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(Integer checkUser) {
        this.checkUser = checkUser;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LaunchClock{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", clockUser='" + clockUser + '\'' +
                ", clockName='" + clockName + '\'' +
                ", clockAddress='" + clockAddress + '\'' +
                ", clockTime=" + clockTime +
                ", clockPic='" + clockPic + '\'' +
                ", checkUser=" + checkUser +
                ", checkTime=" + checkTime +
                ", status=" + status +
                '}';
    }
}
