package com.ruoyi.jianguan.common.domain.dto;

import java.util.Date;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/15 10:19
 * @description :
 **/
public class GroupProjectDTO {

    /**
     * 工区id
     */
    private Integer groupid;

    /**
     * 项目id
     */
    private String projectid;

    /**
     * 工区父级id
     */
    private Integer parentid;

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

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
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

    @Override
    public String toString() {
        return "GroupProjectDTO{" +
                "groupid=" + groupid +
                ", projectid='" + projectid + '\'' +
                ", parentid=" + parentid +
                ", sttime=" + sttime +
                ", ststate=" + ststate +
                ", storder=" + storder +
                ", projectname='" + projectname + '\'' +
                ", projecttype='" + projecttype + '\'' +
                '}';
    }
}
