package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model;

public class Gyy
{
    private Integer id;
    private String funName;
    private String funKey;
    private Integer parentId;
    private Integer ststate;
    private String createtime;

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFunName() {
        return this.funName;
    }

    public void setFunName(final String funName) {
        this.funName = funName;
    }

    public String getFunKey() {
        return this.funKey;
    }

    public void setFunKey(final String funKey) {
        this.funKey = funKey;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(final Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getStstate() {
        return this.ststate;
    }

    public void setStstate(final Integer ststate) {
        this.ststate = ststate;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(final String createtime) {
        this.createtime = createtime;
    }
}
