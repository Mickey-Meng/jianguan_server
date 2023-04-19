package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper.BaseMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper.CiRiverRegulationMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.CiRiverRegulation;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service.ICiRiverRegulationService;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class CiRiverRegulationServiceImpl extends BaseServiceImpl<CiRiverRegulation> implements ICiRiverRegulationService
{
    @Autowired
    private CiRiverRegulationMapper ciRiverRegulationMapper;

    @Override
    public BaseMapper<CiRiverRegulation> getBaseMapper() {
        return this.ciRiverRegulationMapper;
    }
}
