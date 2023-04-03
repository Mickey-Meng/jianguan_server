package com.ruoyi.czjg.zjrw.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/7/13 09:25
 * @Version : 1.0
 * @Description :
 **/
public class UserOnlineDTO {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String username;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 最后更新在线时间
     */
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserOnlineDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
