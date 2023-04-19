package com.ruoyi.jianguan.manage.map.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.jianguan.manage.map.domain.bo.MapPlanServerBo;
import com.ruoyi.jianguan.manage.map.domain.vo.MapPlanServerVo;
import com.ruoyi.jianguan.manage.map.mapper.MapServerConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.jianguan.manage.map.domain.MapPlanServer;
import com.ruoyi.jianguan.manage.map.mapper.MapPlanServerMapper;
import com.ruoyi.jianguan.manage.map.service.IMapPlanServerService;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 地图方案服务关联Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@RequiredArgsConstructor
@Service
public class MapPlanServerServiceImpl implements IMapPlanServerService {

    private final MapPlanServerMapper mapPlanServerMapper;
    private final MapServerConfigMapper mapServerConfigMapper;

    /**
     * 新增地图方案服务关联
     */
    @Override
    public Boolean importBatchByBo(MapPlanServerBo bo) {
        //validEntityBeforeSave(add);
        // 组装方案服务数据
        List<MapPlanServer> mapPlanServerList = Arrays.stream(Convert.toStrArray(bo.getServerIds())).map(serverId -> {
            return new MapPlanServer(bo.getPlanId(), Long.valueOf(serverId));
        }).collect(Collectors.toList());
        return mapPlanServerMapper.insertBatch(mapPlanServerList);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MapPlanServer entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除地图方案服务关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return mapPlanServerMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 根据地图方案ID查询关联地图服务列表
     * @param planId 地图方案ID
     * @param pageQuery
     * @return
     */
    @Override
    public TableDataInfo<MapPlanServerVo> queryPageList(Long planId, PageQuery pageQuery) {
        IPage<MapPlanServerVo> result = mapPlanServerMapper.selectVoPageByPlanId(planId, pageQuery.build());
        /**
         * 优化为自定义SQL数据查询
         *
        // 1.查询地图方案ID关联的地图服务ID集合
        LambdaQueryWrapper<MapPlanServer> lqw1 = Wrappers.lambdaQuery();
        lqw1.eq(planId > 0, MapPlanServer::getPlanId, planId);
        List<MapPlanServerVo> mapPlanServerList = mapPlanServerMapper.selectVoList(lqw1);
        // 2.根据地图服务ID集合查询地图服务分页数据
        IPage<MapServerConfigVo> result = pageQuery.build();
        if (!mapPlanServerList.isEmpty()) {
            // mapPlanServerList.stream().collect(Collectors.toMap(MapPlanServerVo::getId, MapPlanServerVo.class));
            List<Long> serverIdList = mapPlanServerList.stream().map(MapPlanServerVo::getServerId).collect(Collectors.toList());
            LambdaQueryWrapper<MapServerConfig> lqw2 = Wrappers.lambdaQuery();
            lqw2.in(!serverIdList.isEmpty(), MapServerConfig::getId, serverIdList);
            result = mapServerConfigMapper.selectVoPage(pageQuery.build(), lqw2);
        }
         **/
        return TableDataInfo.build(result);
    }
}
