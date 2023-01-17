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
import com.ruoyi.ql.domain.bo.QlBasisSupplierBo;
import com.ruoyi.ql.domain.vo.QlBasisSupplierVo;
import com.ruoyi.ql.domain.QlBasisSupplier;
import com.ruoyi.ql.mapper.QlBasisSupplierMapper;
import com.ruoyi.ql.service.IQlBasisSupplierService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 供应商管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlBasisSupplierServiceImpl implements IQlBasisSupplierService {

    private final QlBasisSupplierMapper baseMapper;

    /**
     * 查询供应商管理
     */
    @Override
    public QlBasisSupplierVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询供应商管理列表
     */
    @Override
    public TableDataInfo<QlBasisSupplierVo> queryPageList(QlBasisSupplierBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlBasisSupplier> lqw = buildQueryWrapper(bo);
        Page<QlBasisSupplierVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商管理列表
     */
    @Override
    public List<QlBasisSupplierVo> queryList(QlBasisSupplierBo bo) {
        LambdaQueryWrapper<QlBasisSupplier> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlBasisSupplier> buildQueryWrapper(QlBasisSupplierBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlBasisSupplier> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getSupplierCode()), QlBasisSupplier::getSupplierCode, bo.getSupplierCode());
        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), QlBasisSupplier::getSupplierName, bo.getSupplierName());
        lqw.eq(StringUtils.isNotBlank(bo.getContactPerson()), QlBasisSupplier::getContactPerson, bo.getContactPerson());
        lqw.like(StringUtils.isNotBlank(bo.getMobilePhone()), QlBasisSupplier::getMobilePhone, bo.getMobilePhone());
        lqw.eq(bo.getPayed() != null, QlBasisSupplier::getPayed, bo.getPayed());
        lqw.eq(bo.getUnpaid() != null, QlBasisSupplier::getUnpaid, bo.getUnpaid());
        lqw.eq(bo.getInvoiceAmount() != null, QlBasisSupplier::getInvoiceAmount, bo.getInvoiceAmount());
        lqw.eq(bo.getUninvoiceAmount() != null, QlBasisSupplier::getUninvoiceAmount, bo.getUninvoiceAmount());
        return lqw;
    }

    /**
     * 新增供应商管理
     */
    @Override
    public Boolean insertByBo(QlBasisSupplierBo bo) {
        QlBasisSupplier add = BeanUtil.toBean(bo, QlBasisSupplier.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改供应商管理
     */
    @Override
    public Boolean updateByBo(QlBasisSupplierBo bo) {
        QlBasisSupplier update = BeanUtil.toBean(bo, QlBasisSupplier.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlBasisSupplier entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
