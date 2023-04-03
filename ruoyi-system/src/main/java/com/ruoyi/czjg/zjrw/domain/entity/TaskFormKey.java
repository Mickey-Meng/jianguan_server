package com.ruoyi.czjg.zjrw.domain.entity;

/**
 * @Author : Chen_ZhiWei
 * @Date : Create file in 2022/5/25 14:44
 * @Version : 1.0
 * @Description :
 **/
public class TaskFormKey {

    private String routerName;

    private boolean readonly;

    private String groupType;

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
