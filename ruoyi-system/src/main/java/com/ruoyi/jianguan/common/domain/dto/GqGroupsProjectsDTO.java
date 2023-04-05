package com.ruoyi.jianguan.common.domain.dto;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/2 16:56
 * @description : 工区下面所拥有的项目
 **/
public class GqGroupsProjectsDTO {

    private Integer groupid;

    private String projectid;

    private String projectname;


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

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }
}
