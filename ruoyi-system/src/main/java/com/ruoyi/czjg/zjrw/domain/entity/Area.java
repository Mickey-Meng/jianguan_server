package com.ruoyi.czjg.zjrw.domain.entity;

import java.io.Serializable;

/**
 * @author
 *
 */
public class Area implements Serializable {
    private Integer id;

    /**
     * 项目编码
     */
    private String projectcod;

    /**
     * 工区id
     */
    private Integer areaid;

    /**
     * 工区名字
     */
    private String areacode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectcod() {
        return projectcod;
    }

    public void setProjectcod(String projectcod) {
        this.projectcod = projectcod;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
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
        Area other = (Area) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectcod() == null ? other.getProjectcod() == null : this.getProjectcod().equals(other.getProjectcod()))
            && (this.getAreaid() == null ? other.getAreaid() == null : this.getAreaid().equals(other.getAreaid()))
            && (this.getAreacode() == null ? other.getAreacode() == null : this.getAreacode().equals(other.getAreacode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectcod() == null) ? 0 : getProjectcod().hashCode());
        result = prime * result + ((getAreaid() == null) ? 0 : getAreaid().hashCode());
        result = prime * result + ((getAreacode() == null) ? 0 : getAreacode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectcod=").append(projectcod);
        sb.append(", areaid=").append(areaid);
        sb.append(", areacode=").append(areacode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
