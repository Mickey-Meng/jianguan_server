package com.ruoyi.common.core.domain.dto;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/26 11:54 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class ConponentScheuleDto {
    private Integer id;
    private Integer conponentId;
    private String mouldId;
    private Integer status;

    public ConponentScheuleDto(Integer id, Integer conponentId, String mouldId, Integer status) {
        this.id = id;
        this.conponentId = conponentId;
        this.mouldId = mouldId;
        this.status = status;
    }

    public ConponentScheuleDto(){


    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConponentId() {
        return conponentId;
    }

    public void setConponentId(Integer conponentId) {
        this.conponentId = conponentId;
    }

    public String getMouldId() {
        return mouldId;
    }

    public void setMouldId(String mouldId) {
        this.mouldId = mouldId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
