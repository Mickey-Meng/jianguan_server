package com.ruoyi.jianguan.common.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/7/25 16:15
 * @Version : 1.0
 * @Description :
 **/
public class OnlineUser {

    @ApiModelProperty(value = "在线人数统计")
    private Integer onlineCount;

    @ApiModelProperty(value = "在线人")
    private List<String> onlineUser;

    @ApiModelProperty(value = "所有人")
    private List<String> allUser;

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }

    public List<String> getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(List<String> onlineUser) {
        this.onlineUser = onlineUser;
    }

    public List<String> getAllUser() {
        return allUser;
    }

    public void setAllUser(List<String> allUser) {
        this.allUser = allUser;
    }

    @Override
    public String toString() {
        return "OnlineUser{" +
                "onlineCount=" + onlineCount +
                ", onlineUser='" + onlineUser + '\'' +
                ", allUser='" + allUser + '\'' +
                '}';
    }
}
