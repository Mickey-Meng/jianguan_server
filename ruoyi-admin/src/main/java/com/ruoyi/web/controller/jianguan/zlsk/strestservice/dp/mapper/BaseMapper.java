package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper;

import java.util.*;

public interface BaseMapper<Entity>
{
    List<Entity> selectByEngineeringId(final Integer p0);

    Integer insert(final Entity p0);

    Integer updateById(final Entity p0);

    Integer deleteByIds(final List<Integer> p0);
}
