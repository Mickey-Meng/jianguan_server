package com.ruoyi.jianguan.common.domain.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/12 11:42
 * @Version : 1.0
 * @Description :
 **/
public class SsFPersonGroups implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id = 0;
    @ApiModelProperty(value = "项目, 部门, 组织 名称")
    private String name;
    @ApiModelProperty(value = "项目, 部门, 组织 编码")
    private String code;
    @ApiModelProperty(value = "父级id")
    private Integer pid;
    @ApiModelProperty(value = "状态:0-无效,1-有效")
    private Integer status;
    @ApiModelProperty(value = "排序")
    private Integer order;

    @Override
    public String toString() {
        return "SsFPersonGroups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", pid=" + pid +
                ", status=" + status +
                ", order=" + order +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
