package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.CiPlacementHouseBuilding;
import com.alibaba.fastjson.*;

public interface ICiPlacementHouseBuildingService extends BaseService<CiPlacementHouseBuilding>
{
    JSONObject houseWorkList(final Integer p0);
}