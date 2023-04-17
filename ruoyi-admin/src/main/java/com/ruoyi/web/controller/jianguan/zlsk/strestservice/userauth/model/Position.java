package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model;

public class Position
{
    private Integer id;
    private String positionName;
    private String positionCode;
    private String positionGrades;
    private Integer parentId;
    private Integer lev;
    private String description;
    private String createtime;
    private Integer storder;
    private Integer ststate;

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return this.positionName;
    }

    public void setPositionName(final String positionName) {
        this.positionName = positionName;
    }

    public String getPositionCode() {
        return this.positionCode;
    }

    public void setPositionCode(final String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionGrades() {
        return this.positionGrades;
    }

    public void setPositionGrades(final String positionGrades) {
        this.positionGrades = positionGrades;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(final Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLev() {
        return this.lev;
    }

    public void setLev(final Integer lev) {
        this.lev = lev;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(final String createtime) {
        this.createtime = createtime;
    }

    public Integer getStorder() {
        return this.storder;
    }

    public void setStorder(final Integer storder) {
        this.storder = storder;
    }

    public Integer getStstate() {
        return this.ststate;
    }

    public void setStstate(final Integer ststate) {
        this.ststate = ststate;
    }
}
