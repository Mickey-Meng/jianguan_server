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
import com.ruoyi.ql.domain.bo.QlFinInvoiceBo;
import com.ruoyi.ql.domain.vo.QlFinInvoiceVo;
import com.ruoyi.ql.domain.QlFinInvoice;
import com.ruoyi.ql.mapper.QlFinInvoiceMapper;
import com.ruoyi.ql.service.IQlFinInvoiceService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 供应商开票Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlFinInvoiceServiceImpl implements IQlFinInvoiceService {

    private final QlFinInvoiceMapper baseMapper;

    /**
     * 查询供应商开票
     */
    @Override
    public QlFinInvoiceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询供应商开票列表
     */
    @Override
    public TableDataInfo<QlFinInvoiceVo> queryPageList(QlFinInvoiceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinInvoice> lqw = buildQueryWrapper(bo);
        Page<QlFinInvoiceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询供应商开票列表
     */
    @Override
    public List<QlFinInvoiceVo> queryList(QlFinInvoiceBo bo) {
        LambdaQueryWrapper<QlFinInvoice> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlFinInvoice> buildQueryWrapper(QlFinInvoiceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinInvoice> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getInvoiceAmount() != null, QlFinInvoice::getInvoiceAmount, bo.getInvoiceAmount());
        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), QlFinInvoice::getSupplierName, bo.getSupplierName());
        return lqw;
    }

    /**
     * 新增供应商开票
     */
    @Override
    public Boolean insertByBo(QlFinInvoiceBo bo) {
        QlFinInvoice add = BeanUtil.toBean(bo, QlFinInvoice.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改供应商开票
     */
    @Override
    public Boolean updateByBo(QlFinInvoiceBo bo) {
        QlFinInvoice update = BeanUtil.toBean(bo, QlFinInvoice.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlFinInvoice entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除供应商开票
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
