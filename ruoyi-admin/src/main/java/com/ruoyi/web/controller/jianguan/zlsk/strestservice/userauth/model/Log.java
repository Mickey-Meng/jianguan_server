package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model;

public class Log
{
    private Integer id;
    private String loginIp;
    private String operation;
    private Integer operUserId;
    private String logType;
    private String remark;
    private Integer ststate;
    private String createtime;
    private String operName;
    private Integer pageSize;
    private Integer pageNumber;

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getOperName() {
        return this.operName;
    }

    public void setOperName(final String operName) {
        this.operName = operName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLoginIp() {
        return this.loginIp;
    }

    public void setLoginIp(final String loginIp) {
        this.loginIp = loginIp;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(final String operation) {
        this.operation = operation;
    }

    public Integer getOperUserId() {
        return this.operUserId;
    }

    public void setOperUserId(final Integer operUserId) {
        this.operUserId = operUserId;
    }

    public String getLogType() {
        return this.logType;
    }

    public void setLogType(final String logType) {
        this.logType = logType;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public Integer getStstate() {
        return this.ststate;
    }

    public void setStstate(final Integer ststate) {
        this.ststate = ststate;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(final String createtime) {
        this.createtime = createtime;
    }
}
