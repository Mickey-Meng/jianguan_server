package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlBasisSupplier;
import com.ruoyi.ql.domain.vo.QlBasisSupplierVo;
import com.ruoyi.ql.mapper.QlBasisSupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlFinPaymentBo;
import com.ruoyi.ql.domain.vo.QlFinPaymentVo;
import com.ruoyi.ql.domain.QlFinPayment;
import com.ruoyi.ql.mapper.QlFinPaymentMapper;
import com.ruoyi.ql.service.IQlFinPaymentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 供应商付款Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlFinPaymentServiceImpl implements IQlFinPaymentService {

    private final QlFinPaymentMapper baseMapper;

    private final QlBasisSupplierMapper qlBasisSupplierMapper;


    /**
     * 查询供应商付款
     */
    @Override
    public QlFinPaymentVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询供应商付款列表
     */
    @Override
    public TableDataInfo<QlFinPaymentVo> queryPageList(QlFinPaymentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinPayment> lqw = buildQueryWrapper(bo);
        Page<QlFinPaymentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商付款列表
     */
    @Override
    public List<QlFinPaymentVo> queryList(QlFinPaymentBo bo) {
        LambdaQueryWrapper<QlFinPayment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlFinPayment> buildQueryWrapper(QlFinPaymentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinPayment> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getContractId()), QlFinPayment::getContractId, bo.getContractId());
        lqw.like(StringUtils.isNotBlank(bo.getContractName()), QlFinPayment::getContractName, bo.getContractName());
        lqw.eq(StringUtils.isNotBlank(bo.getSupplierId()), QlFinPayment::getSupplierId, bo.getSupplierId());
        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), QlFinPayment::getSupplierName, bo.getSupplierName());
        lqw.eq(bo.getPayAmount() != null, QlFinPayment::getPayAmount, bo.getPayAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getPayType()), QlFinPayment::getPayType, bo.getPayType());
        lqw.eq(bo.getUnpaid() != null, QlFinPayment::getUnpaid, bo.getUnpaid());
        lqw.eq(bo.getPaymentDate() != null, QlFinPayment::getPaymentDate, bo.getPaymentDate());
        lqw.eq(bo.getDeptId() != null, QlFinPayment::getDeptId, bo.getDeptId());
        return lqw;
    }

    /**
     * 新增供应商付款
     */
    @Override
    public Boolean insertByBo(QlFinPaymentBo bo) {
        QlFinPayment add = BeanUtil.toBean(bo, QlFinPayment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 付款：对象中金额为正数
     * @param bo
     * @return
     */
    @Override
    @Transactional
    public Boolean insertPaymentByBo(QlFinPaymentBo bo) {
        insertByBo(bo);
        QlBasisSupplierVo qlBasisSupplierVo = qlBasisSupplierMapper.selectVoById(bo.getSupplierId());
        qlBasisSupplierVo.setPayed(qlBasisSupplierVo.getPayed().add( bo.getPayAmount()));
        qlBasisSupplierVo.setUnpaid(qlBasisSupplierVo.getUnpaid().subtract( bo.getPayAmount()));
        QlBasisSupplier qlBasisSupplier = BeanUtil.toBean(qlBasisSupplierVo, QlBasisSupplier.class);
        qlBasisSupplierMapper.updateById(qlBasisSupplier);
        return true;
    }


    /**
     * 修改供应商付款
     */
    @Override
    public Boolean updateByBo(QlFinPaymentBo bo) {
        QlFinPayment update = BeanUtil.toBean(bo, QlFinPayment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlFinPayment entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商付款
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
