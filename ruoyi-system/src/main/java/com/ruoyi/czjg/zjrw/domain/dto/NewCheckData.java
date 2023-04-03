package com.ruoyi.czjg.zjrw.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class NewCheckData {

    @ApiModelProperty(value = "单位工程code(如: QL06Y)")
    private String projectid;
    private String conponenttype;
    private String code;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date sttime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endtime;
    @ApiModelProperty(value = "项目标段id")
    private Integer projectId;

    private List<String> projectCodes;

    public List<String> getProjectCodes() {
        return projectCodes;
    }

    public void setProjectCodes(List<String> projectCodes) {
        this.projectCodes = projectCodes;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
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
}
