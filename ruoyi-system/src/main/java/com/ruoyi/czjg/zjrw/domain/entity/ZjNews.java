package com.ruoyi.czjg.zjrw.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * @author
 *
 */

public class ZjNews implements Serializable {

    private Integer id;

    /**
     * 1工程新闻，2活动新闻，3安全新闻
     */
    private Integer type;

    /**
     * 标题
     */
    private String title;

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date sttime;

    private String pic;

    /**
     * 附件
     */
    private String attch;

    private Integer groupid;

    private Integer projectId;

    private String content;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAttch() {
        return attch;
    }

    public void setAttch(String attch) {
        this.attch = attch;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZjNews zjNews = (ZjNews) o;
        return Objects.equals(id, zjNews.id) &&
                Objects.equals(type, zjNews.type) &&
                Objects.equals(title, zjNews.title) &&
                Objects.equals(sttime, zjNews.sttime) &&
                Objects.equals(pic, zjNews.pic) &&
                Objects.equals(attch, zjNews.attch) &&
                Objects.equals(groupid, zjNews.groupid) &&
                Objects.equals(projectId, zjNews.projectId) &&
                Objects.equals(content, zjNews.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, title, sttime, pic, attch, groupid, projectId, content);
    }

    @Override
    public String toString() {
        return "ZjNews{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", sttime=" + sttime +
                ", pic='" + pic + '\'' +
                ", attch='" + attch + '\'' +
                ", groupid=" + groupid +
                ", projectId=" + projectId +
                ", content='" + content + '\'' +
                '}';
    }
}
