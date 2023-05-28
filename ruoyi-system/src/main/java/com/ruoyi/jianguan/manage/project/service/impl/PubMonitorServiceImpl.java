package com.ruoyi.jianguan.manage.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
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
import java.util.Collection;

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
     * 新增设备监控
     */
    @Override
    public Boolean insertByBo(PubMonitorBo bo) {
        PubMonitor add = BeanUtil.toBean(bo, PubMonitor.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改设备监控
     */
    @Override
    public Boolean updateByBo(PubMonitorBo bo) {
        PubMonitor update = BeanUtil.toBean(bo, PubMonitor.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PubMonitor entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除设备监控
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

}
