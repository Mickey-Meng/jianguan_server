package com.ruoyi.jianguan.common.domain.entity;

import com.ruoyi.common.core.domain.entity.Conponent;

import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/21 1:41 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class NodeTree {



    private List<NodeTree> child;
    private List<Conponent> childConponent;

    public List<Conponent> getChildConponent() {
        return childConponent;
    }

    public void setChildConponent(List<Conponent> childConponent) {
        this.childConponent = childConponent;
    }

    private String name;
    private String code;
    private Integer id;
    private String mouldId;
    private String x;
    private String y;
    private String z;
    private boolean hasChild;

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
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

    public String getMouldId() {
        return mouldId;
    }

    public void setMouldId(String mouldId) {
        this.mouldId = mouldId;
    }


    public List<NodeTree> getChild() {
        return child;
    }

    public void setChild(List<NodeTree> child) {
        this.child = child;
    }
}


