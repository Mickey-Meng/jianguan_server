package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model;

import java.sql.*;

public class OperationLog
{
    private Integer id;
    private Integer userid;
    private String address;
    private String ip;
    private String Operation;
    private Integer pageSize;
    private Integer pageNum;
    private Timestamp STTIME;

    public Integer getId() {
        return this.id;
    }

    public String getOperation() {
        return this.Operation;
    }

    public void setOperation(final String operation) {
        this.Operation = operation;
    }

    public Timestamp getSTTIME() {
        return this.STTIME;
    }

    public void setDate(final Timestamp STTIME) {
        this.STTIME = STTIME;
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
}
