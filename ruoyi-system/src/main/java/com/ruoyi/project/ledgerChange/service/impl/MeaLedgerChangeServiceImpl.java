package com.ruoyi.project.ledgerChange.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.ledgerChange.domain.MeaLedgerChange;
import com.ruoyi.project.ledgerChange.domain.bo.MeaLedgerChangeBo;
import com.ruoyi.project.ledgerChange.domain.vo.MeaLedgerChangeVo;
import com.ruoyi.project.ledgerChange.mapper.MeaLedgerChangeMapper;
import com.ruoyi.project.ledgerChange.service.IMeaLedgerChangeService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 台账变更/工程变更Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaLedgerChangeServiceImpl implements IMeaLedgerChangeService {

    private final MeaLedgerChangeMapper baseMapper;

    /**
     * 查询台账变更/工程变更
     */
    @Override
    public MeaLedgerChangeVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询台账变更/工程变更列表
     */
    @Override
    public TableDataInfo<MeaLedgerChangeVo> queryPageList(MeaLedgerChangeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaLedgerChange> lqw = buildQueryWrapper(bo);
        Page<MeaLedgerChangeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询台账变更/工程变更列表
     */
    @Override
    public List<MeaLedgerChangeVo> queryList(MeaLedgerChangeBo bo) {
        LambdaQueryWrapper<MeaLedgerChange> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaLedgerChange> buildQueryWrapper(MeaLedgerChangeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaLedgerChange> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBgbh()), MeaLedgerChange::getBgbh, bo.getBgbh());
        lqw.eq(StringUtils.isNotBlank(bo.getBgsx()), MeaLedgerChange::getBgsx, bo.getBgsx());
        lqw.eq(StringUtils.isNotBlank(bo.getBgdj()), MeaLedgerChange::getBgdj, bo.getBgdj());
        lqw.eq(StringUtils.isNotBlank(bo.getBglx()), MeaLedgerChange::getBglx, bo.getBglx());
        lqw.eq(StringUtils.isNotBlank(bo.getZh()), MeaLedgerChange::getZh, bo.getZh());
        lqw.eq(StringUtils.isNotBlank(bo.getZmh()), MeaLedgerChange::getZmh, bo.getZmh());
        lqw.eq(StringUtils.isNotBlank(bo.getGcbw()), MeaLedgerChange::getGcbw, bo.getGcbw());
        lqw.eq(StringUtils.isNotBlank(bo.getTh()), MeaLedgerChange::getTh, bo.getTh());
        lqw.eq(bo.getSqrq() != null, MeaLedgerChange::getSqrq, bo.getSqrq());
        lqw.eq(bo.getBgje() != null, MeaLedgerChange::getBgje, bo.getBgje());
        lqw.eq(StringUtils.isNotBlank(bo.getBgyy()), MeaLedgerChange::getBgyy, bo.getBgyy());
        lqw.eq(StringUtils.isNotBlank(bo.getJss()), MeaLedgerChange::getJss, bo.getJss());
        lqw.eq(StringUtils.isNotBlank(bo.getDataStatus()), MeaLedgerChange::getDataStatus, bo.getDataStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaLedgerChange::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增台账变更/工程变更
     */
    @Override
    public Boolean insertByBo(MeaLedgerChangeBo bo) {
        MeaLedgerChange add = BeanUtil.toBean(bo, MeaLedgerChange.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改台账变更/工程变更
     */
    @Override
    public Boolean updateByBo(MeaLedgerChangeBo bo) {
        MeaLedgerChange update = BeanUtil.toBean(bo, MeaLedgerChange.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaLedgerChange entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除台账变更/工程变更
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
