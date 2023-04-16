package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Dic;
import org.apache.ibatis.annotations.*;

public interface DicMapper
{
    int insert(final Dic p0);

    List<Map<String, Object>> select(final Dic p0);

    List<Map<String, Object>> selectByKey(@Param("parentkey") final String p0, @Param("key") final String p1, @Param("value") final String p2);

    int update(final Dic p0);

    int delete(final int p0);
}
