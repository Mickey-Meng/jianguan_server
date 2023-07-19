package com.ruoyi.jianguan.common.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * @author
 *
 */

public class ZjQualityEvent implements Serializable {
    /**
     * 主键id
     */

    private Integer id;

    /**
     * 安全大类
     */
    private Integer qualityfirst;

    /**
     * 安全小类
     */
    private Integer qualitysecond;

    /**
     * 上传人id
     */
    private Integer uploadid;

    /**
     * 上传人姓名
     */
    private String uploadname;

    /**
     * 上传图片地址
     */
    private String uploadurl;

    /**
     * 上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date uploadtime;

    /**
     * 上传地点
     */
    private String uploadloaction;

    /**
     * 保存经纬度
     */
    private String loacltion;

    /**
     * 上传问题描述
     */
    private String uploadremark;

    /**
     * 整改人id
     */
    private Integer modifyid;

    /**
     * 整改人姓名
     */
    private String modifyname;

    /**
     * 限期整改日期
     */
    private Integer modifydate;

    /**
     * 整改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifytime;

    /**
     * 整改意见
     */
    private String modifyremark;

    /**
     * 申请整改延期
     */
    private Integer delayday;

    /**
     * 0，提交事件，1申请延期，2提交整改，3确认整改
     */
    private Integer status;

    /**
     * 1，正常完成，2异常完成
     */
    private Integer normal;

    /**
     * 整改照片
     */
    private String modifyurl;

    /**
     * 申请延期原因
     */
    private String delayreason;

    /**
     * 0,未申请，1，申请中，2通过，3不通过
     */
    private Integer delayresult;

    /**
     * 处理延期的原因
     */
    private String dodelayreason;

    /**
     * 工区id
     */
    private Integer gongquid;

    private String qualityfirstname;

    private String qualitysecondname;

    /**
     * 项目编号
     */
    private Integer singleProjectId;

    private Integer projectId;

    private Integer groupid;

    private String copyUsers;


    private String singleProjectName;

    private String gongquname;

    public String getSingleProjectName() {
        return singleProjectName;
    }

    public void setSingleProjectName(String singleProjectName) {
        this.singleProjectName = singleProjectName;
    }

    public String getGongquname() {
        return gongquname;
    }

    public void setGongquname(String gongquname) {
        this.gongquname = gongquname;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getUploadloaction() {
        return uploadloaction;
    }

    public void setUploadloaction(String uploadloaction) {
        this.uploadloaction = uploadloaction;
    }

    public String getLoacltion() {
        return loacltion;
    }

    public void setLoacltion(String loacltion) {
        this.loacltion = loacltion;
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

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getModifyremark() {
        return modifyremark;
    }

    public void setModifyremark(String modifyremark) {
        this.modifyremark = modifyremark;
    }

    public Integer getDelayday() {
        return delayday;
    }

    public void setDelayday(Integer delayday) {
        this.delayday = delayday;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public String getModifyurl() {
        return modifyurl;
    }

    public void setModifyurl(String modifyurl) {
        this.modifyurl = modifyurl;
    }

    public String getDelayreason() {
        return delayreason;
    }

    public void setDelayreason(String delayreason) {
        this.delayreason = delayreason;
    }

    public Integer getDelayresult() {
        return delayresult;
    }

    public void setDelayresult(Integer delayresult) {
        this.delayresult = delayresult;
    }

    public String getDodelayreason() {
        return dodelayreason;
    }

    public void setDodelayreason(String dodelayreason) {
        this.dodelayreason = dodelayreason;
    }

    public Integer getGongquid() {
        return gongquid;
    }

    public void setGongquid(Integer gongquid) {
        this.gongquid = gongquid;
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

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getCopyUsers() {
        return copyUsers;
    }

    public void setCopyUsers(String copyUsers) {
        this.copyUsers = copyUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZjQualityEvent that = (ZjQualityEvent) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(qualityfirst, that.qualityfirst) &&
                Objects.equals(qualitysecond, that.qualitysecond) &&
                Objects.equals(uploadid, that.uploadid) &&
                Objects.equals(uploadname, that.uploadname) &&
                Objects.equals(uploadurl, that.uploadurl) &&
                Objects.equals(uploadtime, that.uploadtime) &&
                Objects.equals(uploadloaction, that.uploadloaction) &&
                Objects.equals(loacltion, that.loacltion) &&
                Objects.equals(uploadremark, that.uploadremark) &&
                Objects.equals(modifyid, that.modifyid) &&
                Objects.equals(modifyname, that.modifyname) &&
                Objects.equals(modifydate, that.modifydate) &&
                Objects.equals(modifytime, that.modifytime) &&
                Objects.equals(modifyremark, that.modifyremark) &&
                Objects.equals(delayday, that.delayday) &&
                Objects.equals(status, that.status) &&
                Objects.equals(normal, that.normal) &&
                Objects.equals(modifyurl, that.modifyurl) &&
                Objects.equals(delayreason, that.delayreason) &&
                Objects.equals(delayresult, that.delayresult) &&
                Objects.equals(dodelayreason, that.dodelayreason) &&
                Objects.equals(gongquid, that.gongquid) &&
                Objects.equals(qualityfirstname, that.qualityfirstname) &&
                Objects.equals(qualitysecondname, that.qualitysecondname) &&
                Objects.equals(singleProjectId, that.singleProjectId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(groupid, that.groupid) &&
                Objects.equals(copyUsers, that.copyUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qualityfirst, qualitysecond, uploadid, uploadname, uploadurl, uploadtime, uploadloaction, loacltion, uploadremark, modifyid, modifyname, modifydate, modifytime, modifyremark, delayday, status, normal, modifyurl, delayreason, delayresult, dodelayreason, gongquid, qualityfirstname, qualitysecondname, singleProjectId, projectId, groupid, copyUsers);
    }

    @Override
    public String toString() {
        return "ZjQualityEvent{" +
                "id=" + id +
                ", qualityfirst=" + qualityfirst +
                ", qualitysecond=" + qualitysecond +
                ", uploadid=" + uploadid +
                ", uploadname='" + uploadname + '\'' +
                ", uploadurl='" + uploadurl + '\'' +
                ", uploadtime=" + uploadtime +
                ", uploadloaction='" + uploadloaction + '\'' +
                ", loacltion='" + loacltion + '\'' +
                ", uploadremark='" + uploadremark + '\'' +
                ", modifyid=" + modifyid +
                ", modifyname='" + modifyname + '\'' +
                ", modifydate=" + modifydate +
                ", modifytime=" + modifytime +
                ", modifyremark='" + modifyremark + '\'' +
                ", delayday=" + delayday +
                ", status=" + status +
                ", normal=" + normal +
                ", modifyurl='" + modifyurl + '\'' +
                ", delayreason='" + delayreason + '\'' +
                ", delayresult=" + delayresult +
                ", dodelayreason='" + dodelayreason + '\'' +
                ", gongquid=" + gongquid +
                ", qualityfirstname='" + qualityfirstname + '\'' +
                ", qualitysecondname='" + qualitysecondname + '\'' +
                ", singleProjectId=" + singleProjectId +
                ", projectId=" + projectId +
                ", groupid=" + groupid +
                ", copyUsers='" + copyUsers + '\'' +
                '}';
    }
}
