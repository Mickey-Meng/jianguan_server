package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper.BaseMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.mapper.CiBridgeWorkMapper;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model.CiBridgeWork;
import com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.service.ICiBridgeService;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class CiBridgeServiceImpl extends BaseServiceImpl<CiBridgeWork> implements ICiBridgeService
{
    @Autowired
    private CiBridgeWorkMapper ciBridgeWorkMapper;

    @Override
    public BaseMapper<CiBridgeWork> getBaseMapper() {
        return this.ciBridgeWorkMapper;
    }
}
