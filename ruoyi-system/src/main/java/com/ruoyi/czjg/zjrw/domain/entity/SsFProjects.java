package com.ruoyi.czjg.zjrw.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author
 * 组织列表
 */
public class SsFProjects implements Serializable {
    /**
     * key,自增主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 父级ID
     */
    private Integer parentid;

    /**
     * 部门类型
     */
    private String type;

    /**
     * 级别(辅助字段,树状层级)
     */
    private Integer grouplevel;

    private String visible;

    /**
     * 插入记录的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sttime;

    /**
     * 状态: -1: 删除, 0: 冻结, 1: 正常
     */
    private Integer ststate;

    /**
     * 顺序
     */
    private Integer storder;

    /**
     * 组织ID
     */
    private String groupid;

    /**
     * 是否自管
     */
    private Integer isauto;

    /**
     * 项目照片
     */
    @ApiModelProperty(value = "项目照片")
    private String projectpic;

    /**
     * 合同号
     */
    @ApiModelProperty(value = "合同号")
    private String contractnum;

    @ApiModelProperty(value = "坐标")
    private String coordinate;

    @ApiModelProperty(value = "投资金额(单位: 万元)")
    private Integer investment;

    @ApiModelProperty(value = "项目类型（交通类、市政类、房建类、其他类）")
    private String projecttype;

    @ApiModelProperty(value = "项目点")
    private String projectpoint;

    @ApiModelProperty(value = "项目线")
    private String projectline;

    @ApiModelProperty(value = "项目面")
    private String projectsurface;

    @ApiModelProperty(value = "项目简介")
    private String introduction;

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getInvestment() {
        return investment;
    }

    public void setInvestment(Integer investment) {
        this.investment = investment;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGrouplevel() {
        return grouplevel;
    }

    public void setGrouplevel(Integer grouplevel) {
        this.grouplevel = grouplevel;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    public Integer getStstate() {
        return ststate;
    }

    public void setStstate(Integer ststate) {
        this.ststate = ststate;
    }

    public Integer getStorder() {
        return storder;
    }

    public void setStorder(Integer storder) {
        this.storder = storder;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public Integer getIsauto() {
        return isauto;
    }

    public void setIsauto(Integer isauto) {
        this.isauto = isauto;
    }

    public String getProjectpic() {
        return projectpic;
    }

    public void setProjectpic(String projectpic) {
        this.projectpic = projectpic;
    }

    public String getContractnum() {
        return contractnum;
    }

    public void setContractnum(String contractnum) {
        this.contractnum = contractnum;
    }

    public String getProjectpoint() {
        return projectpoint;
    }

    public void setProjectpoint(String projectpoint) {
        this.projectpoint = projectpoint;
    }

    public String getProjectline() {
        return projectline;
    }

    public void setProjectline(String projectline) {
        this.projectline = projectline;
    }

    public String getProjectsurface() {
        return projectsurface;
    }

    public void setProjectsurface(String projectsurface) {
        this.projectsurface = projectsurface;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SsFProjects that = (SsFProjects) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(code, that.code) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(type, that.type) &&
                Objects.equals(grouplevel, that.grouplevel) &&
                Objects.equals(visible, that.visible) &&
                Objects.equals(sttime, that.sttime) &&
                Objects.equals(ststate, that.ststate) &&
                Objects.equals(storder, that.storder) &&
                Objects.equals(groupid, that.groupid) &&
                Objects.equals(isauto, that.isauto) &&
                Objects.equals(projectpic, that.projectpic) &&
                Objects.equals(contractnum, that.contractnum) &&
                Objects.equals(coordinate, that.coordinate) &&
                Objects.equals(investment, that.investment) &&
                Objects.equals(projecttype, that.projecttype) &&
                Objects.equals(projectpoint, that.projectpoint) &&
                Objects.equals(projectline, that.projectline) &&
                Objects.equals(projectsurface, that.projectsurface) &&
                Objects.equals(introduction, that.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, parentid, type, grouplevel, visible, sttime, ststate, storder, groupid, isauto, projectpic, contractnum, coordinate, investment, projecttype, projectpoint, projectline, projectsurface, introduction);
    }

    @Override
    public String toString() {
        return "SsFProjects{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", parentid=" + parentid +
                ", type='" + type + '\'' +
                ", grouplevel=" + grouplevel +
                ", visible='" + visible + '\'' +
                ", sttime=" + sttime +
                ", ststate=" + ststate +
                ", storder=" + storder +
                ", groupid='" + groupid + '\'' +
                ", isauto=" + isauto +
                ", projectpic='" + projectpic + '\'' +
                ", contractnum='" + contractnum + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", investment=" + investment +
                ", projecttype='" + projecttype + '\'' +
                ", projectpoint='" + projectpoint + '\'' +
                ", projectline='" + projectline + '\'' +
                ", projectsurface='" + projectsurface + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
