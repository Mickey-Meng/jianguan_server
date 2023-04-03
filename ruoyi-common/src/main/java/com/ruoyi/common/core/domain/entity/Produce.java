package com.ruoyi.common.core.domain.entity;

import java.io.Serializable;

/**
 * @author
 *
 */
public class Produce implements Serializable {
    private Integer id;

    private String type;

    private String name;

    private Integer rangee;

    private Integer isvaild;

    private Integer groupid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRange() {
        return rangee;
    }

    public void setRange(Integer rangee) {
        this.rangee = rangee;
    }

    public Integer getIsvaild() {
        return isvaild;
    }

    public void setIsvaild(Integer isvaild) {
        this.isvaild = isvaild;
    }

    public Integer getRangee() {
        return rangee;
    }

    public void setRangee(Integer rangee) {
        this.rangee = rangee;
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
        Produce other = (Produce) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRange() == null ? other.getRange() == null : this.getRange().equals(other.getRange()))
            && (this.getGroupid() == null ? other.getGroupid() == null : this.getGroupid().equals(other.getGroupid()))
            && (this.getIsvaild() == null ? other.getIsvaild() == null : this.getIsvaild().equals(other.getIsvaild()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRange() == null) ? 0 : getRange().hashCode());
        result = prime * result + ((getIsvaild() == null) ? 0 : getIsvaild().hashCode());
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
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", range=").append(rangee);
        sb.append(", isvaild=").append(isvaild);
        sb.append(", groupid=").append(groupid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
