package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.DpOtherCost;
import java.util.*;

public interface IDpOtherCostService extends BaseService<DpOtherCost>
{
    List<DpOtherCost> workList(final Integer p0, final Integer p1);
}
