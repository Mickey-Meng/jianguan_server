package com.ruoyi.czjg.zjrw.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProduceData {
    private  int produceid;
    private int sort;
    private String name;
    private int recordid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finish;
    private int status;


    public int getProduceid() {
        return produceid;
    }

    public void setProduceid(int produceid) {
        this.produceid = produceid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecordid() {
        return recordid;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }
}
