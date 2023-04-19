package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.map.model;

public class MapConfigRecord
{
    private Integer ID;
    private String serverName;
    private String url;
    private String description;
    private String type;
    private Integer visiable;
    private String attrbuites;
    private String createtime;
    private Integer ststate;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer mpid;
    private Integer cusId;
    private String img;

    public String getImg() {
        return this.img;
    }

    public void setImg(final String img) {
        this.img = img;
    }

    public Integer getCusId() {
        return this.cusId;
    }

    public void setCusId(final Integer cusId) {
        this.cusId = cusId;
    }

    public Integer getMpid() {
        return this.mpid;
    }

    public void setMpid(final Integer mpid) {
        this.mpid = mpid;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
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

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(final String serverName) {
        this.serverName = serverName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Integer getVisiable() {
        return this.visiable;
    }

    public void setVisiable(final Integer visiable) {
        this.visiable = visiable;
    }

    public String getAttrbuites() {
        return this.attrbuites;
    }

    public void setAttrbuites(final String attrbuites) {
        this.attrbuites = attrbuites;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(final String createtime) {
        this.createtime = createtime;
    }
}
