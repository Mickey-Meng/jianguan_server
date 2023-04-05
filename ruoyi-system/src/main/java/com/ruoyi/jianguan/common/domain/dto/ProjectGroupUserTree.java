package com.ruoyi.jianguan.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/19 11:16
 * @Version : 1.0
 * @Description :
 **/
public class ProjectGroupUserTree {

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

    private List<Object> children;

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

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }

}
