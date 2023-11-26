package com.ruoyi.framework.config;

import com.ruoyi.common.core.domain.entity.SysUser;

public interface UserServiceHandler {
    SysUser selectUserById(Long userId);
}
