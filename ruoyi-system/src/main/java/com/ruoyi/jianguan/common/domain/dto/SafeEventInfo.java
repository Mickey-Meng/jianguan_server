package com.ruoyi.jianguan.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.jianguan.common.domain.entity.ZjSafeEvent;

import java.util.Date;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/1 17:34
 * @description :
 **/
public class SafeEventInfo {


    private ZjSafeEvent zjSafeEvent;

    private Integer isOverdue;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date overdueTime;

    public ZjSafeEvent getZjSafeEvent() {
        return zjSafeEvent;
    }

    public void setZjSafeEvent(ZjSafeEvent zjSafeEvent) {
        this.zjSafeEvent = zjSafeEvent;
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
        return "SafeEventInfo{" +
                "zjSafeEvent=" + zjSafeEvent +
                ", isOverdue=" + isOverdue +
                ", overdueTime=" + overdueTime +
                '}';
    }
}
