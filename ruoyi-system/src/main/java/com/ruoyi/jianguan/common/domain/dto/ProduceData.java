package com.ruoyi.jianguan.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProduceData {
    private  int produceid;
    private  int produceAndRecodeId;
    /**
     * 确认结果待确认0，驳回2，确认1
     */
    private Integer checkresult;
    private int sort;
    private String name;
    private int recordid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finish;
    private Integer status;


    public int getProduceid() {
        return produceid;
    }

    public void setProduceid(int produceid) {
        this.produceid = produceid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public int getProduceAndRecodeId() {
        return produceAndRecodeId;
    }

    public void setProduceAndRecodeId(int produceAndRecodeId) {
        this.produceAndRecodeId = produceAndRecodeId;
    }

    public Integer getCheckresult() {
        return checkresult;
    }

    public void setCheckresult(Integer checkresult) {
        this.checkresult = checkresult;
    }
}
