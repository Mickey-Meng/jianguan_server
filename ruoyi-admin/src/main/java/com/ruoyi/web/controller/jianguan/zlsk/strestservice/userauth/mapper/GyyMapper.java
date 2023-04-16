package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Gyy;
import org.apache.ibatis.annotations.*;

public interface GyyMapper
{
    List<Map<String, Object>> select(final Gyy p0);

    int selectTotal(final Gyy p0);

    int insert(final Gyy p0);

    int update(final Gyy p0);

    int delete(@Param("id") final int p0);

    int deleteChildren(@Param("id") final int p0);
}
