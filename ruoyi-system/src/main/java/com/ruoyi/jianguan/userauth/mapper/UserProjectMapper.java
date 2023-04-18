package com.ruoyi.jianguan.userauth.mapper;

import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.jianguan.userauth.domain.entity.UserProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProjectMapper extends BaseMapperPlus<UserProjectMapper, UserProject, UserProject> {

    void deleteWorkAreaByUserIds(@Param("userIds") List<Integer> userIds, @Param("projectId") Integer projectId);
}
