package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.LadCost;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.dto.LadCostInfoDTO;
import java.util.*;

public interface ILadCostService
{
    LadCostInfoDTO getList(final Integer p0);

    String keepLadInfo(final LadCostInfoDTO p0);

    List<LadCost> addLad(final List<LadCost> p0);
}
