package com.ruoyi.jianguan.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/21 2:58 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class ProgressData {

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "填报时间，web端补录使用，app端不用",required=false)
    private Date plansttime;


    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "填报时间，web端补录使用，app端不用",required=false)
    private Date planendtime;


    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "填报时间，web端补录使用，app端不用",required=false)
    private Date actulsttime;


    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "填报时间，web端补录使用，app端不用",required=false)
    private Date actulendtime;


    private List<Integer>  conponentids;


    public Date getPlansttime() {
        return plansttime;
    }

    public void setPlansttime(Date plansttime) {
        this.plansttime = plansttime;
    }

    public Date getPlanendtime() {
        return planendtime;
    }

    public void setPlanendtime(Date planendtime) {
        this.planendtime = planendtime;
    }

    public Date getActulsttime() {
        return actulsttime;
    }

    public void setActulsttime(Date actulsttime) {
        this.actulsttime = actulsttime;
    }

    public Date getActulendtime() {
        return actulendtime;
    }

    public void setActulendtime(Date actulendtime) {
        this.actulendtime = actulendtime;
    }

    public List<Integer> getConponentids() {
        return conponentids;
    }

    public void setConponentids(List<Integer> conponentids) {
        this.conponentids = conponentids;
    }
}
