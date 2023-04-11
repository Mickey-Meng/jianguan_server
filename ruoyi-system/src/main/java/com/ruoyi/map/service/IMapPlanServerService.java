package com.ruoyi.map.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.map.domain.bo.MapPlanServerBo;
import com.ruoyi.map.domain.vo.MapPlanServerVo;
import com.ruoyi.map.domain.vo.MapServerConfigVo;

import java.util.Collection;

/**
 * 地图方案服务关联Service接口
 *
 * @author ruoyi
 * @date 2023-04-10
 */
public interface IMapPlanServerService {

    /**
     * 批量导入地图方案服务关联
     */
    Boolean importBatchByBo(MapPlanServerBo bo);

    /**
     * 校验并批量删除地图方案服务关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 根据地图方案ID查询关联地图服务列表
     * @param planId 地图方案ID
     * @param pageQuery
     * @return
     */
    TableDataInfo<MapPlanServerVo> queryPageList(Long planId, PageQuery pageQuery);
}
