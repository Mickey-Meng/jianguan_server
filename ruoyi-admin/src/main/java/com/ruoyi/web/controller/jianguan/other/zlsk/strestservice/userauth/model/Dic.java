package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model;

import java.util.*;

public class Dic
{
    private Integer id;
    private String name;
    private Integer parentid;
    private String key;
    private String value;
    private Date sttime;
    private Integer ststate;
    private Integer storder;

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = ((name == null) ? null : name.trim());
    }

    public Integer getParentid() {
        return this.parentid;
    }

    public void setParentid(final Integer parentid) {
        this.parentid = parentid;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = ((key == null) ? null : key.trim());
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = ((value == null) ? null : value.trim());
    }

    public Date getSttime() {
        return this.sttime;
    }

    public void setSttime(final Date sttime) {
        this.sttime = sttime;
    }

    public Integer getStstate() {
        return this.ststate;
    }

    public void setStstate(final Integer ststate) {
        this.ststate = ststate;
    }

    public Integer getStorder() {
        return this.storder;
    }

    public void setStorder(final Integer storder) {
        this.storder = storder;
    }
}
