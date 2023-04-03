package com.ruoyi.common.core.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 *
 */
public class Conponentschedule implements Serializable {
    private Integer id;

    private Date planbegintime;

    private Date planendtime;

    private Date actulbegintime;

    private Date actulendtime;

    private String updater;

    private Date updatetime;

    private String fordayreport;

    /**
     * 构件id
     */
    private Integer conponentid;

    /**
     * 构件模型id
     */
    private String mouldid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPlanbegintime() {
        return planbegintime;
    }

    public void setPlanbegintime(Date planbegintime) {
        this.planbegintime = planbegintime;
    }

    public Date getPlanendtime() {
        return planendtime;
    }

    public void setPlanendtime(Date planendtime) {
        this.planendtime = planendtime;
    }

    public Date getActulbegintime() {
        return actulbegintime;
    }

    public void setActulbegintime(Date actulbegintime) {
        this.actulbegintime = actulbegintime;
    }

    public Date getActulendtime() {
        return actulendtime;
    }

    public void setActulendtime(Date actulendtime) {
        this.actulendtime = actulendtime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getFordayreport() {
        return fordayreport;
    }

    public void setFordayreport(String fordayreport) {
        this.fordayreport = fordayreport;
    }

    public Integer getConponentid() {
        return conponentid;
    }

    public void setConponentid(Integer conponentid) {
        this.conponentid = conponentid;
    }

    public String getMouldid() {
        return mouldid;
    }

    public void setMouldid(String mouldid) {
        this.mouldid = mouldid;
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
        Conponentschedule other = (Conponentschedule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlanbegintime() == null ? other.getPlanbegintime() == null : this.getPlanbegintime().equals(other.getPlanbegintime()))
            && (this.getPlanendtime() == null ? other.getPlanendtime() == null : this.getPlanendtime().equals(other.getPlanendtime()))
            && (this.getActulbegintime() == null ? other.getActulbegintime() == null : this.getActulbegintime().equals(other.getActulbegintime()))
            && (this.getActulendtime() == null ? other.getActulendtime() == null : this.getActulendtime().equals(other.getActulendtime()))
            && (this.getUpdater() == null ? other.getUpdater() == null : this.getUpdater().equals(other.getUpdater()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getFordayreport() == null ? other.getFordayreport() == null : this.getFordayreport().equals(other.getFordayreport()))
            && (this.getConponentid() == null ? other.getConponentid() == null : this.getConponentid().equals(other.getConponentid()))
            && (this.getMouldid() == null ? other.getMouldid() == null : this.getMouldid().equals(other.getMouldid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlanbegintime() == null) ? 0 : getPlanbegintime().hashCode());
        result = prime * result + ((getPlanendtime() == null) ? 0 : getPlanendtime().hashCode());
        result = prime * result + ((getActulbegintime() == null) ? 0 : getActulbegintime().hashCode());
        result = prime * result + ((getActulendtime() == null) ? 0 : getActulendtime().hashCode());
        result = prime * result + ((getUpdater() == null) ? 0 : getUpdater().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getFordayreport() == null) ? 0 : getFordayreport().hashCode());
        result = prime * result + ((getConponentid() == null) ? 0 : getConponentid().hashCode());
        result = prime * result + ((getMouldid() == null) ? 0 : getMouldid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", planbegintime=").append(planbegintime);
        sb.append(", planendtime=").append(planendtime);
        sb.append(", actulbegintime=").append(actulbegintime);
        sb.append(", actulendtime=").append(actulendtime);
        sb.append(", updater=").append(updater);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", fordayreport=").append(fordayreport);
        sb.append(", conponentid=").append(conponentid);
        sb.append(", mouldid=").append(mouldid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
