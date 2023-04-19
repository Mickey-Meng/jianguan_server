package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper.BaseMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper.CiEarthStoneWorkMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.CiEarthStoneWork;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service.ICiEarthStoneWorkService;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class CiEarthStoneWorkServiceImpl extends BaseServiceImpl<CiEarthStoneWork> implements ICiEarthStoneWorkService
{
    @Autowired
    private CiEarthStoneWorkMapper ciEarthStoneWorkMapper;

    @Override
    public BaseMapper<CiEarthStoneWork> getBaseMapper() {
        return this.ciEarthStoneWorkMapper;
    }
}
