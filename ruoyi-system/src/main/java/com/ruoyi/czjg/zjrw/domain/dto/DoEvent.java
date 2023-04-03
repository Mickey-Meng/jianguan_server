package com.ruoyi.czjg.zjrw.domain.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/15 4:25 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class DoEvent {
    @ApiModelProperty(value = "事件id",required=true)
    private Integer eventid;
    @ApiModelProperty(value = "审批结果 1通过，2不通过",required=true)
    private Integer result;
    @ApiModelProperty(value = "通过与通过原因，不通过是必填原因，通过是可不填",required=true)
    private String reason;
    @ApiModelProperty(value = "需要抄送的用户id(字符串表示,','隔开)")
    private String copyUsers;

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCopyUsers() {
        return copyUsers;
    }

    public void setCopyUsers(String copyUsers) {
        this.copyUsers = copyUsers;
    }
}
