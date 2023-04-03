package com.ruoyi.czjg.zjrw.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class RecodeQueryData {

    //工程类型
    private String projectType;

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    //类型
    private String type;
    //单位
    private List<String> list;
    //构建编码
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date sttime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endtime;

    //新增 通过工区来查
    private Integer gongquid;

    //以下时间为用户在搜索相同时间时备用字段（如搜索   2022-03-01  ~   2022-03-01）
    private String newStime;
    private String newEndTime;

    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getGongquid() {
        return gongquid;
    }

    public void setGongquid(Integer gongquid) {
        this.gongquid = gongquid;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getNewStime() {
        return newStime;
    }

    public void setNewStime(String newStime) {
        this.newStime = newStime;
    }

    public String getNewEndTime() {
        return newEndTime;
    }

    public void setNewEndTime(String newEndTime) {
        this.newEndTime = newEndTime;
    }
}
