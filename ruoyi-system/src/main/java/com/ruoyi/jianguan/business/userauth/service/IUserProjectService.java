package com.ruoyi.jianguan.business.userauth.service;

import com.ruoyi.jianguan.business.userauth.domain.entity.UserProject;

import java.util.List;

public interface IUserProjectService {
    /**
     * 删除用户对应的项目下所有旧工区权限
     * @param userIds
     * @param projectId
     */
    public void deleteWorkAreaByUserIds(List<Integer> userIds, Integer projectId);

    /**
     * 添加相应的工区权限
     * @param userProjectList
     * @return
     */
    public boolean batchAddWorkArea(List<UserProject> userProjectList) ;

    /**
     * 根据用户ID查询其关联的工区信息
     * @param userId
     * @return
     */
    public List<UserProject> getWorkAreaByUserId(Integer userId);

}
