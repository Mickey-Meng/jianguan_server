package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model;

public class UserGroup
{
    private Integer userid;
    private Integer groupid;
    private String sttime;
    private Integer ststate;
    private Integer storder;

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(final Integer userid) {
        this.userid = userid;
    }

    public Integer getGroupid() {
        return this.groupid;
    }

    public void setGroupid(final Integer groupid) {
        this.groupid = groupid;
    }

    public String getSttime() {
        return this.sttime;
    }

    public void setSttime(final String sttime) {
        this.sttime = sttime;
    }

    public Integer getStstate() {
        return this.ststate;
    }

    public void setStstate(final Integer ststate) {
        this.ststate = ststate;
    }

    public Integer getStorder() {
        return this.storder;
    }

    public void setStorder(final Integer storder) {
        this.storder = storder;
    }
}
