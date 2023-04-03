package com.ruoyi.czjg.zjrw.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/9 11:33 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class ConModel {



    private Integer id;
    private String conponentcode;
    private String w3;
    private String w2;
    private String w2code;
    private String conponenttypename;
    private String conponenttype;
    private String name;
    private Integer recodeid;
    private Integer produceid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date plansttime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date planendtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date actulsttime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date actulendtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date xxsttime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date xxendtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConponentcode() {
        return conponentcode;
    }

    public void setConponentcode(String conponentcode) {
        this.conponentcode = conponentcode;
    }

    public String getW3() {
        return w3;
    }

    public void setW3(String w3) {
        this.w3 = w3;
    }

    public String getConponenttypename() {
        return conponenttypename;
    }

    public void setConponenttypename(String conponenttypename) {
        this.conponenttypename = conponenttypename;
    }

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getW2() {
        return w2;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }

    public String getW2code() {
        return w2code;
    }

    public void setW2code(String w2code) {
        this.w2code = w2code;
    }



    public Integer getRecodeid() {
        return recodeid;
    }

    public void setRecodeid(Integer recodeid) {
        this.recodeid = recodeid;
    }

    public Integer getProduceid() {
        return produceid;
    }

    public void setProduceid(Integer produceid) {
        this.produceid = produceid;
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

    public Date getXxsttime() {
        return xxsttime;
    }

    public void setXxsttime(Date xxsttime) {
        this.xxsttime = xxsttime;
    }

    public Date getXxendtime() {
        return xxendtime;
    }

    public void setXxendtime(Date xxendtime) {
        this.xxendtime = xxendtime;
    }

    public String getGroup(){
        return

                this.id+"_"+this.conponentcode+"_"+
                this.w3+"_"+this.w2+"_"+this.w2code+"_"+
                this.conponenttypename+"_"+this.conponenttype+"_"+this.name+"_"+
                this.plansttime+"_"+this.planendtime+"_"+
                this.actulsttime+"_"+this.actulendtime+"_"+
                this.xxsttime+"_"+this.xxendtime;
    }
}
