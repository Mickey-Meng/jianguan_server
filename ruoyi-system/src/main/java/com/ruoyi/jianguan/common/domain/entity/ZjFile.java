package com.ruoyi.jianguan.common.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.dto.SaveDTO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 *
 */
public class ZjFile extends SaveDTO implements Serializable {


    private Integer id;

    private Integer uploadid;

    private String uploadusername;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date uploadtime;

    private String uploadtype;

    private String uploadname;

    private String changereason;

    /**
     * 标段名称
     */
    private String distiancename;

    private String changecontent;

    private Integer isutf;

    private String softname;

    private String opiontion;

    private String callunit;

    private String calladdr;

    private String fileurl;

    private Integer type;

    private String typeDesc;

    private Integer groupid;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date calltime;

    private Integer projectId;

    private String filename;

    private String fileType;

    /**
     * 审批状态
     */
    @ApiModelProperty(value = "审批状态 0：审批中 1: 审批完成 2:驳回")
    private Integer status;

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUploadid() {
        return uploadid;
    }

    public void setUploadid(Integer uploadid) {
        this.uploadid = uploadid;
    }

    public String getUploadusername() {
        return uploadusername;
    }

    public void setUploadusername(String uploadusername) {
        this.uploadusername = uploadusername;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getUploadtype() {
        return uploadtype;
    }

    public void setUploadtype(String uploadtype) {
        this.uploadtype = uploadtype;
    }

    public String getUploadname() {
        return uploadname;
    }

    public void setUploadname(String uploadname) {
        this.uploadname = uploadname;
    }

    public String getChangereason() {
        return changereason;
    }

    public void setChangereason(String changereason) {
        this.changereason = changereason;
    }

    public String getDistiancename() {
        return distiancename;
    }

    public void setDistiancename(String distiancename) {
        this.distiancename = distiancename;
    }

    public String getChangecontent() {
        return changecontent;
    }

    public void setChangecontent(String changecontent) {
        this.changecontent = changecontent;
    }

    public Integer getIsutf() {
        return isutf;
    }

    public void setIsutf(Integer isutf) {
        this.isutf = isutf;
    }

    public String getSoftname() {
        return softname;
    }

    public void setSoftname(String softname) {
        this.softname = softname;
    }

    public String getOpiontion() {
        return opiontion;
    }

    public void setOpiontion(String opiontion) {
        this.opiontion = opiontion;
    }

    public String getCallunit() {
        return callunit;
    }

    public void setCallunit(String callunit) {
        this.callunit = callunit;
    }

    public String getCalladdr() {
        return calladdr;
    }

    public void setCalladdr(String calladdr) {
        this.calladdr = calladdr;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCalltime() {
        return calltime;
    }

    public void setCalltime(Date calltime) {
        this.calltime = calltime;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uploadid=").append(uploadid);
        sb.append(", uploadusername=").append(uploadusername);
        sb.append(", uploadtime=").append(uploadtime);
        sb.append(", uploadtype=").append(uploadtype);
        sb.append(", uploadname=").append(uploadname);
        sb.append(", changereason=").append(changereason);
        sb.append(", distiancename=").append(distiancename);
        sb.append(", changecontent=").append(changecontent);
        sb.append(", isutf=").append(isutf);
        sb.append(", softname=").append(softname);
        sb.append(", opiontion=").append(opiontion);
        sb.append(", callunit=").append(callunit);
        sb.append(", calladdr=").append(calladdr);
        sb.append(", fileurl=").append(fileurl);
        sb.append(", type=").append(type);
        sb.append(", calltime=").append(calltime);
        sb.append(", groupid=").append(groupid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
