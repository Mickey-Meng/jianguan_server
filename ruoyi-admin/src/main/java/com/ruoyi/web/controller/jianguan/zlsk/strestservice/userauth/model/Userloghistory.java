package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model;

public class Userloghistory
{
    private Integer id;
    private Integer userid;
    private String address;
    private String ip;
    private Integer pageSize;
    private Integer pageNum;
    private Integer logtype;
    private String sttime;
    private String stime;
    private String etime;

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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(final Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getLogtype() {
        return this.logtype;
    }

    public void setLogtype(final Integer logtype) {
        this.logtype = logtype;
    }

    public String getSttime() {
        return this.sttime;
    }

    public void setSttime(final String sttime) {
        this.sttime = sttime;
    }

    public String getStime() {
        return this.stime;
    }

    public void setStime(final String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return this.etime;
    }

    public void setEtime(final String etime) {
        this.etime = etime;
    }
}
