package com.ruoyi.jianguan.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/15 4:42 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class SubmitDealWithSafeEvent {
    @ApiModelProperty(value = "事件id",required=true)
    private Integer eventid;
    @ApiModelProperty(value = "整改备注",required=true)
    private String modifyremark;
    @ApiModelProperty(value = "整改图片",required=true)
    private String modifyurl;

    @ApiModelProperty(value = "整改时间",required=false)
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifydate ;

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getModifyremark() {
        return modifyremark;
    }

    public void setModifyremark(String modifyremark) {
        this.modifyremark = modifyremark;
    }

    public String getModifyurl() {
        return modifyurl;
    }

    public void setModifyurl(String modifyurl) {
        this.modifyurl = modifyurl;
    }
}
