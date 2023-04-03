package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author
 *
 */
public class Produceandrecode implements Serializable,Comparable<Produceandrecode> {
    private Integer id;

    /**
     * 构件id
     */
    private Integer conponentid;

    /**
     * 记录id
     */
    private Integer recodeid;

    /**
     * 工序id
     */
    private Integer produceid;

    /**
     * 项目编码
     */
    private String projectcode;

    /**
     * 确认结果待确认0，驳回2，确认1
     */
    private Integer checkresult;

    /**
     * 监理的id
     */
    private Integer checkuser;

    /**
     * 上报人id
     */
    private Integer updateuser;

    /**
     * 开始上报时间
     */
    @JsonFormat(timezone = "GMT+8")
    private Date stime;

    /**
     * 最后处理时间
     */
    @JsonFormat(timezone = "GMT+8")
    private Date updatetime;

    /**
     * 报检名称
     */
    private String producename;

    /**
     * 构件类型编码
     */
    private String conponenttype;

    /**
     * 构件类型名称
     */
    private String conponentname;

    /**
     * 上传人姓名
     */
    private String updateusername;

    /**
     * 监理人姓名
     */
    private String checkusername;

    /**
     * 构件编码
     */
    private String conponentcode;

    /**
     * 工序状态:0-监理已提交,1-施工已确认工序并提交给监理审批,3-监理审批完成
     */
    private Integer status;

    private Integer groupid;

    private Integer projectId;

    private String copyUsers;

    public String getCopyUsers() {
        return copyUsers;
    }

    public void setCopyUsers(String copyUsers) {
        this.copyUsers = copyUsers;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConponentid() {
        return conponentid;
    }

    public void setConponentid(Integer conponentid) {
        this.conponentid = conponentid;
    }

    public Integer getRecodeid() {
        return recodeid;
    }

    public void setRecodeid(Integer recodeid) {
        this.recodeid = recodeid;
    }

    public Integer getProduceid() {
        return produceid;
    }

    public void setProduceid(Integer produceid) {
        this.produceid = produceid;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public Integer getCheckresult() {
        return checkresult;
    }

    public void setCheckresult(Integer checkresult) {
        this.checkresult = checkresult;
    }

    public Integer getCheckuser() {
        return checkuser;
    }

    public void setCheckuser(Integer checkuser) {
        this.checkuser = checkuser;
    }

    public Integer getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(Integer updateuser) {
        this.updateuser = updateuser;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getProducename() {
        return producename;
    }

    public void setProducename(String producename) {
        this.producename = producename;
    }

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
    }

    public String getConponentname() {
        return conponentname;
    }

    public void setConponentname(String conponentname) {
        this.conponentname = conponentname;
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

    public String getConponentcode() {
        return conponentcode;
    }

    public void setConponentcode(String conponentcode) {
        this.conponentcode = conponentcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produceandrecode that = (Produceandrecode) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(conponentid, that.conponentid) &&
                Objects.equals(recodeid, that.recodeid) &&
                Objects.equals(produceid, that.produceid) &&
                Objects.equals(projectcode, that.projectcode) &&
                Objects.equals(checkresult, that.checkresult) &&
                Objects.equals(checkuser, that.checkuser) &&
                Objects.equals(updateuser, that.updateuser) &&
                Objects.equals(stime, that.stime) &&
                Objects.equals(updatetime, that.updatetime) &&
                Objects.equals(producename, that.producename) &&
                Objects.equals(conponenttype, that.conponenttype) &&
                Objects.equals(conponentname, that.conponentname) &&
                Objects.equals(updateusername, that.updateusername) &&
                Objects.equals(checkusername, that.checkusername) &&
                Objects.equals(conponentcode, that.conponentcode) &&
                Objects.equals(status, that.status) &&
                Objects.equals(groupid, that.groupid) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(copyUsers, that.copyUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, conponentid, recodeid, produceid, projectcode, checkresult, checkuser, updateuser, stime, updatetime, producename, conponenttype, conponentname, updateusername, checkusername, conponentcode, status, groupid, projectId, copyUsers);
    }

    @Override
    public String toString() {
        return "Produceandrecode{" +
                "id=" + id +
                ", conponentid=" + conponentid +
                ", recodeid=" + recodeid +
                ", produceid=" + produceid +
                ", projectcode='" + projectcode + '\'' +
                ", checkresult=" + checkresult +
                ", checkuser=" + checkuser +
                ", updateuser=" + updateuser +
                ", stime=" + stime +
                ", updatetime=" + updatetime +
                ", producename='" + producename + '\'' +
                ", conponenttype='" + conponenttype + '\'' +
                ", conponentname='" + conponentname + '\'' +
                ", updateusername='" + updateusername + '\'' +
                ", checkusername='" + checkusername + '\'' +
                ", conponentcode='" + conponentcode + '\'' +
                ", status=" + status +
                ", groupid=" + groupid +
                ", projectId=" + projectId +
                ", copyUsers='" + copyUsers + '\'' +
                '}';
    }

    @Override
    public int compareTo(Produceandrecode o) {
        return this.produceid-o.produceid;
    }
}
