package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model;

public class MapPlan
{
    private Integer ID;
    private String pname;
    private Integer parentId;
    private Integer lev;
    private String createtime;
    private String stgroup;
    private Integer ststate;
    private Integer storder;
    private String sttype;

    public String getSttype() {
        return this.sttype;
    }

    public void setSttype(final String sttype) {
        this.sttype = sttype;
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

    public Integer getID() {
        return this.ID;
    }

    public void setID(final Integer iD) {
        this.ID = iD;
    }

    public String getPname() {
        return this.pname;
    }

    public void setPname(final String pname) {
        this.pname = pname;
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

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(final String createtime) {
        this.createtime = createtime;
    }

    public String getStgroup() {
        return this.stgroup;
    }

    public void setStgroup(final String stgroup) {
        this.stgroup = stgroup;
    }
}
