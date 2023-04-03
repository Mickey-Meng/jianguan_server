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
public class SafeEventUpload {

    @ApiModelProperty(value = "安全大项id",required=true)
    private Integer safefirst;
    @ApiModelProperty(value = "安全小项id",required=true)
    private Integer safesecond;
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
    @ApiModelProperty(value = "项目id", required = true)
    private Integer projectId;

    @ApiModelProperty(value = "安全大项名称",required=true)
    private String safefirstname;
    @ApiModelProperty(value = "安全小项名称",required=true)
    private String safesecondname;


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSingleProjectId() {
        return singleProjectId;
    }

    public void setSingleProjectId(Integer singleProjectId) {
        this.singleProjectId = singleProjectId;
    }

    public String getSafefirstname() {
        return safefirstname;
    }

    public void setSafefirstname(String safefirstname) {
        this.safefirstname = safefirstname;
    }

    public String getSafesecondname() {
        return safesecondname;
    }

    public void setSafesecondname(String safesecondname) {
        this.safesecondname = safesecondname;
    }

    public Integer getGongquid() {
        return gongquid;
    }

    public void setGongquid(Integer gongquid) {
        this.gongquid = gongquid;
    }

    public Integer getSafefirst() {
        return safefirst;
    }

    public void setSafefirst(Integer safefirst) {
        this.safefirst = safefirst;
    }

    public Integer getSafesecond() {
        return safesecond;
    }

    public void setSafesecond(Integer safesecond) {
        this.safesecond = safesecond;
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

    @Override
    public String toString() {
        return "SafeEventUpload{" +
                "safefirst=" + safefirst +
                ", safesecond=" + safesecond +
                ", uploadid=" + uploadid +
                ", uploadname='" + uploadname + '\'' +
                ", uploadurl='" + uploadurl + '\'' +
                ", uploadremark='" + uploadremark + '\'' +
                ", modifyid=" + modifyid +
                ", modifyname='" + modifyname + '\'' +
                ", modifydate=" + modifydate +
                ", uploadtime=" + uploadtime +
                ", gongquid=" + gongquid +
                ", singleProjectId=" + singleProjectId +
                ", safefirstname='" + safefirstname + '\'' +
                ", safesecondname='" + safesecondname + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
