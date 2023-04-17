package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.impl;

import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.CiLandScapeGreeningWorkEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.CiPublicSupportingFacilitiesEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.EarthAndStoneEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums.ProjectNameEnum;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.mapper.BaseMapper;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.BaseEntity;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.CiEarthStoneWork;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.CiLandscapeGreeningWork;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model.CiPublicSupportingFacilities;
import com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.service.BaseService;
import org.springframework.stereotype.*;
import java.util.*;

@Component
public abstract class BaseServiceImpl<Entity extends BaseEntity> implements BaseService<Entity>
{
    public abstract BaseMapper<Entity> getBaseMapper();

    @Override
    public List<Entity> workList(final Integer engineeringId) {
        return this.getBaseMapper().selectByEngineeringId(engineeringId);
    }

    @Override
    public List<Entity> workList(final Integer engineeringId, final String modelName) {
        final List<Entity> entities = this.getBaseMapper().selectByEngineeringId(engineeringId);
        if (entities.size() != 0) {
            return this.workList(engineeringId);
        }
        final ProjectNameEnum nameEnum = ProjectNameEnum.valueOf(modelName);
        switch (nameEnum) {
            case EARTH_STONE: {
                final CiEarthStoneWork ciEarthStoneWork = new CiEarthStoneWork();
                for (final EarthAndStoneEnum value : EarthAndStoneEnum.values()) {
                    ciEarthStoneWork.setProjectName(value.getName()).setEngineeringId(engineeringId);
                    this.getBaseMapper().insert((Entity)ciEarthStoneWork);
                }
                break;
            }
            case LANDSCAPE: {
                final CiLandscapeGreeningWork ciLandscapeGreeningWork = new CiLandscapeGreeningWork();
                for (final CiLandScapeGreeningWorkEnum value2 : CiLandScapeGreeningWorkEnum.values()) {
                    ciLandscapeGreeningWork.setProjectName(value2.getName()).setEngineeringId(engineeringId);
                    this.getBaseMapper().insert((Entity)ciLandscapeGreeningWork);
                }
                break;
            }
            case FACILITIES: {
                final CiPublicSupportingFacilities ciPublicSupportingFacilities = new CiPublicSupportingFacilities();
                for (final CiPublicSupportingFacilitiesEnum value3 : CiPublicSupportingFacilitiesEnum.values()) {
                    ciPublicSupportingFacilities.setProjectName(value3.getName());
                    ciPublicSupportingFacilities.setPlotRatio(value3.getVolume());
                    ciPublicSupportingFacilities.setEngineeringId(engineeringId);
                    this.getBaseMapper().insert((Entity)ciPublicSupportingFacilities);
                }
                break;
            }
        }
        return this.getBaseMapper().selectByEngineeringId(engineeringId);
    }

    @Override
    public Integer createWork(final Entity entity) {
        int result = 1;
        if (entity.getId() == null) {
            result = this.getBaseMapper().insert(entity);
        }
        else {
            result = this.getBaseMapper().updateById(entity);
        }
        return entity.getId();
    }

    @Override
    public String deleteWorkInfo(final List<Integer> ids) {
        final Integer s = this.getBaseMapper().deleteByIds(ids);
        return "success";
    }
}
