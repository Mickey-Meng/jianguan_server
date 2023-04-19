package com.ruoyi.jianguan.manage.map.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.jianguan.manage.map.domain.MapServerConfig;
import com.ruoyi.jianguan.manage.map.domain.bo.MapServerConfigBo;
import com.ruoyi.jianguan.manage.map.domain.vo.MapServerConfigVo;
import com.ruoyi.jianguan.manage.map.mapper.MapServerConfigMapper;
import com.ruoyi.jianguan.manage.map.service.IMapServerConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 地图服务注册Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class MapServerConfigServiceImpl implements IMapServerConfigService {

    private final MapServerConfigMapper baseMapper;

    /**
     * 查询地图服务注册
     */
    @Override
    public MapServerConfigVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询地图服务注册列表
     */
    @Override
    public TableDataInfo<MapServerConfigVo> queryPageList(MapServerConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MapServerConfig> lqw = buildQueryWrapper(bo);
        Page<MapServerConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询地图服务注册列表
     */
    @Override
    public List<MapServerConfigVo> queryList(MapServerConfigBo bo) {
        LambdaQueryWrapper<MapServerConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MapServerConfig> buildQueryWrapper(MapServerConfigBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MapServerConfig> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getServerName()), MapServerConfig::getServerName, bo.getServerName());
        lqw.eq(StringUtils.isNotBlank(bo.getServerCode()), MapServerConfig::getServerCode, bo.getServerCode());
        lqw.eq(StringUtils.isNotBlank(bo.getServerUrl()), MapServerConfig::getServerUrl, bo.getServerUrl());
        lqw.in(StringUtils.isNotBlank(bo.getServerType()), MapServerConfig::getServerType,
            Arrays.asList(StringUtils.isNotBlank(bo.getServerType()) ? bo.getServerType().split(",") : new String[0]));
        lqw.eq(bo.getVisiable() != null, MapServerConfig::getVisiable, bo.getVisiable());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MapServerConfig::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrbuites()), MapServerConfig::getAttrbuites, bo.getAttrbuites());
        lqw.eq(StringUtils.isNotBlank(bo.getThumbnail()), MapServerConfig::getThumbnail, bo.getThumbnail());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), MapServerConfig::getDescription, bo.getDescription());
        return lqw;
    }

    /**
     * 新增地图服务注册
     */
    @Override
    public Boolean insertByBo(MapServerConfigBo bo) {
        MapServerConfig add = BeanUtil.toBean(bo, MapServerConfig.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改地图服务注册
     */
    @Override
    public Boolean updateByBo(MapServerConfigBo bo) {
        MapServerConfig update = BeanUtil.toBean(bo, MapServerConfig.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MapServerConfig entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除地图服务注册
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 修改状态
     * @param bo
     * @return
     */
    @Override
    public int updateServerStatus(MapServerConfigBo bo) {
        MapServerConfig update = BeanUtil.toBean(bo, MapServerConfig.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) ;
    }

    @Override
    public List<MapServerConfigVo> queryListVoByTypes(String serverType) {
        MapServerConfigBo bo =new MapServerConfigBo();
        bo.setServerType(serverType);
        LambdaQueryWrapper<MapServerConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw, MapServerConfigVo.class);
    }

}
