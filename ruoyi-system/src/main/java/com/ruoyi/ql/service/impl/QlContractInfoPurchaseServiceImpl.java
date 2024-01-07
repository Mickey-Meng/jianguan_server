package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.BeanCopyUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ql.domain.QlBasisSupplier;
import com.ruoyi.ql.domain.QlContractInfoPurchase;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.domain.bo.QlContractGoodsRelBo;
import com.ruoyi.ql.domain.bo.QlContractInfoPurchaseBo;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.vo.*;
import com.ruoyi.ql.mapper.QlBasisSupplierMapper;
import com.ruoyi.ql.mapper.QlContractInfoPurchaseMapper;
import com.ruoyi.ql.mapper.QlFinPaymentMapper;
import com.ruoyi.ql.mapper.QlWarehousingMapper;
import com.ruoyi.ql.mapstruct.QlContractInfoPurchaseMapstruct;
import com.ruoyi.ql.service.IQlContractGoodsRelService;
import com.ruoyi.ql.service.IQlContractInfoPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final QlBasisSupplierMapper qlBasisSupplierMapper;

    private final IQlContractGoodsRelService contractGoodsRelService;

    /**
     * 查询采购合同
     */
    @Override
    public QlContractInfoPurchaseVo queryById(Long id){
        QlContractInfoPurchaseVo vo = baseMapper.selectVoById(id);
        if(ObjectUtil.isNotNull(vo.getId())) {
            QlContractGoodsRelBo qlContractGoodsRelBo = new QlContractGoodsRelBo();
            qlContractGoodsRelBo.setContractId(vo.getId());
            List<QlContractGoodsRelVo> qlContractGoodsRels = contractGoodsRelService.queryList(qlContractGoodsRelBo);
            vo.setContractGoodsRels(qlContractGoodsRels);
        }
        return vo;
    }



    /**
     * 查询采购合同 列表
     */
    @Override
    public TableDataInfo<QlContractInfoPurchaseVo> queryPageList(QlContractInfoPurchaseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlContractInfoPurchase> lqw = buildQueryWrapper(bo);
        Page<QlContractInfoPurchaseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        if (ObjectUtil.isNull(result) || CollUtil.isEmpty(result.getRecords())) {
            return TableDataInfo.build(result);
        }
        findContractGoodsRels(result.getRecords());
        return TableDataInfo.build(result);
    }

    /**
     * 查询采购合同 列表
     */
    @Override
    public List<QlContractInfoPurchaseVo> queryList(QlContractInfoPurchaseBo bo) {
        LambdaQueryWrapper<QlContractInfoPurchase> lqw = buildQueryWrapper(bo);
        List<QlContractInfoPurchaseVo> qlContractInfoPurchaseVos = baseMapper.selectVoList(lqw);
        findContractGoodsRels(qlContractInfoPurchaseVos);
        return qlContractInfoPurchaseVos;
    }

    private void findContractGoodsRels(List<QlContractInfoPurchaseVo> contractInfoPurchaseVos) {
        if(CollUtil.isEmpty(contractInfoPurchaseVos)) {
            return;
        }
        List<Long> paymentIds = contractInfoPurchaseVos.stream().map(QlContractInfoPurchaseVo::getId).collect(Collectors.toList());
        QlContractGoodsRelBo contractGoodsRelBo = new QlContractGoodsRelBo();
        contractGoodsRelBo.setContractIds(paymentIds);
        List<QlContractGoodsRelVo> contractGoodsRelVos = contractGoodsRelService.queryList(contractGoodsRelBo);
        if (CollUtil.isEmpty(contractGoodsRelVos)) {
            return;
        }
        Map<Long, List<QlContractGoodsRelVo>> contractGoodsRelMap = contractGoodsRelVos.stream().collect(Collectors.groupingBy(QlContractGoodsRelVo::getContractId));
        for (QlContractInfoPurchaseVo contractInfoPurchaseVo : contractInfoPurchaseVos) {
            if (!contractGoodsRelMap.containsKey(contractInfoPurchaseVo.getId())) {
                continue;
            }
            contractInfoPurchaseVo.setContractGoodsRels(contractGoodsRelMap.get(contractInfoPurchaseVo.getId()));
        }
    }

    private LambdaQueryWrapper<QlContractInfoPurchase> buildQueryWrapper(QlContractInfoPurchaseBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlContractInfoPurchase> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getContractCode()), QlContractInfoPurchase::getContractCode, bo.getContractCode());
        lqw.like(StringUtils.isNotBlank(bo.getContractName()), QlContractInfoPurchase::getContractName, bo.getContractName());
        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), QlContractInfoPurchase::getSupplierName, bo.getSupplierName());
        lqw.eq(StringUtils.isNotBlank(bo.getContactPerson()), QlContractInfoPurchase::getContactPerson, bo.getContactPerson());
        lqw.eq(StringUtils.isNotBlank(bo.getMobilePhone()), QlContractInfoPurchase::getMobilePhone, bo.getMobilePhone());
        lqw.eq(StringUtils.isNotBlank(bo.getPurchaser()), QlContractInfoPurchase::getPurchaser, bo.getPurchaser());
        lqw.eq(bo.getContactDate() != null, QlContractInfoPurchase::getContactDate, bo.getContactDate());
        lqw.eq(bo.getStartDate() != null, QlContractInfoPurchase::getStartDate, bo.getStartDate());
        lqw.eq(bo.getEndDate() != null, QlContractInfoPurchase::getEndDate, bo.getEndDate());
        lqw.in(CollUtil.isNotEmpty(bo.getContractCodes()), QlContractInfoPurchase::getContractCode, bo.getContractCodes());
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
            if (CollUtil.isNotEmpty(bo.getContractGoodsRels())) {
                for (QlContractGoodsRelBo contractGoodsRel : bo.getContractGoodsRels()) {
                    contractGoodsRel.setContractId(add.getId());
                    contractGoodsRel.setContractType("purchase");
                    contractGoodsRelService.insertByBo(contractGoodsRel);
                }
            }
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

        QlContractGoodsRelBo contractGoodsRelBo = new QlContractGoodsRelBo();
        contractGoodsRelBo.setContractId(bo.getId());
        List<QlContractGoodsRelVo> contractGoodsRelVos = contractGoodsRelService.queryList(contractGoodsRelBo);

        if(CollUtil.isNotEmpty(contractGoodsRelVos)) {
            List<Long> ids = contractGoodsRelVos.stream().map(QlContractGoodsRelVo::getId).collect(Collectors.toList());
            contractGoodsRelService.deleteWithValidByIds(ids, true);
        }
        for (QlContractGoodsRelBo contractGoodsRel : bo.getContractGoodsRels()) {
            contractGoodsRel.setId(null);
            contractGoodsRel.setContractId(bo.getId());
            contractGoodsRelService.insertByBo(contractGoodsRel);
        }


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
        boolean deleteResult = baseMapper.deleteBatchIds(ids) > 0;
        if (deleteResult) {
            QlContractGoodsRelBo qlContractGoodsRelBo = new QlContractGoodsRelBo();
            List<Long> idList = ids.stream().distinct().collect(Collectors.toList());
            if(CollUtil.isEmpty(idList)) {
                return true;
            }
            qlContractGoodsRelBo.setContractIds(idList);
            List<QlContractGoodsRelVo> contractGoodsRels = contractGoodsRelService.queryList(qlContractGoodsRelBo);
            List<Long> relIds = contractGoodsRels.stream().map(QlContractGoodsRelVo::getId).collect(Collectors.toList());
            if(CollUtil.isEmpty(relIds)) {
                return true;
            }
            contractGoodsRelService.deleteWithValidByIds(relIds, true);
        }
        return deleteResult;
    }

    @Override
    public void batchInsertBo(List<QlContractInfoPurchaseBo> bos) {
        for (QlContractInfoPurchaseBo bo : bos) {
            insertByBo(bo);
        }
    }
}
