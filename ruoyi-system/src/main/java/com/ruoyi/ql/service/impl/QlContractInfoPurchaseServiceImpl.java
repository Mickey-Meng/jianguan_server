package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.*;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.vo.*;
import com.ruoyi.ql.mapper.QlBasisSupplierMapper;
import com.ruoyi.ql.mapper.QlFinPaymentMapper;
import com.ruoyi.ql.mapper.QlWarehousingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlContractInfoPurchaseBo;
import com.ruoyi.ql.mapper.QlContractInfoPurchaseMapper;
import com.ruoyi.ql.service.IQlContractInfoPurchaseService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 采购合同 Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlContractInfoPurchaseServiceImpl implements IQlContractInfoPurchaseService {

    private final QlContractInfoPurchaseMapper baseMapper;

    private final QlWarehousingMapper qlWarehousingMapper;

    private final QlBasisSupplierMapper qlBasisSupplierMapper;

    private final QlFinPaymentMapper qlFinPaymentMapper;

    /**
     * 查询采购合同
     */
    @Override
    public QlContractInfoPurchaseVo queryById(Long id){
        QlContractInfoPurchaseVo vo = baseMapper.selectVoById(id);
        LambdaQueryWrapper<QlWarehousing> qlWarehousingLambdaQueryWrapper = new LambdaQueryWrapper<>();
        qlWarehousingLambdaQueryWrapper.eq(QlWarehousing::getPurchaseOrderId, vo.getContractCode());
        List<QlWarehousingVo> qlFinReimbursementItemVoList = qlWarehousingMapper.selectVoList(qlWarehousingLambdaQueryWrapper);
        List<QlWarehousingBo> qlWarehousingBos = new ArrayList<QlWarehousingBo>();
        qlWarehousingBos = BeanCopyUtils.copyList(qlFinReimbursementItemVoList, QlWarehousingBo.class);
        vo.setQlWarehousingBos(qlWarehousingBos);
        return vo;
    }

    /**
     * 查询采购合同 列表
     */
    @Override
    public TableDataInfo<QlContractInfoPurchaseVo> queryPageList(QlContractInfoPurchaseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlContractInfoPurchase> lqw = buildQueryWrapper(bo);
        Page<QlContractInfoPurchaseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询采购合同 列表
     */
    @Override
    public List<QlContractInfoPurchaseVo> queryList(QlContractInfoPurchaseBo bo) {
        LambdaQueryWrapper<QlContractInfoPurchase> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<QlContractInfoPurchase> buildQueryWrapper(QlContractInfoPurchaseBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlContractInfoPurchase> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getContractCode()), QlContractInfoPurchase::getContractCode, bo.getContractCode());
        lqw.like(StringUtils.isNotBlank(bo.getContractName()), QlContractInfoPurchase::getContractName, bo.getContractName());
        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), QlContractInfoPurchase::getSupplierName, bo.getSupplierName());
        lqw.eq(bo.getAmount() != null, QlContractInfoPurchase::getAmount, bo.getAmount());
        lqw.eq(bo.getContactDate() != null, QlContractInfoPurchase::getContactDate, bo.getContactDate());
        return lqw;
    }

    /**
     * 新增采购合同
     */
    @Override
    @Transactional
    public Boolean insertByBo(QlContractInfoPurchaseBo bo) {
        //1.更新采购合同表
        QlContractInfoPurchase add = BeanUtil.toBean(bo, QlContractInfoPurchase.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        //2. 插入订单表（入库表）
        if (flag) {
            List<QlWarehousing> items = new ArrayList<>();
            for (QlWarehousingBo qlWarehousingBo : bo.getQlWarehousingBos()) {
                QlWarehousing item = BeanUtil.toBean(qlWarehousingBo, QlWarehousing.class);
                item.setPurchaseOrderId(add.getContractCode());
                items.add(item);
            }
            flag = qlWarehousingMapper.insertBatch(items);
        }
        // 3.更新该供应商的欠款金额
        if(flag){
            QlBasisSupplierVo qlBasisSupplierVo = qlBasisSupplierMapper.selectVoById(bo.getSupplierId());
            qlBasisSupplierVo.setUnpaid(qlBasisSupplierVo.getUnpaid().add( bo.getAmount()));
            QlBasisSupplier qlBasisSupplier = BeanUtil.toBean(qlBasisSupplierVo, QlBasisSupplier.class);
            qlBasisSupplierMapper.updateById(qlBasisSupplier);
        }
        return flag;
    }

    @Override
    public Boolean insertContractInfoPurchase(QlContractInfoPurchaseBo bo) {
        return null;
    }

    /**
     * 修改采购合同
     */
    @Override
    @Transactional
    public Boolean updateByBo(QlContractInfoPurchaseBo bo) {
        QlContractInfoPurchase update = BeanUtil.toBean(bo, QlContractInfoPurchase.class);
        validEntityBeforeSave(update);
        List<QlWarehousing> items = new ArrayList<>();
        QlContractInfoPurchaseVo qlContractInfoPurchaseVo =  baseMapper.selectVoById(bo.getId());

        //1 ，合同中，产品明细先删除后新增
        LambdaQueryWrapper<QlWarehousing> qlWarehousingLambdaQueryWrapper = new LambdaQueryWrapper<>();
        qlWarehousingLambdaQueryWrapper.eq(QlWarehousing::getPurchaseOrderId, bo.getContractCode());
        List<QlWarehousing> qlWarehousingBos  = qlWarehousingMapper.selectList(qlWarehousingLambdaQueryWrapper);
        qlWarehousingMapper.deleteBatchIds(qlWarehousingBos);



        //2 ，新传入的list对象，把id置为null，方便insert
        for (QlWarehousingBo qlWarehousingBo : bo.getQlWarehousingBos()) {
            QlWarehousing item = BeanUtil.toBean(qlWarehousingBo, QlWarehousing.class);
            item.setPurchaseOrderId(update.getContractCode());//因为修改的做法是delete 后insert，id会变化，因此需要用业务主键进行关联，
            items.add(item);
        }
        items.forEach((e) -> {
            e.setId(null);
        });
        qlWarehousingMapper.insertBatch(items);


        // 3.获取当前合同信息，需要获取合同总额，从而在供应商表中，回退 对应的未付款金额

        BigDecimal currentContractAmount = qlContractInfoPurchaseVo.getAmount();


        // 4.更新该供应商的欠款金额
        QlBasisSupplierVo qlBasisSupplierVo = qlBasisSupplierMapper.selectVoById(bo.getSupplierId());
        //未付款金额 = 该供应商当前数据库中未付款金额 - 当前修改的合同在数据库中已存在的合同金额 + 前端传送过来的合同金额
        qlBasisSupplierVo.setUnpaid(qlBasisSupplierVo.getUnpaid().subtract(currentContractAmount).add(bo.getAmount()));
        QlBasisSupplier qlBasisSupplier = BeanUtil.toBean(qlBasisSupplierVo, QlBasisSupplier.class);
        qlBasisSupplierMapper.updateById(qlBasisSupplier);

        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlContractInfoPurchase entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除采购合同
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
