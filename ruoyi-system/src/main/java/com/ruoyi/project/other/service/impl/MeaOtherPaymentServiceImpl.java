package com.ruoyi.project.other.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.project.other.domain.MeaOtherPayment;
import com.ruoyi.project.other.domain.bo.MeaOtherPaymentBo;
import com.ruoyi.project.other.domain.vo.MeaOtherPaymentVo;
import com.ruoyi.project.other.mapper.MeaOtherPaymentMapper;
import com.ruoyi.project.other.service.IMeaOtherPaymentService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 其他款项Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@RequiredArgsConstructor
@Service
public class MeaOtherPaymentServiceImpl implements IMeaOtherPaymentService {

    private final MeaOtherPaymentMapper baseMapper;

    /**
     * 查询其他款项
     */
    @Override
    public MeaOtherPaymentVo queryById(String id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询其他款项列表
     */
    @Override
    public TableDataInfo<MeaOtherPaymentVo> queryPageList(MeaOtherPaymentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MeaOtherPayment> lqw = buildQueryWrapper(bo);
        Page<MeaOtherPaymentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询其他款项列表
     */
    @Override
    public List<MeaOtherPaymentVo> queryList(MeaOtherPaymentBo bo) {
        LambdaQueryWrapper<MeaOtherPayment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MeaOtherPayment> buildQueryWrapper(MeaOtherPaymentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MeaOtherPayment> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBdbh()), MeaOtherPayment::getBdbh, bo.getBdbh());
        lqw.eq(StringUtils.isNotBlank(bo.getJlqsbh()), MeaOtherPayment::getJlqsbh, bo.getJlqsbh());
        lqw.eq(StringUtils.isNotBlank(bo.getSqbh()), MeaOtherPayment::getSqbh, bo.getSqbh());
        lqw.eq(bo.getSqsj() != null, MeaOtherPayment::getSqsj, bo.getSqsj());
        lqw.eq(StringUtils.isNotBlank(bo.getSsdw()), MeaOtherPayment::getSsdw, bo.getSsdw());
        lqw.eq(StringUtils.isNotBlank(bo.getKxlx()), MeaOtherPayment::getKxlx, bo.getKxlx());
        lqw.eq(bo.getKxje() != null, MeaOtherPayment::getKxje, bo.getKxje());
        lqw.eq(StringUtils.isNotBlank(bo.getFj()), MeaOtherPayment::getFj, bo.getFj());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MeaOtherPayment::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增其他款项
     */
    @Override
    public Boolean insertByBo(MeaOtherPaymentBo bo) {
        MeaOtherPayment add = BeanUtil.toBean(bo, MeaOtherPayment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改其他款项
     */
    @Override
    public Boolean updateByBo(MeaOtherPaymentBo bo) {
        MeaOtherPayment update = BeanUtil.toBean(bo, MeaOtherPayment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MeaOtherPayment entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除其他款项
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
