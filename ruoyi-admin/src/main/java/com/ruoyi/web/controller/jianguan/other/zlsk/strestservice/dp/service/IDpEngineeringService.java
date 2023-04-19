package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service;

import com.alibaba.fastjson.*;
import java.util.*;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.DpEngineering;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.DpEngineeringCost;
import javax.servlet.http.*;

public interface IDpEngineeringService
{
    JSONObject modelList();

    Integer createEngineering(final DpEngineering p0);

    String deleteEngineering(final Integer p0);

    List<DpEngineering> getEngineeringList();

    JSONObject getTotalCost(final Integer p0);

    Integer changeTotalCost(final DpEngineeringCost p0);

    void outExcel(final Integer p0, final HttpServletResponse p1);

    DpEngineeringCost getRate(final Integer p0);
}
