package com.ruoyi.jianguan.manage.map.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.jianguan.manage.map.domain.vo.MapPlanServerVo;
import com.ruoyi.jianguan.manage.map.domain.MapPlanServer;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

/**
 * 地图方案服务关联Mapper接口
 *
 * @author ruoyi
 * @date 2023-04-10
 */
public interface MapPlanServerMapper extends BaseMapperPlus<MapPlanServerMapper, MapPlanServer, MapPlanServerVo> {

    /**
     * 根据地图方案ID查询关联地图服务列表
     * @param planId 地图方案ID
     * @param page
     * @return
     */
    IPage<MapPlanServerVo> selectVoPageByPlanId(@Param("planId") Long planId, IPage<MapPlanServerVo> page);
}
