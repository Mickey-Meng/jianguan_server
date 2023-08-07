package com.ruoyi.project.ledgerChangeDetail.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.ledgerChangeDetail.domain.MeaLedgerChangeDetail;
import com.ruoyi.project.ledgerChangeDetail.domain.bo.MeaLedgerChangeDetailBo;
import com.ruoyi.project.ledgerChangeDetail.domain.vo.MeaLedgerChangeDetailVo;
import com.ruoyi.project.ledgerChangeDetail.mapper.MeaLedgerChangeDetailMapper;
import com.ruoyi.project.ledgerChangeDetail.service.IMeaLedgerChangeDetailService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 台账变更/工程变更 明细Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@RequiredArgsConstructor
@Service
public class MeaLedgerChangeDetailServiceImpl implements IMeaLedgerChangeDetailService {

    private final MeaLedgerChangeDetailMapper baseMapper;

    /**
     * 查询台账变更/工程变更 明细
     */
    @Override
    public MeaLedgerChangeDetailVo queryById(String zmh){
        return baseMapper.selectVoById(zmh);
    }

    /**
     * 查询台账变更/工程变更 明细列表
     */
    @Override
    public TableDataInfo<MeaLedgerChangeDetailVo> queryPageList(MeaLedgerChangeDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaLedgerChangeDetail> lqw = buildQueryWrapper(bo);
        Page<MeaLedgerChangeDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询台账变更/工程变更 明细列表
     */
    @Override
    public List<MeaLedgerChangeDetailVo> queryList(MeaLedgerChangeDetailBo bo) {
        LambdaQueryWrapper<MeaLedgerChangeDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaLedgerChangeDetail> buildQueryWrapper(MeaLedgerChangeDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaLedgerChangeDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaLedgerChangeDetail::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getBgbh()), MeaLedgerChangeDetail::getBgbh, bo.getBgbh());
        lqw.eq(StringUtils.isNotBlank(bo.getZmmc()), MeaLedgerChangeDetail::getZmmc, bo.getZmmc());
        lqw.eq(StringUtils.isNotBlank(bo.getGcbw()), MeaLedgerChangeDetail::getGcbw, bo.getGcbw());
        lqw.eq(StringUtils.isNotBlank(bo.getDw()), MeaLedgerChangeDetail::getDw, bo.getDw());
        lqw.eq(bo.getHtdj() != null, MeaLedgerChangeDetail::getHtdj, bo.getHtdj());
        lqw.eq(bo.getXzdj() != null, MeaLedgerChangeDetail::getXzdj, bo.getXzdj());
        lqw.eq(bo.getHtsl() != null, MeaLedgerChangeDetail::getHtsl, bo.getHtsl());
        lqw.eq(bo.getHtje() != null, MeaLedgerChangeDetail::getHtje, bo.getHtje());
        lqw.eq(bo.getShsl() != null, MeaLedgerChangeDetail::getShsl, bo.getShsl());
        lqw.eq(bo.getShje() != null, MeaLedgerChangeDetail::getShje, bo.getShje());
        lqw.eq(bo.getBgsl() != null, MeaLedgerChangeDetail::getBgsl, bo.getBgsl());
        lqw.eq(bo.getBgje() != null, MeaLedgerChangeDetail::getBgje, bo.getBgje());
        lqw.eq(bo.getYjlsl() != null, MeaLedgerChangeDetail::getYjlsl, bo.getYjlsl());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaLedgerChangeDetail::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增台账变更/工程变更 明细
     */
    @Override
    public Boolean insertByBo(MeaLedgerChangeDetailBo bo) {
        MeaLedgerChangeDetail add = BeanUtil.toBean(bo, MeaLedgerChangeDetail.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setZmh(add.getZmh());
        }
        return flag;
    }

    /**
     * 修改台账变更/工程变更 明细
     */
    @Override
    public Boolean updateByBo(MeaLedgerChangeDetailBo bo) {
        MeaLedgerChangeDetail update = BeanUtil.toBean(bo, MeaLedgerChangeDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaLedgerChangeDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除台账变更/工程变更 明细
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
