package com.ruoyi.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/11 18:03
 * @description : 用户对应角色权限
 **/
public class SsFUserRole implements Serializable {

    private Integer userid;

    private Integer roleid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sttime;

    private Integer ststate;

    private Integer storder;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    public Integer getStstate() {
        return ststate;
    }

    public void setStstate(Integer ststate) {
        this.ststate = ststate;
    }

    public Integer getStorder() {
        return storder;
    }

    public void setStorder(Integer storder) {
        this.storder = storder;
    }

    @Override
    public String toString() {
        return "SsFUserRole{" +
                "userid=" + userid +
                ", roleid=" + roleid +
                ", sttime=" + sttime +
                ", ststate=" + ststate +
                ", storder=" + storder +
                '}';
    }
}
