package com.ruoyi.jianguan.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.jianguan.common.domain.entity.ZjQualityEvent;

import java.util.Date;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/1 17:34
 * @description :
 **/
public class QualityEventInfo {


    private ZjQualityEvent zjQualityEvent;

    private Integer isOverdue;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date overdueTime;

    public ZjQualityEvent getZjQualityEvent() {
        return zjQualityEvent;
    }

    public void setZjQualityEvent(ZjQualityEvent zjQualityEvent) {
        this.zjQualityEvent = zjQualityEvent;
    }

    public Integer getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Integer isOverdue) {
        this.isOverdue = isOverdue;
    }

    public Date getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    @Override
    public String toString() {
        return "QualityEventInfo{" +
                "zjQualityEvent=" + zjQualityEvent +
                ", isOverdue=" + isOverdue +
                ", overdueTime=" + overdueTime +
                '}';
    }
}
