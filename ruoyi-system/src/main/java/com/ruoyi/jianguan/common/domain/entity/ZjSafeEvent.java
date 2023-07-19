package com.ruoyi.jianguan.common.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author
 *
 */
public class ZjSafeEvent implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 安全大类
     */
    private Integer safefirst;

    /**
     * 安全小类
     */
    private Integer safesecond;

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

    private String safefirstname;

    private String safesecondname;

    /**
     * 单位工程id
     */
    private Integer singleProjectId;

    private Integer groupid;

    private Integer projectId;

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

    public Integer getSingleProjectId() {
        return singleProjectId;
    }

    public void setSingleProjectId(Integer singleProjectId) {
        this.singleProjectId = singleProjectId;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
        ZjSafeEvent safeEvent = (ZjSafeEvent) o;
        return Objects.equals(id, safeEvent.id) &&
                Objects.equals(safefirst, safeEvent.safefirst) &&
                Objects.equals(safesecond, safeEvent.safesecond) &&
                Objects.equals(uploadid, safeEvent.uploadid) &&
                Objects.equals(uploadname, safeEvent.uploadname) &&
                Objects.equals(uploadurl, safeEvent.uploadurl) &&
                Objects.equals(uploadtime, safeEvent.uploadtime) &&
                Objects.equals(uploadloaction, safeEvent.uploadloaction) &&
                Objects.equals(loacltion, safeEvent.loacltion) &&
                Objects.equals(uploadremark, safeEvent.uploadremark) &&
                Objects.equals(modifyid, safeEvent.modifyid) &&
                Objects.equals(modifyname, safeEvent.modifyname) &&
                Objects.equals(modifydate, safeEvent.modifydate) &&
                Objects.equals(modifytime, safeEvent.modifytime) &&
                Objects.equals(modifyremark, safeEvent.modifyremark) &&
                Objects.equals(delayday, safeEvent.delayday) &&
                Objects.equals(status, safeEvent.status) &&
                Objects.equals(normal, safeEvent.normal) &&
                Objects.equals(modifyurl, safeEvent.modifyurl) &&
                Objects.equals(delayreason, safeEvent.delayreason) &&
                Objects.equals(delayresult, safeEvent.delayresult) &&
                Objects.equals(dodelayreason, safeEvent.dodelayreason) &&
                Objects.equals(gongquid, safeEvent.gongquid) &&
                Objects.equals(safefirstname, safeEvent.safefirstname) &&
                Objects.equals(safesecondname, safeEvent.safesecondname) &&
                Objects.equals(singleProjectId, safeEvent.singleProjectId) &&
                Objects.equals(groupid, safeEvent.groupid) &&
                Objects.equals(projectId, safeEvent.projectId) &&
                Objects.equals(copyUsers, safeEvent.copyUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, safefirst, safesecond, uploadid, uploadname, uploadurl, uploadtime, uploadloaction, loacltion, uploadremark, modifyid, modifyname, modifydate, modifytime, modifyremark, delayday, status, normal, modifyurl, delayreason, delayresult, dodelayreason, gongquid, safefirstname, safesecondname, singleProjectId, groupid, projectId, copyUsers);
    }

    @Override
    public String toString() {
        return "ZjSafeEvent{" +
                "id=" + id +
                ", safefirst=" + safefirst +
                ", safesecond=" + safesecond +
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
                ", safefirstname='" + safefirstname + '\'' +
                ", safesecondname='" + safesecondname + '\'' +
                ", singleProjectId=" + singleProjectId +
                ", groupid=" + groupid +
                ", projectId=" + projectId +
                ", copyUsers='" + copyUsers + '\'' +
                '}';
    }
}
