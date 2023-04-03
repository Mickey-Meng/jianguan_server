package com.ruoyi.czjg.zjrw.domain.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/17 15:59
 * @Version : 1.0
 * @Description :
 **/
public class SsFCompany implements Serializable {

    private Integer id;
    /**
     * 单位名称
     */
    private String name;
    /**
     * 单位编码
     */
    private String code;
    /**
     * 单位类型
     */
    private String typename;
    /**
     * 单位类型编码
     */
    private String typecode;
    /**
     * 法人
     */
    private String legalperson;
    /**
     * 法人联系电话
     */
    private String legalphone;
    /**
     * 公司基础信息
     */
    private String data;
    /**
     * 公司税号
     */
    private String dutynum;
    /**
     * 附件
     */
    private String enclosure;

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

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }

    public String getLegalphone() {
        return legalphone;
    }

    public void setLegalphone(String legalphone) {
        this.legalphone = legalphone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDutynum() {
        return dutynum;
    }

    public void setDutynum(String dutynum) {
        this.dutynum = dutynum;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    @Override
    public String toString() {
        return "SsFCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", typename='" + typename + '\'' +
                ", typecode='" + typecode + '\'' +
                ", legalperson='" + legalperson + '\'' +
                ", legalphone='" + legalphone + '\'' +
                ", data='" + data + '\'' +
                ", dutynum='" + dutynum + '\'' +
                ", enclosure='" + enclosure + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SsFCompany)) return false;
        SsFCompany that = (SsFCompany) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getTypename(), that.getTypename()) &&
                Objects.equals(getTypecode(), that.getTypecode()) &&
                Objects.equals(getLegalperson(), that.getLegalperson()) &&
                Objects.equals(getLegalphone(), that.getLegalphone()) &&
                Objects.equals(getData(), that.getData()) &&
                Objects.equals(getDutynum(), that.getDutynum()) &&
                Objects.equals(getEnclosure(), that.getEnclosure());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCode(), getTypename(), getTypecode(), getLegalperson(), getLegalphone(), getData(), getDutynum(), getEnclosure());
    }

}
