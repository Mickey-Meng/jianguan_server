package com.ruoyi.czjg.zjrw.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/12 16:22
 * @Version : 1.0
 * @Description :
 **/
public class SsFRoles implements Serializable {

    private Integer id;

    private String name;

    private String code;

    private Integer parentid;

    private Integer type;

    private Integer rolelevel;

    private String visible;

    private Date sttime;

    private Integer ststate;

    private Integer storder;

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

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRolelevel() {
        return rolelevel;
    }

    public void setRolelevel(Integer rolelevel) {
        this.rolelevel = rolelevel;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    public Integer getStstate() {
        return ststate;
    }

    public void setStstate(Integer ststate) {
        this.ststate = ststate;
    }

    public Integer getStorder() {
        return storder;
    }

    public void setStorder(Integer storder) {
        this.storder = storder;
    }

    @Override
    public String toString() {
        return "SsFRoles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", parentid=" + parentid +
                ", type=" + type +
                ", rolelevel=" + rolelevel +
                ", visible='" + visible + '\'' +
                ", sttime=" + sttime +
                ", ststate=" + ststate +
                ", storder=" + storder +
                '}';
    }
}
