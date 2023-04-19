package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.Roles;

import java.util.*;

public interface RolesMapper
{
    List<Map<String, Object>> select(final Roles p0);

    int delete(final int p0);

    int insert(final Roles p0);

    int update(final Roles p0);
}
