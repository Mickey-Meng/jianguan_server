package com.ruoyi.jianguan.business.contract.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.jianguan.business.contract.domain.bo.ContractPaymentBo;
import com.ruoyi.jianguan.business.contract.domain.entity.ContractPayment;
import com.ruoyi.jianguan.business.contract.domain.vo.ContractPaymentVo;
import com.ruoyi.jianguan.business.contract.mapper.ContractPaymentMapper;
import com.ruoyi.jianguan.business.contract.service.IContractPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 合同付款Service业务层处理
 *
 * @author mickey
 * @date 2023-06-07
 */
@RequiredArgsConstructor
@Service
public class ContractPaymentServiceImpl implements IContractPaymentService {

    private final ContractPaymentMapper baseMapper;

    /**
     * 查询合同付款
     */
    @Override
    public ContractPaymentVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询合同付款列表
     */
    @Override
    public TableDataInfo<ContractPaymentVo> queryPageList(ContractPaymentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ContractPayment> lqw = buildQueryWrapper(bo);
        Page<ContractPaymentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询合同付款列表
     */
    @Override
    public List<ContractPaymentVo> queryList(ContractPaymentBo bo) {
        LambdaQueryWrapper<ContractPayment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ContractPayment> buildQueryWrapper(ContractPaymentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ContractPayment> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getType()), ContractPayment::getType, bo.getType());
        lqw.eq(bo.getAmount() != null, ContractPayment::getAmount, bo.getAmount());
        lqw.eq(bo.getRecordTime() != null, ContractPayment::getRecordTime, bo.getRecordTime());
        lqw.eq(StringUtils.isNotBlank(bo.getAttachment()), ContractPayment::getAttachment, bo.getAttachment());
        lqw.eq(bo.getStatus() != null, ContractPayment::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增合同付款
     */
    @Override
    public Boolean insertByBo(ContractPaymentBo bo) {
        ContractPayment add = BeanUtil.toBean(bo, ContractPayment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改合同付款
     */
    @Override
    public Boolean updateByBo(ContractPaymentBo bo) {
        ContractPayment update = BeanUtil.toBean(bo, ContractPayment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ContractPayment entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除合同付款
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

}