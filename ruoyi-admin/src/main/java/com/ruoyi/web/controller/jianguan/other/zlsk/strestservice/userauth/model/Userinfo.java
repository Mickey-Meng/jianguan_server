package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model;

public class Userinfo
{
    private Integer userid;
    private String post;
    private String phone;
    private String email;
    private String userimage;
    private String remark;
    private String sttime;
    private Integer ststate;
    private Integer storder;

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(final Integer userid) {
        this.userid = userid;
    }

    public String getPost() {
        return this.post;
    }

    public void setPost(final String post) {
        this.post = post;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getUserimage() {
        return this.userimage;
    }

    public void setUserimage(final String userimage) {
        this.userimage = userimage;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
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
