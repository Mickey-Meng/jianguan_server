package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model;

public class UserOrganize
{
    private Integer id;
    private String userGroupName;
    private String userGroupCode;
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

    public String getUserGroupName() {
        return this.userGroupName;
    }

    public void setUserGroupName(final String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getUserGroupCode() {
        return this.userGroupCode;
    }

    public void setUserGroupCode(final String userGroupCode) {
        this.userGroupCode = userGroupCode;
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
