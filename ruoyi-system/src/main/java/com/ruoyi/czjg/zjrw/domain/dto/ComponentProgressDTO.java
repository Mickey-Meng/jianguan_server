package com.ruoyi.czjg.zjrw.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author : chenzhiwei
 * @Date : Create file in 2022/4/12 14:19
 * @Version : 1.0
 * @Description :
 **/
public class ComponentProgressDTO implements Serializable {

    @ApiModelProperty(value = "构件id", required = false)
    private Integer id;
    @ApiModelProperty(value = "模型id", required = false)
    private String mouldid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMouldid() {
        return mouldid;
    }

    public void setMouldid(String mouldid) {
        this.mouldid = mouldid;
    }

    @Override
    public String toString() {
        return "ComponentProgressDTO{" +
                "id=" + id +
                ", mouldid='" + mouldid + '\'' +
                '}';
    }
}
