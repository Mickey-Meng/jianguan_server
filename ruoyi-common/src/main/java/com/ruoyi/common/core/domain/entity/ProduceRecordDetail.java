package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.core.domain.model.Head;

import java.util.*;

public class ProduceRecordDetail {

    private int conponentid;

    private String produceRangee;

    private Integer status;

    private Date time;

    public int getConponentid() {
        return conponentid;
    }

    public void setConponentid(int conponentid) {
        this.conponentid = conponentid;
    }




    public String getProduceRangee() {
        return produceRangee;
    }

    public void setProduceRangee(String produceRangee) {
        this.produceRangee = produceRangee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
