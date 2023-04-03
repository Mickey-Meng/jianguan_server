package com.ruoyi.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/26 15:43
 * @Version : 1.0
 * @Description :
 **/
public class ZjPersonChangeFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "人员变更id")
    private Integer gid;
    @ApiModelProperty(value = "文件id(字符串,base64)")
    private String fileId;
    @ApiModelProperty(value = "文件名")
    private String fileName;
    @ApiModelProperty(value = "上传时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date uploadTime;
    @ApiModelProperty(value = "上传人id")
    private Integer uploadPersonId;
    @ApiModelProperty(value = "上传人名")
    private String uploadPerson;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getUploadPersonId() {
        return uploadPersonId;
    }

    public void setUploadPersonId(Integer uploadPersonId) {
        this.uploadPersonId = uploadPersonId;
    }

    public String getUploadPerson() {
        return uploadPerson;
    }

    public void setUploadPerson(String uploadPerson) {
        this.uploadPerson = uploadPerson;
    }

    @Override
    public String toString() {
        return "ZjPersonChangeFile{" +
                "id=" + id +
                ", gid=" + gid +
                ", fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", uploadTime=" + uploadTime +
                ", uploadPersonId=" + uploadPersonId +
                ", uploadPerson=" + uploadPerson +
                '}';
    }
}
