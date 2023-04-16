package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model;

public class Groups
{
    private Integer id;
    private String name;
    private String code;
    private Integer parentid;
    private String type;
    private Integer grouplevel;
    private String visible;
    private String sttime;
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
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Integer getParentid() {
        return this.parentid;
    }

    public void setParentid(final Integer parentid) {
        this.parentid = parentid;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Integer getGrouplevel() {
        return this.grouplevel;
    }

    public void setGrouplevel(final Integer grouplevel) {
        this.grouplevel = grouplevel;
    }

    public String getVisible() {
        return this.visible;
    }

    public void setVisible(final String visible) {
        this.visible = visible;
    }

    public String getSttime() {
        return this.sttime;
    }

    public void setSttime(final String sttime) {
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
