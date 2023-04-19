package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.UserRole;

import java.util.*;

public interface UserRoleMapper
{
    List<Map<String, Object>> select(final UserRole p0);

    int insert(final UserRole p0);

    int delete(final int p0);

    List<Map<String, Object>> selectRoleByUser(final UserRole p0);
}
