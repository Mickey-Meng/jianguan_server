package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @author
 *
 */

public class ZjConponentProducetime implements Serializable {

    private Integer id;

    private int status;
    /**
     * 构件id
     */
    private Integer conponentid;

    /**
     * 构件编码
     */
    private String conponentcode;

    /**
     * 构件计划开始时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date plansttime;

    /**
     * 构件计划完成时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date planendtime;

    /**
     * 构件实际开始时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date actulsttime;

    /**
     * 构件实际完成时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date actulendtime;

    /**
     * 形象开始时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date xxsttime;

    /**
     * 形象完成时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date xxendtime;

    /**
     * 记录时间
     */
    @JsonFormat(timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
    private Date recordtime;

    /**
     * 项目编号
     */
    private String projectcode;

    /**
     * 项目类型
     */
    private String projecttype;

    /**
     * 构件类型
     */
    private String conponenttype;

    /**
     * 项目名称
     */
    private String projectname;

    /**
     * 构件类型名称
     */
    private String conponenttypename;

    private String x;

    private String y;

    private String z;

    /**
     * 模型id
     */
    private String mouldid;

    /**
     * 设计方量
     */
    private Float desginnum;

    /**
     * 实际方量
     */
    private Float actulnum;

    /**
     * 工区id
     */
    private String gongquid;

    /**
     * 工区name
     */
    private String gongquname;

    /**
     * 上下部结构
     */
    private String pjctype;
    /**
     * 部门id
     */
    private Integer groupid;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public String getConponentcode() {
        return conponentcode;
    }

    public void setConponentcode(String conponentcode) {
        this.conponentcode = conponentcode;
    }

    public Date getPlansttime() {
        return plansttime;
    }

    public void setPlansttime(Date plansttime) {
        this.plansttime = plansttime;
    }

    public Date getPlanendtime() {
        return planendtime;
    }

    public void setPlanendtime(Date planendtime) {
        this.planendtime = planendtime;
    }

    public Date getActulsttime() {
        return actulsttime;
    }

    public void setActulsttime(Date actulsttime) {
        this.actulsttime = actulsttime;
    }

    public Date getActulendtime() {
        return actulendtime;
    }

    public void setActulendtime(Date actulendtime) {
        this.actulendtime = actulendtime;
    }

    public Date getXxsttime() {
        return xxsttime;
    }

    public void setXxsttime(Date xxsttime) {
        this.xxsttime = xxsttime;
    }

    public Date getXxendtime() {
        return xxendtime;
    }

    public void setXxendtime(Date xxendtime) {
        this.xxendtime = xxendtime;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getConponenttypename() {
        return conponenttypename;
    }

    public void setConponenttypename(String conponenttypename) {
        this.conponenttypename = conponenttypename;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getMouldid() {
        return mouldid;
    }

    public void setMouldid(String mouldid) {
        this.mouldid = mouldid;
    }

    public Float getDesginnum() {
        return desginnum;
    }

    public void setDesginnum(Float desginnum) {
        this.desginnum = desginnum;
    }

    public Float getActulnum() {
        return actulnum;
    }

    public void setActulnum(Float actulnum) {
        this.actulnum = actulnum;
    }

    public String getGongquid() {
        return gongquid;
    }

    public void setGongquid(String gongquid) {
        this.gongquid = gongquid;
    }

    public String getGongquname() {
        return gongquname;
    }

    public void setGongquname(String gongquname) {
        this.gongquname = gongquname;
    }

    public String getPjctype() {
        return pjctype;
    }

    public void setPjctype(String pjctype) {
        this.pjctype = pjctype;
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
        ZjConponentProducetime other = (ZjConponentProducetime) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getConponentid() == null ? other.getConponentid() == null : this.getConponentid().equals(other.getConponentid()))
                && (this.getConponentcode() == null ? other.getConponentcode() == null : this.getConponentcode().equals(other.getConponentcode()))
                && (this.getPlansttime() == null ? other.getPlansttime() == null : this.getPlansttime().equals(other.getPlansttime()))
                && (this.getPlanendtime() == null ? other.getPlanendtime() == null : this.getPlanendtime().equals(other.getPlanendtime()))
                && (this.getActulsttime() == null ? other.getActulsttime() == null : this.getActulsttime().equals(other.getActulsttime()))
                && (this.getActulendtime() == null ? other.getActulendtime() == null : this.getActulendtime().equals(other.getActulendtime()))
                && (this.getXxsttime() == null ? other.getXxsttime() == null : this.getXxsttime().equals(other.getXxsttime()))
                && (this.getXxendtime() == null ? other.getXxendtime() == null : this.getXxendtime().equals(other.getXxendtime()))
                && (this.getRecordtime() == null ? other.getRecordtime() == null : this.getRecordtime().equals(other.getRecordtime()))
                && (this.getProjectcode() == null ? other.getProjectcode() == null : this.getProjectcode().equals(other.getProjectcode()))
                && (this.getProjecttype() == null ? other.getProjecttype() == null : this.getProjecttype().equals(other.getProjecttype()))
                && (this.getConponenttype() == null ? other.getConponenttype() == null : this.getConponenttype().equals(other.getConponenttype()))
                && (this.getProjectname() == null ? other.getProjectname() == null : this.getProjectname().equals(other.getProjectname()))
                && (this.getConponenttypename() == null ? other.getConponenttypename() == null : this.getConponenttypename().equals(other.getConponenttypename()))
                && (this.getX() == null ? other.getX() == null : this.getX().equals(other.getX()))
                && (this.getY() == null ? other.getY() == null : this.getY().equals(other.getY()))
                && (this.getZ() == null ? other.getZ() == null : this.getZ().equals(other.getZ()))
                && (this.getMouldid() == null ? other.getMouldid() == null : this.getMouldid().equals(other.getMouldid()))
                && (this.getDesginnum() == null ? other.getDesginnum() == null : this.getDesginnum().equals(other.getDesginnum()))
                && (this.getActulnum() == null ? other.getActulnum() == null : this.getActulnum().equals(other.getActulnum()))
                && (this.getGongquid() == null ? other.getGongquid() == null : this.getGongquid().equals(other.getGongquid()))
                && (this.getGongquname() == null ? other.getGongquname() == null : this.getGongquname().equals(other.getGongquname()))
                && (this.getGongquid() == null ? other.getGongquid() == null : this.getGongquid().equals(other.getGongquid()))
                && (this.getPjctype() == null ? other.getPjctype() == null : this.getPjctype().equals(other.getPjctype()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getConponentid() == null) ? 0 : getConponentid().hashCode());
        result = prime * result + ((getConponentcode() == null) ? 0 : getConponentcode().hashCode());
        result = prime * result + ((getPlansttime() == null) ? 0 : getPlansttime().hashCode());
        result = prime * result + ((getPlanendtime() == null) ? 0 : getPlanendtime().hashCode());
        result = prime * result + ((getActulsttime() == null) ? 0 : getActulsttime().hashCode());
        result = prime * result + ((getActulendtime() == null) ? 0 : getActulendtime().hashCode());
        result = prime * result + ((getXxsttime() == null) ? 0 : getXxsttime().hashCode());
        result = prime * result + ((getXxendtime() == null) ? 0 : getXxendtime().hashCode());
        result = prime * result + ((getRecordtime() == null) ? 0 : getRecordtime().hashCode());
        result = prime * result + ((getProjectcode() == null) ? 0 : getProjectcode().hashCode());
        result = prime * result + ((getProjecttype() == null) ? 0 : getProjecttype().hashCode());
        result = prime * result + ((getConponenttype() == null) ? 0 : getConponenttype().hashCode());
        result = prime * result + ((getProjectname() == null) ? 0 : getProjectname().hashCode());
        result = prime * result + ((getConponenttypename() == null) ? 0 : getConponenttypename().hashCode());
        result = prime * result + ((getX() == null) ? 0 : getX().hashCode());
        result = prime * result + ((getY() == null) ? 0 : getY().hashCode());
        result = prime * result + ((getZ() == null) ? 0 : getZ().hashCode());
        result = prime * result + ((getMouldid() == null) ? 0 : getMouldid().hashCode());
        result = prime * result + ((getDesginnum() == null) ? 0 : getDesginnum().hashCode());
        result = prime * result + ((getActulnum() == null) ? 0 : getActulnum().hashCode());
        result = prime * result + ((getGongquid() == null) ? 0 : getGongquid().hashCode());
        result = prime * result + ((getGongquname() == null) ? 0 : getGongquname().hashCode());
        result = prime * result + ((getPjctype() == null) ? 0 : getPjctype().hashCode());
        result = prime * result + ((getGongquid() == null) ? 0 : getGongquid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", conponentid=").append(conponentid);
        sb.append(", conponentcode=").append(conponentcode);
        sb.append(", plansttime=").append(plansttime);
        sb.append(", planendtime=").append(planendtime);
        sb.append(", actulsttime=").append(actulsttime);
        sb.append(", actulendtime=").append(actulendtime);
        sb.append(", xxsttime=").append(xxsttime);
        sb.append(", xxendtime=").append(xxendtime);
        sb.append(", recordtime=").append(recordtime);
        sb.append(", projectcode=").append(projectcode);
        sb.append(", projecttype=").append(projecttype);
        sb.append(", conponenttype=").append(conponenttype);
        sb.append(", projectname=").append(projectname);
        sb.append(", conponenttypename=").append(conponenttypename);
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", z=").append(z);
        sb.append(", mouldid=").append(mouldid);
        sb.append(", desginnum=").append(desginnum);
        sb.append(", actulnum=").append(actulnum);
        sb.append(", gongquid=").append(gongquid);
        sb.append(", gongquname=").append(gongquname);
        sb.append(", pjctype=").append(pjctype);
        sb.append(", groupid=").append(groupid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
