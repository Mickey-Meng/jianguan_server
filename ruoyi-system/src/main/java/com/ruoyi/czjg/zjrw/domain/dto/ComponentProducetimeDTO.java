package com.ruoyi.czjg.zjrw.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/4 16:46
 * @description :
 **/
public class ComponentProducetimeDTO {

    private Integer id;

    private String mouldid;

    @ApiModelProperty(value = "构件进度状态值：0-未开工，1-开工中，2-已完成")
    private Integer status;

    private String layername;

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

    /**
     * 预计开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date plansttime;

    /**
     * 预计结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date planendtime;


    public String getMouldid() {
        return mouldid;
    }

    public void setMouldid(String mouldid) {
        this.mouldid = mouldid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLayername() {
        return layername;
    }

    public void setLayername(String layername) {
        this.layername = layername;
    }

    public Date getActulendtime() {
        return actulendtime;
    }

    public void setActulendtime(Date actulendtime) {
        this.actulendtime = actulendtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getActulsttime() {
        return actulsttime;
    }

    public void setActulsttime(Date actulsttime) {
        this.actulsttime = actulsttime;
    }

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

    @Override
    public String toString() {
        return "ComponentProducetimeDTO{" +
                "id=" + id +
                ", mouldid='" + mouldid + '\'' +
                ", status=" + status +
                ", layername='" + layername + '\'' +
                ", actulsttime=" + actulsttime +
                ", actulendtime=" + actulendtime +
                ", plansttime=" + plansttime +
                ", planendtime=" + planendtime +
                '}';
    }
}
