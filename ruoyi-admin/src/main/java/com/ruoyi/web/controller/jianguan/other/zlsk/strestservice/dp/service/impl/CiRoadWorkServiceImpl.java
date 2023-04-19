package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper.BaseMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper.CiRoadWorkMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.CiRoadWork;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service.ICiRoadWorkService;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class CiRoadWorkServiceImpl extends BaseServiceImpl<CiRoadWork> implements ICiRoadWorkService
{
    @Autowired
    private CiRoadWorkMapper ciRoadWorkMapper;

    @Override
    public BaseMapper<CiRoadWork> getBaseMapper() {
        return this.ciRoadWorkMapper;
    }
}
