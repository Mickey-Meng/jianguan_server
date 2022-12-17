package com.ruoyi.project.ledger.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import com.ruoyi.project.ledger.domain.bo.MeaLedgerBreakdownDetailBo;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownDetailMapper;
import com.ruoyi.project.ledger.service.IMeaLedgerBreakdownDetailService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 台账分解明细Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-04s
 */
@RequiredArgsConstructor
@Service
public class MeaLedgerBreakdownDetailServiceImpl implements IMeaLedgerBreakdownDetailService {

    private final MeaLedgerBreakdownDetailMapper baseMapper;

    /**
     * 查询台账分解明细
     */
    @Override
    public MeaLedgerBreakdownDetailVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询台账分解明细列表
     */
    @Override
    public TableDataInfo<MeaLedgerBreakdownDetailVo> queryPageList(MeaLedgerBreakdownDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = buildQueryWrapper(bo);
        Page<MeaLedgerBreakdownDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询台账分解明细列表
     */
    @Override
    public List<MeaLedgerBreakdownDetailVo> queryList(MeaLedgerBreakdownDetailBo bo) {
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaLedgerBreakdownDetail> buildQueryWrapper(MeaLedgerBreakdownDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaLedgerBreakdownDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaLedgerBreakdownDetail::getBdbh, bo.getBdbh());
        lqw.likeRight(StringUtils.isNotBlank(bo.getTzfjbh()), MeaLedgerBreakdownDetail::getTzfjbh, bo.getTzfjbh());
        lqw.eq(StringUtils.isNotBlank(bo.getFjmulu()), MeaLedgerBreakdownDetail::getFjmulu, bo.getFjmulu());
        lqw.eq(StringUtils.isNotBlank(bo.getZmh()), MeaLedgerBreakdownDetail::getZmh, bo.getZmh());
        lqw.eq(StringUtils.isNotBlank(bo.getZmmc()), MeaLedgerBreakdownDetail::getZmmc, bo.getZmmc());
        lqw.eq(StringUtils.isNotBlank(bo.getDw()), MeaLedgerBreakdownDetail::getDw, bo.getDw());
        lqw.eq(StringUtils.isNotBlank(bo.getReviewCode()), MeaLedgerBreakdownDetail::getReviewCode, bo.getReviewCode());
        lqw.eq(bo.getHtdj() != null, MeaLedgerBreakdownDetail::getHtdj, bo.getHtdj());
        lqw.eq(bo.getSjsl() != null, MeaLedgerBreakdownDetail::getSjsl, bo.getSjsl());
        lqw.eq(bo.getFjsl() != null, MeaLedgerBreakdownDetail::getFjsl, bo.getFjsl());
        lqw.eq(bo.getBgsl() != null, MeaLedgerBreakdownDetail::getBgsl, bo.getBgsl());
        lqw.eq(bo.getFhsl() != null, MeaLedgerBreakdownDetail::getFhsl, bo.getFhsl());
        lqw.eq(bo.getYjlsl() != null, MeaLedgerBreakdownDetail::getYjlsl, bo.getYjlsl());
        lqw.eq(bo.getFhje() != null, MeaLedgerBreakdownDetail::getFhje, bo.getFhje());
        lqw.eq(StringUtils.isNotBlank(bo.getFjlx()), MeaLedgerBreakdownDetail::getFjlx, bo.getFjlx());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaLedgerBreakdownDetail::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增台账分解明细
     */
    @Override
    public Boolean insertByBo(MeaLedgerBreakdownDetailBo bo) {
        MeaLedgerBreakdownDetail add = BeanUtil.toBean(bo, MeaLedgerBreakdownDetail.class);
        validEntityBeforeSave(add);
        add.setId(null);
        add.setReviewCode("1");
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改台账分解明细
     */
    @Override
    public Boolean updateByBo(MeaLedgerBreakdownDetailBo bo) {
        MeaLedgerBreakdownDetail update = BeanUtil.toBean(bo, MeaLedgerBreakdownDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaLedgerBreakdownDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除台账分解明细
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
