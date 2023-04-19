package com.ruoyi.jianguan.manage.map.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.TreeBuildUtils;
import com.ruoyi.jianguan.manage.map.domain.bo.MapPlanBo;
import com.ruoyi.jianguan.manage.map.domain.vo.MapPlanVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.jianguan.manage.map.domain.MapPlan;
import com.ruoyi.jianguan.manage.map.mapper.MapPlanMapper;
import com.ruoyi.jianguan.manage.map.service.IMapPlanService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 地图方案管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class MapPlanServiceImpl implements IMapPlanService {

    private final MapPlanMapper baseMapper;

    /**
     * 查询地图方案管理
     */
    @Override
    public MapPlanVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询地图方案管理列表
     */
    @Override
    public TableDataInfo<MapPlanVo> queryPageList(MapPlanBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MapPlan> lqw = buildQueryWrapper(bo);
        Page<MapPlanVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询地图方案管理列表
     */
    @Override
    public List<MapPlanVo> queryList(MapPlanBo bo) {
        LambdaQueryWrapper<MapPlan> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MapPlan> buildQueryWrapper(MapPlanBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MapPlan> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getPlanName()), MapPlan::getPlanName, bo.getPlanName());
        lqw.eq(bo.getParentId() != null, MapPlan::getParentId, bo.getParentId());
        lqw.eq(bo.getLevel() != null, MapPlan::getLevel, bo.getLevel());
        lqw.eq(StringUtils.isNotBlank(bo.getGroup()), MapPlan::getGroup, bo.getGroup());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MapPlan::getStatus, bo.getStatus());
        lqw.eq(bo.getOrderNumber() != null, MapPlan::getOrderNumber, bo.getOrderNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getGroupType()), MapPlan::getGroupType, bo.getGroupType());
        return lqw;
    }

    /**
     * 新增地图方案管理
     */
    @Override
    public Boolean insertByBo(MapPlanBo bo) {
        MapPlan add = BeanUtil.toBean(bo, MapPlan.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改地图方案管理
     */
    @Override
    public Boolean updateByBo(MapPlanBo bo) {
        MapPlan update = BeanUtil.toBean(bo, MapPlan.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MapPlan entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除地图方案管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 查询地图方案管理树列表
     * @param bo
     * @return
     */
    @Override
    public List<Tree<Long>> getMapPlanTree(MapPlanBo mapPlanBo) {
        LambdaQueryWrapper<MapPlan> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ObjectUtil.isNotNull(mapPlanBo.getId()), MapPlan::getId, mapPlanBo.getId())
            .eq(ObjectUtil.isNotNull(mapPlanBo.getParentId()), MapPlan::getParentId, mapPlanBo.getParentId())
            .like(StringUtils.isNotBlank(mapPlanBo.getPlanName()), MapPlan::getPlanName, mapPlanBo.getPlanName())
            .eq(StringUtils.isNotBlank(mapPlanBo.getStatus()), MapPlan::getStatus, mapPlanBo.getStatus())
            .orderByAsc(MapPlan::getParentId)
            .orderByAsc(MapPlan::getOrderNumber);
        List<MapPlan> mapPlanList = baseMapper.selectList(lqw);
        return buildMapPlanTree(mapPlanList);
    }

    public List<Tree<Long>> buildMapPlanTree(List<MapPlan> mapPlanList) {
        List<MapPlan> buildMapPlanList = CollUtil.newArrayList();
        // 设置顶级树节点
        MapPlan topMapPlan = new MapPlan();
        topMapPlan.setId(0L)
            .setParentId(-1L)
            .setPlanName("顶级方案")
            .setOrderNumber(0L);
        buildMapPlanList.add(topMapPlan);
        buildMapPlanList.addAll(mapPlanList);

        return TreeBuildUtils.build(buildMapPlanList, (mapPlan, tree) ->
            tree.setId(mapPlan.getId())
                .setParentId(mapPlan.getParentId())
                .setName(mapPlan.getPlanName())
                .setWeight(mapPlan.getOrderNumber()));
    }
}
