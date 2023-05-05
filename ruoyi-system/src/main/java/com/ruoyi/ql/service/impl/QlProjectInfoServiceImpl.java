package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlProjectInfoBo;
import com.ruoyi.ql.domain.vo.QlProjectInfoVo;
import com.ruoyi.ql.domain.QlProjectInfo;
import com.ruoyi.ql.mapper.QlProjectInfoMapper;
import com.ruoyi.ql.service.IQlProjectInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 项目信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlProjectInfoServiceImpl implements IQlProjectInfoService {

    private final QlProjectInfoMapper baseMapper;

    /**
     * 查询项目信息
     */
    @Override
    public QlProjectInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询项目信息列表
     */
    @Override
    public TableDataInfo<QlProjectInfoVo> queryPageList(QlProjectInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlProjectInfo> lqw = buildQueryWrapper(bo);
        Page<QlProjectInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询项目信息列表
     */
    @Override
    public List<QlProjectInfoVo> queryList(QlProjectInfoBo bo) {
        LambdaQueryWrapper<QlProjectInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlProjectInfo> buildQueryWrapper(QlProjectInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlProjectInfo> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlProjectInfo::getCustomerName, bo.getCustomerName());
        lqw.like(StringUtils.isNotBlank(bo.getProjectName()), QlProjectInfo::getProjectName, bo.getProjectName());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectType()), QlProjectInfo::getProjectType, bo.getProjectType());
        lqw.eq(bo.getProjectStartDate() != null, QlProjectInfo::getProjectStartDate, bo.getProjectStartDate());
        lqw.eq(bo.getProjectDays() != null, QlProjectInfo::getProjectDays, bo.getProjectDays());
        lqw.eq(bo.getDeptId() != null, QlProjectInfo::getDeptId, bo.getDeptId());
        lqw.eq(StringUtils.isNotBlank(bo.getArea()), QlProjectInfo::getArea, bo.getArea());
        return lqw;
    }

    /**
     * 新增项目信息
     */
    @Override
    public Boolean insertByBo(QlProjectInfoBo bo) {
        QlProjectInfo add = BeanUtil.toBean(bo, QlProjectInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改项目信息
     */
    @Override
    public Boolean updateByBo(QlProjectInfoBo bo) {
        QlProjectInfo update = BeanUtil.toBean(bo, QlProjectInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlProjectInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除项目信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
