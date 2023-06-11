package com.ruoyi.jianguan.common.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 *
 */
public class ZjFile implements Serializable {


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

    private Integer groupid;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date calltime;

    private Integer projectId;

    private String filename;

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
        ZjFile other = (ZjFile) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUploadid() == null ? other.getUploadid() == null : this.getUploadid().equals(other.getUploadid()))
            && (this.getUploadusername() == null ? other.getUploadusername() == null : this.getUploadusername().equals(other.getUploadusername()))
            && (this.getUploadtime() == null ? other.getUploadtime() == null : this.getUploadtime().equals(other.getUploadtime()))
            && (this.getUploadtype() == null ? other.getUploadtype() == null : this.getUploadtype().equals(other.getUploadtype()))
            && (this.getUploadname() == null ? other.getUploadname() == null : this.getUploadname().equals(other.getUploadname()))
            && (this.getChangereason() == null ? other.getChangereason() == null : this.getChangereason().equals(other.getChangereason()))
            && (this.getDistiancename() == null ? other.getDistiancename() == null : this.getDistiancename().equals(other.getDistiancename()))
            && (this.getChangecontent() == null ? other.getChangecontent() == null : this.getChangecontent().equals(other.getChangecontent()))
            && (this.getIsutf() == null ? other.getIsutf() == null : this.getIsutf().equals(other.getIsutf()))
            && (this.getSoftname() == null ? other.getSoftname() == null : this.getSoftname().equals(other.getSoftname()))
            && (this.getOpiontion() == null ? other.getOpiontion() == null : this.getOpiontion().equals(other.getOpiontion()))
            && (this.getCallunit() == null ? other.getCallunit() == null : this.getCallunit().equals(other.getCallunit()))
            && (this.getCalladdr() == null ? other.getCalladdr() == null : this.getCalladdr().equals(other.getCalladdr()))
            && (this.getFileurl() == null ? other.getFileurl() == null : this.getFileurl().equals(other.getFileurl()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getGroupid() == null ? other.getGroupid() == null : this.getGroupid().equals(other.getGroupid()))
            && (this.getCalltime() == null ? other.getCalltime() == null : this.getCalltime().equals(other.getCalltime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUploadid() == null) ? 0 : getUploadid().hashCode());
        result = prime * result + ((getUploadusername() == null) ? 0 : getUploadusername().hashCode());
        result = prime * result + ((getUploadtime() == null) ? 0 : getUploadtime().hashCode());
        result = prime * result + ((getUploadtype() == null) ? 0 : getUploadtype().hashCode());
        result = prime * result + ((getUploadname() == null) ? 0 : getUploadname().hashCode());
        result = prime * result + ((getChangereason() == null) ? 0 : getChangereason().hashCode());
        result = prime * result + ((getDistiancename() == null) ? 0 : getDistiancename().hashCode());
        result = prime * result + ((getChangecontent() == null) ? 0 : getChangecontent().hashCode());
        result = prime * result + ((getIsutf() == null) ? 0 : getIsutf().hashCode());
        result = prime * result + ((getSoftname() == null) ? 0 : getSoftname().hashCode());
        result = prime * result + ((getOpiontion() == null) ? 0 : getOpiontion().hashCode());
        result = prime * result + ((getCallunit() == null) ? 0 : getCallunit().hashCode());
        result = prime * result + ((getCalladdr() == null) ? 0 : getCalladdr().hashCode());
        result = prime * result + ((getFileurl() == null) ? 0 : getFileurl().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCalltime() == null) ? 0 : getCalltime().hashCode());
        result = prime * result + ((getGroupid() == null) ? 0 : getGroupid().hashCode());
        return result;
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
