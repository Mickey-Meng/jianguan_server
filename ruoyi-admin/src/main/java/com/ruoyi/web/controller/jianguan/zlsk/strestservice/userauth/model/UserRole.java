package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model;

public class UserRole
{
    private Integer userid;
    private Integer roleid;
    private String sttime;
    private Integer ststate;
    private Integer storder;

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(final Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return this.roleid;
    }

    public void setRoleid(final Integer roleid) {
        this.roleid = roleid;
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
