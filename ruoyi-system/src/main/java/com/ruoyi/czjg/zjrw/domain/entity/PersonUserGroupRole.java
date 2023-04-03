package com.ruoyi.czjg.zjrw.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/12 15:28
 * @Version : 1.0
 * @Description :
 **/
public class PersonUserGroupRole {

    @ApiModelProperty(value = "用户id")
    private Integer userid;
    @ApiModelProperty(value = "用户名称")
    private String username;
    @ApiModelProperty(value = "新部门组织机构id")
    private Integer personGroupid;
    @ApiModelProperty(value = "新部门组织机构名称")
    private String personGroupName;
    @ApiModelProperty(value = "角色id")
    private Integer roleid;
    @ApiModelProperty(value = "角色名")
    private String rolename;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sttime;
    @ApiModelProperty(value = "排序")
    private Integer storder;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonGroupName() {
        return personGroupName;
    }

    public void setPersonGroupName(String personGroupName) {
        this.personGroupName = personGroupName;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPersonGroupid() {
        return personGroupid;
    }

    public void setPersonGroupid(Integer personGroupid) {
        this.personGroupid = personGroupid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    public Integer getStorder() {
        return storder;
    }

    public void setStorder(Integer storder) {
        this.storder = storder;
    }
}
