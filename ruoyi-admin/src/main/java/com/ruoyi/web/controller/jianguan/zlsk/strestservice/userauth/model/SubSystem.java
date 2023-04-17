package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model;

public class SubSystem
{
    private Integer ID;
    private String sysName;
    private String sysCode;
    private String type;
    private String createtime;
    private Integer ststate;
    private Integer pageNumber;
    private Integer pageSize;

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(final Integer iD) {
        this.ID = iD;
    }

    public String getSysName() {
        return this.sysName;
    }

    public void setSysName(final String sysName) {
        this.sysName = sysName;
    }

    public String getSysCode() {
        return this.sysCode;
    }

    public void setSysCode(final String sysCode) {
        this.sysCode = sysCode;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(final String createtime) {
        this.createtime = createtime;
    }

    public Integer getStstate() {
        return this.ststate;
    }

    public void setStstate(final Integer ststate) {
        this.ststate = ststate;
    }
}
