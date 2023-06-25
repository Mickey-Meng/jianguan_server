package com.ruoyi.common.core.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @mm
 * @author
 * 用户列表
 */
public class SsFUsersDTO implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;


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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SsFUsersDTO other = (SsFUsersDTO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUsercode() == null ? other.getUsercode() == null : this.getUsercode().equals(other.getUsercode()))
            && (this.getSttime() == null ? other.getSttime() == null : this.getSttime().equals(other.getSttime()))
            && (this.getStstate() == null ? other.getStstate() == null : this.getStstate().equals(other.getStstate()))
            && (this.getStorder() == null ? other.getStorder() == null : this.getStorder().equals(other.getStorder()))
            && (this.getUgid() == null ? other.getUgid() == null : this.getUgid().equals(other.getUgid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUsercode() == null) ? 0 : getUsercode().hashCode());
        result = prime * result + ((getSttime() == null) ? 0 : getSttime().hashCode());
        result = prime * result + ((getStstate() == null) ? 0 : getStstate().hashCode());
        result = prime * result + ((getStorder() == null) ? 0 : getStorder().hashCode());
        result = prime * result + ((getUgid() == null) ? 0 : getUgid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", name=").append(name);
        sb.append(", usercode=").append(usercode);
        sb.append(", sttime=").append(sttime);
        sb.append(", ststate=").append(ststate);
        sb.append(", storder=").append(storder);
        sb.append(", ugid=").append(ugid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
