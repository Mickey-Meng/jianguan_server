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
import com.ruoyi.ql.domain.bo.QlFinReceivableBo;
import com.ruoyi.ql.domain.vo.QlFinReceivableVo;
import com.ruoyi.ql.domain.QlFinReceivable;
import com.ruoyi.ql.mapper.QlFinReceivableMapper;
import com.ruoyi.ql.service.IQlFinReceivableService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 收款记录Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@RequiredArgsConstructor
@Service
public class QlFinReceivableServiceImpl implements IQlFinReceivableService {

    private final QlFinReceivableMapper baseMapper;

    /**
     * 查询收款记录
     */
    @Override
    public QlFinReceivableVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询收款记录列表
     */
    @Override
    public TableDataInfo<QlFinReceivableVo> queryPageList(QlFinReceivableBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinReceivable> lqw = buildQueryWrapper(bo);
        Page<QlFinReceivableVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询收款记录列表
     */
    @Override
    public List<QlFinReceivableVo> queryList(QlFinReceivableBo bo) {
        LambdaQueryWrapper<QlFinReceivable> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlFinReceivable> buildQueryWrapper(QlFinReceivableBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinReceivable> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getContractId()), QlFinReceivable::getContractId, bo.getContractId());
        lqw.eq(StringUtils.isNotBlank(bo.getContractCode()), QlFinReceivable::getContractCode, bo.getContractCode());
        lqw.like(StringUtils.isNotBlank(bo.getContractName()), QlFinReceivable::getContractName, bo.getContractName());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerId()), QlFinReceivable::getCustomerId, bo.getCustomerId());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlFinReceivable::getCustomerName, bo.getCustomerName());
        lqw.eq(bo.getReceivableAmount() != null, QlFinReceivable::getReceivableAmount, bo.getReceivableAmount());
        lqw.eq(bo.getReceivableDate() != null, QlFinReceivable::getReceivableDate, bo.getReceivableDate());
        lqw.eq(StringUtils.isNotBlank(bo.getReceivableSummary()), QlFinReceivable::getReceivableSummary, bo.getReceivableSummary());
        lqw.eq(StringUtils.isNotBlank(bo.getAccountNo()), QlFinReceivable::getAccountNo, bo.getAccountNo());
        lqw.like(StringUtils.isNotBlank(bo.getBankName()), QlFinReceivable::getBankName, bo.getBankName());
        lqw.eq(StringUtils.isNotBlank(bo.getInvoiceNo()), QlFinReceivable::getInvoiceNo, bo.getInvoiceNo());
        lqw.eq(StringUtils.isNotBlank(bo.getFj()), QlFinReceivable::getFj, bo.getFj());
        return lqw;
    }

    /**
     * 新增收款记录
     */
    @Override
    public Boolean insertByBo(QlFinReceivableBo bo) {
        QlFinReceivable add = BeanUtil.toBean(bo, QlFinReceivable.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改收款记录
     */
    @Override
    public Boolean updateByBo(QlFinReceivableBo bo) {
        QlFinReceivable update = BeanUtil.toBean(bo, QlFinReceivable.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlFinReceivable entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除收款记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
