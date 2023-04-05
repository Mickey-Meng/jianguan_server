package com.ruoyi.jianguan.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : chenzhiwei
 * @Date : Create file in 2022/4/3 10:12
 * @Version : 1.0
 * @Description :
 **/
public class ComponentProgressTimes implements Serializable {

    /**
     * 构件计划开始时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date plansttime;

    /**
     * 构件计划完成时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date planendtime;

    /**
     * 构件实际开始时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date actulsttime;

    /**
     * 构件实际完成时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date actulendtime;

    @ApiModelProperty(value = "新构件编码")
    private String conponentcode;

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

    public String getConponentcode() {
        return conponentcode;
    }

    public void setConponentcode(String conponentcode) {
        this.conponentcode = conponentcode;
    }

    @Override
    public String toString() {
        return "ComponentProgressTimes{" +
                "plansttime=" + plansttime +
                ", planendtime=" + planendtime +
                ", actulsttime=" + actulsttime +
                ", actulendtime=" + actulendtime +
                ", conponentcode='" + conponentcode + '\'' +
                '}';
    }
}
