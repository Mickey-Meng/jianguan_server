package com.ruoyi.jianguan.manage.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.jianguan.manage.project.domain.bo.PubMonitorBo;
import com.ruoyi.jianguan.manage.project.domain.entity.PubMonitor;
import com.ruoyi.jianguan.manage.project.domain.vo.PubMonitorVo;
import com.ruoyi.jianguan.manage.project.mapper.PubMonitorMapper;
import com.ruoyi.jianguan.manage.project.service.IPubMonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 设备监控Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-28
 */
@RequiredArgsConstructor
@Service
public class PubMonitorServiceImpl implements IPubMonitorService {

    private final PubMonitorMapper baseMapper;

    /**
     * 查询设备监控
     */
    @Override
    public PubMonitorVo queryById(Integer id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询设备监控列表
     */
    @Override
    public TableDataInfo<PubMonitorVo> queryPageList(PubMonitorBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PubMonitor> lqw = buildQueryWrapper(bo);
        Page<PubMonitorVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询设备监控列表
     */
    @Override
    public List<PubMonitorVo> queryList(PubMonitorBo bo) {
        LambdaQueryWrapper<PubMonitor> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public Boolean saveMonitors(String projectId, List<PubMonitorBo> boList) {
        // 1、删除旧设备数据
        Map<String, Object> columnMap = Maps.newHashMap();
        columnMap.put("project_id", projectId);
        baseMapper.deleteByMap(columnMap);
        // 2、保存新设备数据
        boList.forEach(bo -> bo.setProjectId(projectId));
        List<PubMonitor> monitorList = BeanUtil.copyToList(boList, PubMonitor.class);
        return baseMapper.insertBatch(monitorList);
    }

    private LambdaQueryWrapper<PubMonitor> buildQueryWrapper(PubMonitorBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PubMonitor> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getProjectId()), PubMonitor::getProjectId, bo.getProjectId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), PubMonitor::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), PubMonitor::getType, bo.getType());
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceNo()), PubMonitor::getDeviceNo, bo.getDeviceNo());
        lqw.eq(StringUtils.isNotBlank(bo.getChannelNo()), PubMonitor::getChannelNo, bo.getChannelNo());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), PubMonitor::getUrl, bo.getUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getGeom()), PubMonitor::getGeom, bo.getGeom());
        return lqw;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PubMonitor entity){
        //TODO 做一些数据校验,如唯一约束
    }

}
