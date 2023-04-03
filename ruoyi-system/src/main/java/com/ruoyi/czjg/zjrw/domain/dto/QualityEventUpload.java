package com.ruoyi.czjg.zjrw.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/15 2:56 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class QualityEventUpload {

    @ApiModelProperty(value = "安全大项id",required=true)
    private Integer qualityfirst;
    @ApiModelProperty(value = "安全小项id",required=true)
    private Integer qualitysecond;
    @ApiModelProperty(value = "上传人id",required=true)
    private Integer uploadid;
    @ApiModelProperty(value = "上传人姓名",required=true)
    private String uploadname;
    @ApiModelProperty(value = "上传文件地址",required=true)
    private String uploadurl;
    @ApiModelProperty(value = "上传人的备注",required=true)
    private String uploadremark;

    @ApiModelProperty(value = "通知处理人id",required=true)
    private Integer modifyid;
    @ApiModelProperty(value = "通知处理人的姓名",required=true)
    private String modifyname;
    @ApiModelProperty(value = "限期整改时间n天后",required=true)
    private Integer modifydate;
    @ApiModelProperty(value = "上传时间",required=false)
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date uploadtime ;

    @ApiModelProperty(value = "工区id",required=true)
    private Integer gongquid;

    @ApiModelProperty(value = "单位工程id",required=true)
    private Integer singleProjectId;
    @ApiModelProperty(value = "项目标段id",required=true)
    private Integer projectId;

    @ApiModelProperty(value = "安全大项名称",required=true)
    private String qualityfirstname;
    @ApiModelProperty(value = "安全小项名称",required=true)
    private String qualitysecondname;


    public Integer getSingleProjectId() {
        return singleProjectId;
    }

    public void setSingleProjectId(Integer singleProjectId) {
        this.singleProjectId = singleProjectId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getQualityfirst() {
        return qualityfirst;
    }

    public void setQualityfirst(Integer qualityfirst) {
        this.qualityfirst = qualityfirst;
    }

    public Integer getQualitysecond() {
        return qualitysecond;
    }

    public void setQualitysecond(Integer qualitysecond) {
        this.qualitysecond = qualitysecond;
    }

    public String getQualityfirstname() {
        return qualityfirstname;
    }

    public void setQualityfirstname(String qualityfirstname) {
        this.qualityfirstname = qualityfirstname;
    }

    public String getQualitysecondname() {
        return qualitysecondname;
    }

    public void setQualitysecondname(String qualitysecondname) {
        this.qualitysecondname = qualitysecondname;
    }

    public Integer getGongquid() {
        return gongquid;
    }

    public void setGongquid(Integer gongquid) {
        this.gongquid = gongquid;
    }



    public Integer getUploadid() {
        return uploadid;
    }

    public void setUploadid(Integer uploadid) {
        this.uploadid = uploadid;
    }

    public String getUploadname() {
        return uploadname;
    }

    public void setUploadname(String uploadname) {
        this.uploadname = uploadname;
    }

    public String getUploadurl() {
        return uploadurl;
    }

    public void setUploadurl(String uploadurl) {
        this.uploadurl = uploadurl;
    }

    public String getUploadremark() {
        return uploadremark;
    }

    public void setUploadremark(String uploadremark) {
        this.uploadremark = uploadremark;
    }

    public Integer getModifyid() {
        return modifyid;
    }

    public void setModifyid(Integer modifyid) {
        this.modifyid = modifyid;
    }

    public String getModifyname() {
        return modifyname;
    }

    public void setModifyname(String modifyname) {
        this.modifyname = modifyname;
    }

    public Integer getModifydate() {
        return modifydate;
    }

    public void setModifydate(Integer modifydate) {
        this.modifydate = modifydate;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }
}
