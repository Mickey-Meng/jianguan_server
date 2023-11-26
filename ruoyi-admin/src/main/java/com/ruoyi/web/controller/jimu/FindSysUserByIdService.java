package com.ruoyi.web.controller.jimu;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.framework.config.UserServiceHandler;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindSysUserByIdService implements UserServiceHandler {

    @Autowired
    private ISysUserService iSysUserService;

    @Override
    public SysUser selectUserById(Long userId) {
        SysUser sysUser = iSysUserService.selectUserById(userId);
        return sysUser;
    }
}
