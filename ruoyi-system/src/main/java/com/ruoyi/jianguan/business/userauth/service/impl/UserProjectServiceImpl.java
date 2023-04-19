package com.ruoyi.jianguan.business.userauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.jianguan.business.userauth.domain.entity.UserProject;
import com.ruoyi.jianguan.business.userauth.mapper.UserProjectMapper;
import com.ruoyi.jianguan.business.userauth.service.IUserProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserProjectServiceImpl implements IUserProjectService {

    private final UserProjectMapper userProjectMapper;

    /**
     * 删除用户对应的项目下所有旧工区权限
     * @param userIds
     * @param projectId
     */
    @Override
    public void deleteWorkAreaByUserIds(List<Integer> userIds, Integer projectId) {
        userProjectMapper.deleteWorkAreaByUserIds(userIds, projectId);
    }

    /**
     * 添加相应的工区权限
     * @param userProjectList
     * @return
     */
    @Override
    public boolean batchAddWorkArea(List<UserProject> userProjectList) {
        return userProjectMapper.insertBatch(userProjectList);
    }

    /**
     * 根据用户ID查询其关联的工区信息
     * @param userId
     * @return
     */
    @Override
    public List<UserProject> getWorkAreaByUserId(Integer userId) {
        LambdaQueryWrapper<UserProject> lqw = Wrappers.lambdaQuery();
        lqw.eq(!Objects.isNull(userId), UserProject::getUserId, userId);
        return userProjectMapper.selectList(lqw);
    }
}
