package com.ruoyi.czjg.zjrw.domain.entity;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/23 4:02 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class SafeCountByType {
    private String name;
    private Integer count;
    private Integer nodo;
    private Integer nodelay;
    private Integer nocheck;
    private Integer check;

    public Integer getNodo() {
        return nodo;
    }

    public void setNodo(Integer nodo) {
        this.nodo = nodo;
    }

    public Integer getNodelay() {
        return nodelay;
    }

    public void setNodelay(Integer nodelay) {
        this.nodelay = nodelay;
    }

    public Integer getNocheck() {
        return nocheck;
    }

    public void setNocheck(Integer nocheck) {
        this.nocheck = nocheck;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
