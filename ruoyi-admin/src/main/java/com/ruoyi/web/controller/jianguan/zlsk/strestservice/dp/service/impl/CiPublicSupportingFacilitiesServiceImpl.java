package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.BaseMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.CiPublicSupportingFacilitiesMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.CiPublicSupportingFacilities;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.ICiPublicSupportingFacilitiesService;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class CiPublicSupportingFacilitiesServiceImpl extends BaseServiceImpl<CiPublicSupportingFacilities> implements ICiPublicSupportingFacilitiesService
{
    @Autowired
    private CiPublicSupportingFacilitiesMapper ciPublicSupportingFacilitiesMapper;

    @Override
    public BaseMapper<CiPublicSupportingFacilities> getBaseMapper() {
        return this.ciPublicSupportingFacilitiesMapper;
    }
}
