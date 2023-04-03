package com.ruoyi.czjg.zjrw.domain.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/15 4:05 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class SubMitDelayEvent {
    @ApiModelProperty(value = "事件id",required=true)
    private Integer eventid;
    @ApiModelProperty(value = "延期天数",required=true)
    private Integer delay;
    @ApiModelProperty(value = "延期原因",required=true)
    private String reason;

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
