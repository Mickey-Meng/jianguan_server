package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper;

import java.util.*;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.LadCost;

public interface LadCostMapper
{
    List<LadCost> selectById(final Integer p0);

    Integer updateInfo(final LadCost p0);

    Integer insert(final LadCost p0);
}
