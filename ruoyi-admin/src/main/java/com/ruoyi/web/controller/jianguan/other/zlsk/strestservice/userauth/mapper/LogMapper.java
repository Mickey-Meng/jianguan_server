package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.userauth.model.Log;
import org.apache.ibatis.annotations.*;

public interface LogMapper
{
    List<Map<String, Object>> select(final Log p0);

    int selectTotal(final Log p0);

    int insert(final Log p0);

    int update(final Log p0);

    int delete(@Param("id") final int p0);
}
