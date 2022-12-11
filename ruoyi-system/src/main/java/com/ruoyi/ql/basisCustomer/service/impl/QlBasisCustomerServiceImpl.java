package com.ruoyi.ql.basisCustomer.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.ql.basisCustomer.domain.QlBasisCustomer;
import com.ruoyi.ql.basisCustomer.domain.bo.QlBasisCustomerBo;
import com.ruoyi.ql.basisCustomer.domain.vo.QlBasisCustomerVo;
import com.ruoyi.ql.basisCustomer.mapper.QlBasisCustomerMapper;
import com.ruoyi.ql.basisCustomer.service.IQlBasisCustomerService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 客户资料Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlBasisCustomerServiceImpl implements IQlBasisCustomerService {

    private final QlBasisCustomerMapper baseMapper;

    /**
     * 查询客户资料
     */
    @Override
    public QlBasisCustomerVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询客户资料列表
     */
    @Override
    public TableDataInfo<QlBasisCustomerVo> queryPageList(QlBasisCustomerBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlBasisCustomer> lqw = buildQueryWrapper(bo);
        Page<QlBasisCustomerVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询客户资料列表
     */
    @Override
    public List<QlBasisCustomerVo> queryList(QlBasisCustomerBo bo) {
        LambdaQueryWrapper<QlBasisCustomer> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlBasisCustomer> buildQueryWrapper(QlBasisCustomerBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlBasisCustomer> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerCode()), QlBasisCustomer::getCustomerCode, bo.getCustomerCode());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlBasisCustomer::getCustomerName, bo.getCustomerName());
        lqw.eq(StringUtils.isNotBlank(bo.getBusinessLicense()), QlBasisCustomer::getBusinessLicense, bo.getBusinessLicense());
        lqw.eq(StringUtils.isNotBlank(bo.getContactPerson()), QlBasisCustomer::getContactPerson, bo.getContactPerson());
        lqw.eq(StringUtils.isNotBlank(bo.getTelephone()), QlBasisCustomer::getTelephone, bo.getTelephone());
        lqw.eq(StringUtils.isNotBlank(bo.getMobilePhone()), QlBasisCustomer::getMobilePhone, bo.getMobilePhone());
        lqw.eq(StringUtils.isNotBlank(bo.getEmail()), QlBasisCustomer::getEmail, bo.getEmail());
        lqw.eq(StringUtils.isNotBlank(bo.getArea()), QlBasisCustomer::getArea, bo.getArea());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), QlBasisCustomer::getAddress, bo.getAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getContactPersonOne()), QlBasisCustomer::getContactPersonOne, bo.getContactPersonOne());
        lqw.eq(StringUtils.isNotBlank(bo.getTelephoneOne()), QlBasisCustomer::getTelephoneOne, bo.getTelephoneOne());
        lqw.like(StringUtils.isNotBlank(bo.getInvoiceLookedUp()), QlBasisCustomer::getInvoiceLookedUp, bo.getInvoiceLookedUp());
        lqw.eq(bo.getInvoiceTax() != null, QlBasisCustomer::getInvoiceTax, bo.getInvoiceTax());
        lqw.eq(StringUtils.isNotBlank(bo.getInvoiceType()), QlBasisCustomer::getInvoiceType, bo.getInvoiceType());
        lqw.eq(bo.getDeptId() != null, QlBasisCustomer::getDeptId, bo.getDeptId());
        return lqw;
    }

    /**
     * 新增客户资料
     */
    @Override
    public Boolean insertByBo(QlBasisCustomerBo bo) {
        QlBasisCustomer add = BeanUtil.toBean(bo, QlBasisCustomer.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户资料
     */
    @Override
    public Boolean updateByBo(QlBasisCustomerBo bo) {
        QlBasisCustomer update = BeanUtil.toBean(bo, QlBasisCustomer.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlBasisCustomer entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除客户资料
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
