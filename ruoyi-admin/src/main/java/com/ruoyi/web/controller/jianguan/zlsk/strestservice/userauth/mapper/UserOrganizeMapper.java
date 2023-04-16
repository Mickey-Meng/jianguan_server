package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.UserOrganize;
import org.apache.ibatis.annotations.*;

public interface UserOrganizeMapper
{
    List<Map<String, Object>> select(final UserOrganize p0);

    int selectTotal(final UserOrganize p0);

    int insert(final UserOrganize p0);

    int update(final UserOrganize p0);

    int delete(@Param("id") final int p0);
}
