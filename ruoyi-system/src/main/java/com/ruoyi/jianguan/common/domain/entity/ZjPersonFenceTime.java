package com.ruoyi.jianguan.common.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruoyi.jianguan.common.domain.dto.PostDTO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Chen_ZhiWei
 * @since 2022-05-30
 */
public class ZjPersonFenceTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "postId和postName的对象集合")
    private List<PostDTO> posts;

    /**
     * 打卡开始时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "打卡开始时间")
    private String clockInStartTime;

    /**
     * 打卡结束时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "打卡结束时间")
    private String clockInEndTime;

    /**
     * 打卡时常
     */
    @ApiModelProperty(value = "打卡时常")
    private Integer clockInOften;

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

    private Integer projectId;

    //===============返回前端
    private String postId;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    //=====================

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public String getClockInStartTime() {
        return clockInStartTime;
    }

    public void setClockInStartTime(String clockInStartTime) {
        this.clockInStartTime = clockInStartTime;
    }

    public String getClockInEndTime() {
        return clockInEndTime;
    }

    public void setClockInEndTime(String clockInEndTime) {
        this.clockInEndTime = clockInEndTime;
    }

    public Integer getClockInOften() {
        return clockInOften;
    }

    public void setClockInOften(Integer clockInOften) {
        this.clockInOften = clockInOften;
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


    @Override
    public String toString() {
        return "ZjPersonFenceTime{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", posts=" + posts +
                ", clockInStartTime=" + clockInStartTime +
                ", clockInEndTime=" + clockInEndTime +
                ", clockInOften=" + clockInOften +
                ", status=" + status +
                ", order=" + order +
                ", projectId=" + projectId +
                ", postId='" + postId + '\'' +
                '}';
    }
}
