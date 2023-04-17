package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.CiPlacementHouseBuildingEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.ProjectNameEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.BaseMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.CiPlacementHouseBuildingMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.CiPlacementHouseBuilding;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.ICiPlacementHouseBuildingService;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import com.alibaba.fastjson.*;
import java.util.*;

@Service
public class CiPlacementHouseBuildingServiceImpl extends BaseServiceImpl<CiPlacementHouseBuilding> implements ICiPlacementHouseBuildingService
{
    @Autowired
    private CiPlacementHouseBuildingMapper ciPlacementHouseBuildingMapper;

    @Override
    public BaseMapper<CiPlacementHouseBuilding> getBaseMapper() {
        return this.ciPlacementHouseBuildingMapper;
    }

    @Override
    public JSONObject houseWorkList(final Integer engineeringId) {
        final List<CiPlacementHouseBuilding> ciPlacementHouseBuildings = this.ciPlacementHouseBuildingMapper.selectByEngineeringId(engineeringId);
        final JSONObject json = new JSONObject();
        json.put(ProjectNameEnum.PLACEMENT_HOUSE.name(), (Object)ciPlacementHouseBuildings);
        for (final CiPlacementHouseBuildingEnum value : CiPlacementHouseBuildingEnum.values()) {
            final Map<Double, String> map = new HashMap<Double, String>();
            map.put(value.getValue(), value.getUnit());
            json.put(value.name(), (Object)map);
        }
        return json;
    }
}
