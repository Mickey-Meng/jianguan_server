package com.ruoyi.czjg.zjrw.domain.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Chen_ZhiWei
 * @since 2022-05-30
 */
public class ZjPersonFence implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 打卡地区名称
     */
    @ApiModelProperty(value = "打卡地区名称")
    private String clockAddrName;

    /**
     * 坐标
     */
    @ApiModelProperty(value = "坐标")
    private String coordinate;

    /**
     * 中心点（记录打卡地点名称用）
     */
    @ApiModelProperty(value = "中心点（记录打卡地点名称用）")
    private String centerPoint;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String describe;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer order;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClockAddrName() {
        return clockAddrName;
    }

    public void setClockAddrName(String clockAddrName) {
        this.clockAddrName = clockAddrName;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(String centerPoint) {
        this.centerPoint = centerPoint;
    }

    @Override
    public String toString() {
        return "ZjPersonFence{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", clockAddrName='" + clockAddrName + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", centerPoint='" + centerPoint + '\'' +
                ", describe='" + describe + '\'' +
                ", status=" + status +
                ", order=" + order +
                ", projectId=" + projectId +
                '}';
    }
}
