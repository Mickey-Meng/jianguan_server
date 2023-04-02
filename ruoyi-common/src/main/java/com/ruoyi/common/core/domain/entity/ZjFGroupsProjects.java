package com.ruoyi.common.core.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 *
 */
public class ZjFGroupsProjects implements Serializable {
    /**
     * 工区id
     */
    private Integer groupid;

    /**
     * 项目id
     */
    private String projectid;

    /**
     * 记录插入时间
     */
    private Date sttime;

    /**
     * 状态0，1
     */
    private Integer ststate;

    /**
     * 排序
     */
    private Integer storder;

    /**
     * 项目名称
     */
    private String projectname;

    /**
     * 项目类型
     */
    private String projecttype;

    /**
     * 部门id
     */
    private Integer departmentid;

    private static final long serialVersionUID = 1L;

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
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

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
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
        ZjFGroupsProjects other = (ZjFGroupsProjects) that;
        return (this.getGroupid() == null ? other.getGroupid() == null : this.getGroupid().equals(other.getGroupid()))
                && (this.getProjectid() == null ? other.getProjectid() == null : this.getProjectid().equals(other.getProjectid()))
                && (this.getSttime() == null ? other.getSttime() == null : this.getSttime().equals(other.getSttime()))
                && (this.getStstate() == null ? other.getStstate() == null : this.getStstate().equals(other.getStstate()))
                && (this.getStorder() == null ? other.getStorder() == null : this.getStorder().equals(other.getStorder()))
                && (this.getProjectname() == null ? other.getProjectname() == null : this.getProjectname().equals(other.getProjectname()))
                && (this.getDepartmentid() == null ? other.getDepartmentid() == null : this.getDepartmentid().equals(other.getDepartmentid()))
                && (this.getProjecttype() == null ? other.getProjecttype() == null : this.getProjecttype().equals(other.getProjecttype()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGroupid() == null) ? 0 : getGroupid().hashCode());
        result = prime * result + ((getProjectid() == null) ? 0 : getProjectid().hashCode());
        result = prime * result + ((getSttime() == null) ? 0 : getSttime().hashCode());
        result = prime * result + ((getStstate() == null) ? 0 : getStstate().hashCode());
        result = prime * result + ((getStorder() == null) ? 0 : getStorder().hashCode());
        result = prime * result + ((getProjectname() == null) ? 0 : getProjectname().hashCode());
        result = prime * result + ((getProjecttype() == null) ? 0 : getProjecttype().hashCode());
        result = prime * result + ((getDepartmentid() == null) ? 0 : getDepartmentid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupid=").append(groupid);
        sb.append(", projectid=").append(projectid);
        sb.append(", sttime=").append(sttime);
        sb.append(", ststate=").append(ststate);
        sb.append(", storder=").append(storder);
        sb.append(", projectname=").append(projectname);
        sb.append(", projecttype=").append(projecttype);
        sb.append(", departmentid=").append(departmentid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
