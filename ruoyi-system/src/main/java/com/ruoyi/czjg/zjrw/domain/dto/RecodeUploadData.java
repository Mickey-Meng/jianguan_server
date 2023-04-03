package com.ruoyi.czjg.zjrw.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/10 1:56 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class RecodeUploadData {

    @ApiModelProperty(value = "构件类型name",required=true)
    private String conponentname;


    public String getConponentname() {
        return conponentname;
    }

    public void setConponentname(String conponentname) {
        this.conponentname = conponentname;
    }

    @ApiModelProperty(value = "构件id",required=true)
    private Integer conpoentid;

    @ApiModelProperty(value = "构件所属项目",required=true)
    private String projectcode;

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    @ApiModelProperty(value = "构件类型",required=true)
    private String conponenttype;

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
    }

    //构件工序
    @ApiModelProperty(value = "工序id",required=true)
    private Integer produceid;

    @ApiModelProperty(value = "上传人id",required=true)
    private Integer updateid;

    public Integer getUpdateid() {
        return updateid;
    }

    public void setUpdateid(Integer updateid) {
        this.updateid = updateid;
    }

    @ApiModelProperty(value = "工序名称",required=true)
    private String produceidname;

    public String getProduceidname() {
        return produceidname;
    }

    public void setProduceidname(String produceidname) {
        this.produceidname = produceidname;
    }

    //记录id
    @ApiModelProperty(value = "记录id，如果是填报，则不传",required=true)
    private Integer recodeid;

    @ApiModelProperty(value = "指定报检人id",required=true)
    private Integer checkid;


    @ApiModelProperty(value = "施工上传举牌照片",required=false)
    private String accrecodeurl;

    @ApiModelProperty(value = "监理上传举牌照片",required=false)
    private String testurl;

    @ApiModelProperty(value = "关键数据",required=false)
    private String keydata;
    @ApiModelProperty(value = "上传人姓名",required=false)
    private String updateusername;
    @ApiModelProperty(value = "监理姓名",required=false)
    private String checkusername;

    @ApiModelProperty(value = "附件",required=false)
    private String remark;
    @ApiModelProperty(value = "监理审核上传附件",required = false)
    private String standbyrecode;

    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "填报时间，web端补录使用，app端不用",required=true)
    private Date uploadtime;

    @ApiModelProperty(value = "项目标段id")
    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getUpdateusername() {
        return updateusername;
    }

    public void setUpdateusername(String updateusername) {
        this.updateusername = updateusername;
    }

    public String getCheckusername() {
        return checkusername;
    }

    public void setCheckusername(String checkusername) {
        this.checkusername = checkusername;
    }

    public String getAccrecodeurl() {
        return accrecodeurl;
    }

    public void setAccrecodeurl(String accrecodeurl) {
        this.accrecodeurl = accrecodeurl;
    }

    public String getTesturl() {
        return testurl;
    }

    public void setTesturl(String testurl) {
        this.testurl = testurl;
    }

    public String getKeydata() {
        return keydata;
    }

    public void setKeydata(String keydata) {
        this.keydata = keydata;
    }

    public String getStandbyrecode() {
        return standbyrecode;
    }

    public void setStandbyrecode(String standbyrecode) {
        this.standbyrecode = standbyrecode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCheckid() {
        return checkid;
    }

    public void setCheckid(Integer checkid) {
        this.checkid = checkid;
    }

    //工序中的哪一环
    /*
    1. 填报
    2. 自检
    3. 专检
    4. 验收报告
    5. 检测报告
    6. 关键数据
    7. 旁站记录
     */

    public Integer getRecodeid() {
        return recodeid;
    }

    public void setRecodeid(Integer recodeid) {
        this.recodeid = recodeid;
    }

    private Integer type;
    private String url;

    public Integer getConpoentid() {
        return conpoentid;
    }

    public void setConpoentid(Integer conpoentid) {
        this.conpoentid = conpoentid;
    }

    public Integer getProduceid() {
        return produceid;
    }

    public void setProduceid(Integer produceid) {
        this.produceid = produceid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
