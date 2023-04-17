package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Groups;

import java.util.*;

public interface GroupsMapper
{
    List<Map<String, Object>> select(final Groups p0);

    List<Map<String, Object>> getGroupCode(final Groups p0);

    int insert(final Groups p0);

    int update(final Groups p0);

    int delete(final int p0);
}
