package com.ruoyi.jianguan.manage.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.jianguan.manage.project.domain.bo.JgProjectItemBo;
import com.ruoyi.jianguan.manage.project.domain.entity.JgProjectItem;
import com.ruoyi.jianguan.manage.project.domain.vo.JgProjectItemVo;
import com.ruoyi.jianguan.manage.project.mapper.JgProjectItemMapper;
import com.ruoyi.jianguan.manage.project.service.IJgProjectItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 项目详情Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-08
 */
@RequiredArgsConstructor
@Service
public class JgProjectItemServiceImpl implements IJgProjectItemService {

    private final JgProjectItemMapper baseMapper;

    /**
     * 查询项目详情
     */
    @Override
    public JgProjectItemVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询项目详情列表
     */
    @Override
    public TableDataInfo<JgProjectItemVo> queryPageList(JgProjectItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<JgProjectItem> lqw = buildQueryWrapper(bo);
        Page<JgProjectItemVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询项目详情列表
     */
    @Override
    public List<JgProjectItemVo> queryList(JgProjectItemBo bo) {
        LambdaQueryWrapper<JgProjectItem> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<JgProjectItem> buildQueryWrapper(JgProjectItemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<JgProjectItem> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProjectId() != null, JgProjectItem::getProjectId, bo.getProjectId());
        lqw.eq(StringUtils.isNotBlank(bo.getManageDept()), JgProjectItem::getManageDept, bo.getManageDept());
        lqw.eq(StringUtils.isNotBlank(bo.getBuildDept()), JgProjectItem::getBuildDept, bo.getBuildDept());
        lqw.eq(StringUtils.isNotBlank(bo.getDesginDept()), JgProjectItem::getDesginDept, bo.getDesginDept());
        lqw.eq(StringUtils.isNotBlank(bo.getConstructDept()), JgProjectItem::getConstructDept, bo.getConstructDept());
        lqw.eq(StringUtils.isNotBlank(bo.getSupervisorDept()), JgProjectItem::getSupervisorDept, bo.getSupervisorDept());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectScale()), JgProjectItem::getProjectScale, bo.getProjectScale());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectDuration()), JgProjectItem::getProjectDuration, bo.getProjectDuration());
        lqw.eq(StringUtils.isNotBlank(bo.getInputsCale()), JgProjectItem::getInputsCale, bo.getInputsCale());
        lqw.eq(bo.getStartTime() != null, JgProjectItem::getStartTime, bo.getStartTime());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectCode()), JgProjectItem::getProjectCode, bo.getProjectCode());
        lqw.eq(StringUtils.isNotBlank(bo.getAuditUnit()), JgProjectItem::getAuditUnit, bo.getAuditUnit());
        lqw.like(StringUtils.isNotBlank(bo.getProjectName()), JgProjectItem::getProjectName, bo.getProjectName());
        lqw.eq(StringUtils.isNotBlank(bo.getInvestmentProjectOverview()), JgProjectItem::getInvestmentProjectOverview, bo.getInvestmentProjectOverview());
        lqw.eq(StringUtils.isNotBlank(bo.getCompactionResponsibility()), JgProjectItem::getCompactionResponsibility, bo.getCompactionResponsibility());
        lqw.eq(StringUtils.isNotBlank(bo.getImplementGuarantee()), JgProjectItem::getImplementGuarantee, bo.getImplementGuarantee());
        lqw.eq(StringUtils.isNotBlank(bo.getGraspProgress()), JgProjectItem::getGraspProgress, bo.getGraspProgress());
        lqw.eq(StringUtils.isNotBlank(bo.getFirstQuarter()), JgProjectItem::getFirstQuarter, bo.getFirstQuarter());
        lqw.eq(StringUtils.isNotBlank(bo.getSecondQuarter()), JgProjectItem::getSecondQuarter, bo.getSecondQuarter());
        lqw.eq(StringUtils.isNotBlank(bo.getThirdQuarter()), JgProjectItem::getThirdQuarter, bo.getThirdQuarter());
        lqw.eq(StringUtils.isNotBlank(bo.getFourthQuarter()), JgProjectItem::getFourthQuarter, bo.getFourthQuarter());
        lqw.eq(StringUtils.isNotBlank(bo.getEngineeringLayoutImage()), JgProjectItem::getEngineeringLayoutImage, bo.getEngineeringLayoutImage());
        return lqw;
    }

    /**
     * 新增项目详情
     */
    @Override
    public Boolean insertByBo(JgProjectItemBo bo) {
        JgProjectItem add = BeanUtil.toBean(bo, JgProjectItem.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改项目详情
     */
    @Override
    public Boolean updateByBo(JgProjectItemBo bo) {
        JgProjectItem update = BeanUtil.toBean(bo, JgProjectItem.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(JgProjectItem entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除项目详情
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public boolean saveProjectItem(JgProjectItemBo bo) {
        JgProjectItem projectItem = BeanUtil.toBean(bo, JgProjectItem.class);
        return baseMapper.insertOrUpdate(projectItem);
    }
}
