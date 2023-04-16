package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.UserGroup;

import java.util.*;

public interface UserGroupMapper
{
    int insert(final UserGroup p0);

    int update(final UserGroup p0);

    int delete(final int p0);

    List<Map<String, Object>> select(final UserGroup p0);
}
