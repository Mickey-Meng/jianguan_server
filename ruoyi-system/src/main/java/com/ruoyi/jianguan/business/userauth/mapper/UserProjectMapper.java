package com.ruoyi.jianguan.business.userauth.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.jianguan.business.userauth.domain.entity.UserProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProjectMapper extends BaseMapperPlus<UserProjectMapper, UserProject, UserProject> {

    void deleteWorkAreaByUserIds(@Param("userIds") List<Integer> userIds, @Param("projectId") Integer projectId);

    List<SysUser> selectUserListByProjectId(@Param("projectId") Long projectId);
}
