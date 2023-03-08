package com.ruoyi.project.approval.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.approval.domain.MeaLedgerApprovalNo;
import com.ruoyi.project.approval.domain.bo.MeaLedgerApprovalNoBo;
import com.ruoyi.project.approval.domain.vo.MeaLedgerApprovalNoVo;
import com.ruoyi.project.approval.mapper.MeaLedgerApprovalNoMapper;
import com.ruoyi.project.approval.service.IMeaLedgerApprovalNoService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 期数管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaLedgerApprovalNoServiceImpl implements IMeaLedgerApprovalNoService {

    private final MeaLedgerApprovalNoMapper baseMapper;

    /**
     * 查询期数管理
     */
    @Override
    public MeaLedgerApprovalNoVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询期数管理列表
     */
    @Override
    public TableDataInfo<MeaLedgerApprovalNoVo> queryPageList(MeaLedgerApprovalNoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaLedgerApprovalNo> lqw = buildQueryWrapper(bo);
        lqw.orderByAsc(MeaLedgerApprovalNo::getSqqc);
        Page<MeaLedgerApprovalNoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询期数管理列表
     */
    @Override
    public List<MeaLedgerApprovalNoVo> queryList(MeaLedgerApprovalNoBo bo) {
        LambdaQueryWrapper<MeaLedgerApprovalNo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询期数管理列表
     */
    @Override
    public long count(MeaLedgerApprovalNoBo bo) {
        LambdaQueryWrapper<MeaLedgerApprovalNo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectCount(lqw);
    }

    private LambdaQueryWrapper<MeaLedgerApprovalNo> buildQueryWrapper(MeaLedgerApprovalNoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaLedgerApprovalNo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaLedgerApprovalNo::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getSqqc()), MeaLedgerApprovalNo::getSqqc, bo.getSqqc());
        lqw.eq(bo.getSbsj() != null, MeaLedgerApprovalNo::getSbsj, bo.getSbsj());
        lqw.eq(StringUtils.isNotBlank(bo.getSpzt()), MeaLedgerApprovalNo::getSpzt, bo.getSpzt());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaLedgerApprovalNo::getStatus, bo.getStatus());
        lqw.orderByAsc(MeaLedgerApprovalNo:: getSqqc);
        return lqw;
    }

    /**
     * 新增期数管理
     */
    @Override
    public Boolean insertByBo(MeaLedgerApprovalNoBo bo) {
        MeaLedgerApprovalNo add = BeanUtil.toBean(bo, MeaLedgerApprovalNo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改期数管理
     */
    @Override
    public Boolean updateByBo(MeaLedgerApprovalNoBo bo) {
        MeaLedgerApprovalNo update = BeanUtil.toBean(bo, MeaLedgerApprovalNo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaLedgerApprovalNo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除期数管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
