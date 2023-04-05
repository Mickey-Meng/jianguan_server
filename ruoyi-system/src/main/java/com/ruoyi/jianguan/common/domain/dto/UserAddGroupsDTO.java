package com.ruoyi.jianguan.common.domain.dto;

import java.util.List;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/9 13:42
 * @description : 用户增加工区权限
 **/
public class UserAddGroupsDTO {

    private Integer projectId;

    private List<Integer> userIds;

    private List<Integer> groupsIds;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getGroupsIds() {
        return groupsIds;
    }

    public void setGroupsIds(List<Integer> groupsIds) {
        this.groupsIds = groupsIds;
    }

    @Override
    public String toString() {
        return "UserAddGroupsDTO{" +
                "projectId=" + projectId +
                ", userIds=" + userIds +
                ", groupsIds=" + groupsIds +
                '}';
    }
}
