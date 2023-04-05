package com.ruoyi.jianguan.common.domain.dto;

import com.google.common.collect.Lists;
import com.ruoyi.jianguan.common.domain.entity.PersonUserGroupRole;
import com.ruoyi.jianguan.common.domain.entity.SsFPersonGroups;

import java.util.List;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/12 13:50
 * @Version : 1.0
 * @Description :
 **/
public class PersonGroupTree extends SsFPersonGroups{

    /**
     *	子级节点
     */
    private List<PersonGroupTree> children = Lists.newArrayList();

    private List<PersonUserGroupRole> users = Lists.newArrayList();

    public List<PersonGroupTree> getChildren() {
        return children;
    }

    public void setChildren(List<PersonGroupTree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getCode() {
        return super.getCode();
    }

    @Override
    public void setCode(String code) {
        super.setCode(code);
    }

    @Override
    public Integer getPid() {
        return super.getPid();
    }

    @Override
    public void setPid(Integer pid) {
        super.setPid(pid);
    }

    @Override
    public Integer getStatus() {
        return super.getStatus();
    }

    @Override
    public void setStatus(Integer status) {
        super.setStatus(status);
    }

    @Override
    public Integer getOrder() {
        return super.getOrder();
    }

    @Override
    public void setOrder(Integer order) {
        super.setOrder(order);
    }
}
