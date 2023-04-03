package com.ruoyi.common.core.domain.entity;

import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/8 3:19 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description: 权限对象，
 **/

public class PowerData {
    private Integer id; //用户id
    private List<String> projects; // 关联的项目id
    private List<Integer> gongqus; //关联的工区id
    private Integer role; //关联的角色id

    public PowerData(Integer id, List<String> projects, List<Integer> gongqus, Integer role) {
        this.id=id;
        this.projects = projects;
        this.gongqus = gongqus;
        this.role = role;
    }

    public PowerData(Integer id, List<String> projects, List<Integer> gongqus) {
        this.id=id;
        this.projects = projects;
        this.gongqus = gongqus;
    }

    public PowerData(Integer id, Integer role) {
        this.id=id;
        this.role = role;
    }

    public PowerData() {
    }

    public List<Integer> getGongqus() {
        return gongqus;
    }

    public void setGongqus(List<Integer> gongqus) {
        this.gongqus = gongqus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
