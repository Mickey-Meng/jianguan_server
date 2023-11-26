package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.bo.QlInvoiceItemBo;
import com.ruoyi.ql.domain.vo.QlInvoiceItemVo;
import com.ruoyi.ql.mapper.QlInvoiceItemMapper;
import com.ruoyi.ql.service.IQlInvoiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlFinInvoiceSaleBo;
import com.ruoyi.ql.domain.vo.QlFinInvoiceSaleVo;
import com.ruoyi.ql.domain.QlFinInvoiceSale;
import com.ruoyi.ql.mapper.QlFinInvoiceSaleMapper;
import com.ruoyi.ql.service.IQlFinInvoiceSaleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 销售开票Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@RequiredArgsConstructor
@Service
public class QlFinInvoiceSaleServiceImpl implements IQlFinInvoiceSaleService {

    private final QlFinInvoiceSaleMapper baseMapper;

    private final IQlInvoiceItemService iQlInvoiceItemService;

    /**
     * 查询销售开票
     */
    @Override
    public QlFinInvoiceSaleVo queryById(Long id){
        QlFinInvoiceSaleVo qlFinInvoiceSaleVo = baseMapper.selectVoById(id);
        QlInvoiceItemBo bo = new QlInvoiceItemBo();
        bo.setSourceId(qlFinInvoiceSaleVo.getId());
        List<QlInvoiceItemVo> qlInvoiceItemVos = iQlInvoiceItemService.queryList(bo);
        qlFinInvoiceSaleVo.setInvoiceItems(qlInvoiceItemVos);
        return qlFinInvoiceSaleVo;
    }

    /**
     * 查询销售开票列表
     */
    @Override
    public TableDataInfo<QlFinInvoiceSaleVo> queryPageList(QlFinInvoiceSaleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinInvoiceSale> lqw = buildQueryWrapper(bo);
        Page<QlFinInvoiceSaleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        setInvoiceItems(result);
        return TableDataInfo.build(result);
    }



    /**
     * 查询销售开票列表
     */
    @Override
    public List<QlFinInvoiceSaleVo> queryList(QlFinInvoiceSaleBo bo) {
        LambdaQueryWrapper<QlFinInvoiceSale> lqw = buildQueryWrapper(bo);
        List<QlFinInvoiceSaleVo> finInvoiceSales = baseMapper.selectVoList(lqw);
        setInvoiceItems(finInvoiceSales);
        return finInvoiceSales;
    }

    private void setInvoiceItems(Page<QlFinInvoiceSaleVo> result) {
        if(ObjectUtil.isNull(result)) {
            return;
        }
        setInvoiceItems(result.getRecords());
    }

    private void setInvoiceItems(List<QlFinInvoiceSaleVo> finInvoiceSales) {
        if(CollUtil.isEmpty(finInvoiceSales)) {
            return ;
        }
        QlInvoiceItemBo invoiceItemBo = new QlInvoiceItemBo();
        invoiceItemBo.setSourceIds(finInvoiceSales.stream().map(QlFinInvoiceSaleVo::getId).collect(Collectors.toList()));
        invoiceItemBo.setSourceType("outbound");
        List<QlInvoiceItemVo> invoiceItems = iQlInvoiceItemService.queryList(invoiceItemBo);
        if(CollUtil.isEmpty(invoiceItems)) {
            return ;
        }
        Map<Long, List<QlInvoiceItemVo>> invoiceItemMap = invoiceItems.stream().collect(Collectors.groupingBy(QlInvoiceItemVo::getSourceId));
        for (QlFinInvoiceSaleVo finInvoiceSale : finInvoiceSales) {
            if (!invoiceItemMap.containsKey(finInvoiceSale.getId())) {
                continue;
            }
            finInvoiceSale.setInvoiceItems(invoiceItemMap.get(finInvoiceSale.getId()));
        }
    }

    private LambdaQueryWrapper<QlFinInvoiceSale> buildQueryWrapper(QlFinInvoiceSaleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinInvoiceSale> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getContractId()), QlFinInvoiceSale::getContractId, bo.getContractId());
        lqw.like(StringUtils.isNotBlank(bo.getContractCode()), QlFinInvoiceSale::getContractCode, bo.getContractCode());
        lqw.like(StringUtils.isNotBlank(bo.getContractName()), QlFinInvoiceSale::getContractName, bo.getContractName());
        lqw.eq(StringUtils.isNotBlank(bo.getCustomerId()), QlFinInvoiceSale::getCustomerId, bo.getCustomerId());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlFinInvoiceSale::getCustomerName, bo.getCustomerName());
        lqw.like(StringUtils.isNotBlank(bo.getInvoiceNo()), QlFinInvoiceSale::getInvoiceNo, bo.getInvoiceNo());
        lqw.eq(bo.getInvoiceAmount() != null, QlFinInvoiceSale::getInvoiceAmount, bo.getInvoiceAmount());
        lqw.eq(bo.getInvoiceDate() != null, QlFinInvoiceSale::getInvoiceDate, bo.getInvoiceDate());
        return lqw;
    }

    /**
     * 新增销售开票
     */
    @Override
    public Boolean insertByBo(QlFinInvoiceSaleBo bo) {
        QlFinInvoiceSale add = BeanUtil.toBean(bo, QlFinInvoiceSale.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        for (QlInvoiceItemBo invoiceItem : bo.getInvoiceItems()) {
            invoiceItem.setSourceId(add.getId());
            invoiceItem.setSourceType("outbound");
            iQlInvoiceItemService.insertByBo(invoiceItem);
        }
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改销售开票
     */
    @Override
    public Boolean updateByBo(QlFinInvoiceSaleBo bo) {
        QlFinInvoiceSale update = BeanUtil.toBean(bo, QlFinInvoiceSale.class);
        validEntityBeforeSave(update);
        iQlInvoiceItemService.deleteBySourceId(bo.getId());
        for (QlInvoiceItemBo invoiceItem : bo.getInvoiceItems()) {
            invoiceItem.setId(null);
            iQlInvoiceItemService.insertByBo(invoiceItem);
        }
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlFinInvoiceSale entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除销售开票
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        for (Long id : ids) {
            iQlInvoiceItemService.deleteBySourceId(id);
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
