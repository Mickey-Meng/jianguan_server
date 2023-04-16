package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.BaseMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.CiLandscapeGreeningWorkMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.CiLandscapeGreeningWork;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.ICiLandScapeGreeningWorkService;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class CiLandScapeGreeningWorkServiceImpl extends BaseServiceImpl<CiLandscapeGreeningWork> implements ICiLandScapeGreeningWorkService
{
    @Autowired
    private CiLandscapeGreeningWorkMapper ciLandscapeGreeningWorkMapper;

    @Override
    public BaseMapper<CiLandscapeGreeningWork> getBaseMapper() {
        return this.ciLandscapeGreeningWorkMapper;
    }
}
