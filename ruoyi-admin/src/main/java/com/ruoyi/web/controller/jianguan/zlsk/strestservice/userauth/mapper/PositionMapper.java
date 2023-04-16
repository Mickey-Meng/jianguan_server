package com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.userauth.model.Position;
import org.apache.ibatis.annotations.*;

public interface PositionMapper
{
    List<Map<String, Object>> select(final Position p0);

    int insert(final Position p0);

    int update(final Position p0);

    int delete(@Param("id") final int p0);

    int deleteChildren(@Param("id") final int p0);
}
