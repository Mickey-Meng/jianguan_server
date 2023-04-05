package com.ruoyi.jianguan.common.domain.dto;

import java.util.Objects;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/19 14:25
 * @Version : 1.0
 * @Description :
 **/
public class RoleDataDTO {

    private Integer roleid;

    private String name;

    private String code;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDataDTO)) return false;
        RoleDataDTO that = (RoleDataDTO) o;
        return Objects.equals(getRoleid(), that.getRoleid()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleid(), getName(), getCode());
    }

    @Override
    public String toString() {
        return "RoleDataDTO{" +
                "roleid=" + roleid +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
