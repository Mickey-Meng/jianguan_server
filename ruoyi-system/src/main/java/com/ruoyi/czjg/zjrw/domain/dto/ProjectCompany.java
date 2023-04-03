package com.ruoyi.czjg.zjrw.domain.dto;

import java.util.Objects;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/17 15:06
 * @Version : 1.0
 * @Description :
 **/
public class ProjectCompany {

    private Integer id;

    private String name;

    private String code;

    private String typename;

    private String typecode;

    private Integer projectid;

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

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectCompany)) return false;
        ProjectCompany that = (ProjectCompany) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                getCode().equals(that.getCode()) &&
                getTypename().equals(that.getTypename()) &&
                getTypecode().equals(that.getTypecode()) &&
                getProjectid().equals(that.getProjectid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCode(), getTypename(), getTypecode(), getProjectid());
    }

    @Override
    public String toString() {
        return "ProjectCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", typename='" + typename + '\'' +
                ", typecode='" + typecode + '\'' +
                ", projectid=" + projectid +
                '}';
    }
}
