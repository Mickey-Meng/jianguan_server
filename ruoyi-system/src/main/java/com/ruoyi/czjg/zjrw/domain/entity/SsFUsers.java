package com.ruoyi.czjg.zjrw.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author
 * 用户列表
 */
public class SsFUsers implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户编号
     */
    private String usercode;

    /**
     * 插入记录的时间
     */
    private Date sttime;

    /**
     * 状态: -1: 删除, 0: 冻结, 1: 正常
     */
    private Integer ststate;

    /**
     * 顺序
     */
    private Integer storder;

    /**
     * 用户组表id
     */
    private Integer ugid;

    private Date updatetime;


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    public Integer getStstate() {
        return ststate;
    }

    public void setStstate(Integer ststate) {
        this.ststate = ststate;
    }

    public Integer getStorder() {
        return storder;
    }

    public void setStorder(Integer storder) {
        this.storder = storder;
    }

    public Integer getUgid() {
        return ugid;
    }

    public void setUgid(Integer ugid) {
        this.ugid = ugid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SsFUsers ssFUsers = (SsFUsers) o;
        return Objects.equals(id, ssFUsers.id) &&
                Objects.equals(username, ssFUsers.username) &&
                Objects.equals(pwd, ssFUsers.pwd) &&
                Objects.equals(name, ssFUsers.name) &&
                Objects.equals(usercode, ssFUsers.usercode) &&
                Objects.equals(sttime, ssFUsers.sttime) &&
                Objects.equals(ststate, ssFUsers.ststate) &&
                Objects.equals(storder, ssFUsers.storder) &&
                Objects.equals(ugid, ssFUsers.ugid) &&
                Objects.equals(updatetime, ssFUsers.updatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, pwd, name, usercode, sttime, ststate, storder, ugid, updatetime);
    }

    @Override
    public String toString() {
        return "SsFUsers{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", usercode='" + usercode + '\'' +
                ", sttime=" + sttime +
                ", ststate=" + ststate +
                ", storder=" + storder +
                ", ugid=" + ugid +
                ", updatetime=" + updatetime +
                '}';
    }
}
