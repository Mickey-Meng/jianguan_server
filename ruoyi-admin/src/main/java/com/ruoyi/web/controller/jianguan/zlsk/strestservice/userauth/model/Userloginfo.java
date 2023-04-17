package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model;

public class Userloginfo
{
    private Integer id;
    private Integer userid;
    private String ip;
    private String intime;
    private String outtime;
    private Integer outtype;
    private Integer logcount;
    private String sttime;
    private Integer ststate;
    private Integer storder;

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(final Integer userid) {
        this.userid = userid;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public String getIntime() {
        return this.intime;
    }

    public void setIntime(final String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return this.outtime;
    }

    public void setOuttime(final String outtime) {
        this.outtime = outtime;
    }

    public Integer getOuttype() {
        return this.outtype;
    }

    public void setOuttype(final Integer outtype) {
        this.outtype = outtype;
    }

    public Integer getLogcount() {
        return this.logcount;
    }

    public void setLogcount(final Integer logcount) {
        this.logcount = logcount;
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
