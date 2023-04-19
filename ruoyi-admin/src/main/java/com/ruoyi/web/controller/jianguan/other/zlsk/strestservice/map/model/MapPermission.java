package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model;

public class MapPermission
{
    private Integer ID;
    private Integer mId;
    private Integer associateId;
    private Integer ptype;
    private String createtime;

    public Integer getID() {
        return this.ID;
    }

    public void setID(final Integer iD) {
        this.ID = iD;
    }

    public Integer getmId() {
        return this.mId;
    }

    public void setmId(final Integer mId) {
        this.mId = mId;
    }

    public Integer getAssociateId() {
        return this.associateId;
    }

    public void setAssociateId(final Integer associateId) {
        this.associateId = associateId;
    }

    public Integer getPtype() {
        return this.ptype;
    }

    public void setPtype(final Integer ptype) {
        this.ptype = ptype;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(final String createtime) {
        this.createtime = createtime;
    }
}
