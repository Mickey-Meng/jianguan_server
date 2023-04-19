package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model;

public class Notice
{
    private Integer id;
    private String title;
    private String content;
    private String linkPath;
    private Integer createrId;
    private String createtime;
    private Integer ststate;
    private Integer pageSize;
    private Integer pageNumber;

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getLinkPath() {
        return this.linkPath;
    }

    public void setLinkPath(final String linkPath) {
        this.linkPath = linkPath;
    }

    public Integer getCreaterId() {
        return this.createrId;
    }

    public void setCreaterId(final Integer createrId) {
        this.createrId = createrId;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(final String createtime) {
        this.createtime = createtime;
    }

    public Integer getStstate() {
        return this.ststate;
    }

    public void setStstate(final Integer ststate) {
        this.ststate = ststate;
    }
}
