package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.DpEngineering;

import java.util.*;

public interface DpEngineeringMapper
{
    Integer insert(final DpEngineering p0);

    Integer deleteById(final Integer p0);

    Integer updateById(final DpEngineering p0);

    List<DpEngineering> selectList();

    Double selectCiCost(final Integer p0);

    String selectSite(final Integer p0);

    DpEngineering selectById(final Integer p0);
}
