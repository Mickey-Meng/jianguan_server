package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlBasisSupplier;
import com.ruoyi.ql.domain.QlPaymentWarehousingRel;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.domain.bo.QlPaymentWarehousingRelBo;
import com.ruoyi.ql.domain.vo.QlBasisSupplierVo;
import com.ruoyi.ql.domain.vo.QlPaymentWarehousingRelVo;
import com.ruoyi.ql.mapper.QlBasisSupplierMapper;
import com.ruoyi.ql.mapper.QlPaymentWarehousingRelMapper;
import com.ruoyi.ql.service.IQlPaymentWarehousingRelService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;
import com.ruoyi.ql.domain.bo.QlFinPaymentBo;
import com.ruoyi.ql.domain.vo.QlFinPaymentVo;
import com.ruoyi.ql.domain.QlFinPayment;
import com.ruoyi.ql.mapper.QlFinPaymentMapper;
import com.ruoyi.ql.service.IQlFinPaymentService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

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

    private final IQlPaymentWarehousingRelService paymentWarehousingRelService;

    private final QlPaymentWarehousingRelMapper paymentWarehousingRelMapper;


    /**
     * 查询供应商付款
     */
    @Override
    public QlFinPaymentVo queryById(Long id){
        QlFinPaymentVo qlFinPaymentVo = baseMapper.selectVoById(id);
        QlPaymentWarehousingRelBo qlPaymentWarehousingRelBo = new QlPaymentWarehousingRelBo();
        qlPaymentWarehousingRelBo.setPaymentId(qlFinPaymentVo.getId());
        List<QlPaymentWarehousingRelVo> qlPaymentWarehousingRelVos = paymentWarehousingRelService.queryList(qlPaymentWarehousingRelBo);
        qlFinPaymentVo.setPaymentWarehousingRels(qlPaymentWarehousingRelVos);
        return qlFinPaymentVo;
    }

    /**
     * 查询供应商付款列表
     */
    @Override
    public TableDataInfo<QlFinPaymentVo> queryPageList(QlFinPaymentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlFinPayment> lqw = buildQueryWrapper(bo);
        Page<QlFinPaymentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        if(ObjectUtil.isNull(result) || CollUtil.isEmpty(result.getRecords())) {
            return TableDataInfo.build(result);
        }
        findPaymentWarehousingRels(result.getRecords());
        return TableDataInfo.build(result);
    }

    private void findPaymentWarehousingRels(List<QlFinPaymentVo> finPaymentVos) {
        List<Long> paymentIds = finPaymentVos.stream().map(QlFinPaymentVo::getId).collect(Collectors.toList());
        QlPaymentWarehousingRelBo qlPaymentWarehousingRelBo = new QlPaymentWarehousingRelBo();
        qlPaymentWarehousingRelBo.setPaymentIds(paymentIds);
        List<QlPaymentWarehousingRelVo> qlPaymentWarehousingRelVos = paymentWarehousingRelService.queryList(qlPaymentWarehousingRelBo);
        if (CollUtil.isEmpty(qlPaymentWarehousingRelVos)) {
            return;
        }
        Map<Long, List<QlPaymentWarehousingRelVo>> paymentWarehousingRelMap = qlPaymentWarehousingRelVos.stream().collect(Collectors.groupingBy(QlPaymentWarehousingRelVo::getPaymentId));
        for (QlFinPaymentVo finPaymentVo : finPaymentVos) {
            if (!paymentWarehousingRelMap.containsKey(finPaymentVo.getId())) {
                continue;
            }
            finPaymentVo.setPaymentWarehousingRels(paymentWarehousingRelMap.get(finPaymentVo.getId()));
        }
    }

    /**
     * 查询供应商付款列表
     */
    @Override
    public List<QlFinPaymentVo> queryList(QlFinPaymentBo bo) {
        LambdaQueryWrapper<QlFinPayment> lqw = buildQueryWrapper(bo);
        List<QlFinPaymentVo> qlFinPaymentVos = baseMapper.selectVoList(lqw);
        if(CollUtil.isEmpty(qlFinPaymentVos)) {
            return qlFinPaymentVos;
        }
        findPaymentWarehousingRels(qlFinPaymentVos);
        return qlFinPaymentVos;
    }

    private LambdaQueryWrapper<QlFinPayment> buildQueryWrapper(QlFinPaymentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlFinPayment> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getContractId()), QlFinPayment::getContractId, bo.getContractId());
        lqw.like(StringUtils.isNotBlank(bo.getContractName()), QlFinPayment::getContractName, bo.getContractName());
//        lqw.eq(StringUtils.isNotBlank(bo.getSupplierId()), QlFinPayment::getSupplierId, bo.getSupplierId());
//        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), QlFinPayment::getSupplierName, bo.getSupplierName());
        lqw.eq(bo.getPayAmount() != null, QlFinPayment::getPayAmount, bo.getPayAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getPayType()), QlFinPayment::getPayType, bo.getPayType());
        lqw.eq(bo.getUnpaid() != null, QlFinPayment::getUnpaid, bo.getUnpaid());
        lqw.eq(bo.getPaymentDate() != null, QlFinPayment::getPaymentDate, bo.getPaymentDate());
        lqw.eq(bo.getDeptId() != null, QlFinPayment::getDeptId, bo.getDeptId());
        lqw.like(StringUtils.isNotBlank(bo.getAccountNo()), QlFinPayment::getAccountNo, bo.getAccountNo());
        lqw.like(StringUtils.isNotBlank(bo.getBankName()), QlFinPayment::getBankName, bo.getBankName());
        lqw.like(StringUtils.isNotBlank(bo.getInvoiceNo()), QlFinPayment::getInvoiceNo, bo.getInvoiceNo());
//        lqw.like(StringUtils.isNotBlank(bo.getContractCode()), QlFinPayment::getContractCode, bo.getContractCode());
//        lqw.like(StringUtils.isNotBlank(bo.getWarehousingCode()), QlFinPayment::getWarehousingCode, bo.getWarehousingCode());

        return lqw;
    }

    /**
     * 新增供应商付款
     */
    @Override
    public Boolean insertByBo(QlFinPaymentBo bo) {
        QlFinPayment add = BeanUtil.toBean(bo, QlFinPayment.class);
        validEntityBeforeSave(add);
        BigDecimal payAmount = bo.getPaymentWarehousingRels().stream().map(QlPaymentWarehousingRelBo::getPayAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        add.setPayAmount(payAmount);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        for (QlPaymentWarehousingRelBo paymentWarehousingRel : bo.getPaymentWarehousingRels()) {
            paymentWarehousingRel.setPaymentId(add.getId());
            paymentWarehousingRelService.insertByBo(paymentWarehousingRel);
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
    /*    QlBasisSupplierVo qlBasisSupplierVo = qlBasisSupplierMapper.selectOne(new LambdaQueryWrapper<SysDictData>()
                .select(SysDictData::getDictLabel)
                .eq(SysDictData::getDictType, bo.getSupplierName())
                .eq(SysDictData::getDictValue, dictValue))*/

        /*
        QueryWrapper<QlBasisSupplier> qlBasisSupplierVoQueryWrapper=new QueryWrapper<>();
        qlBasisSupplierVoQueryWrapper.eq("supplier_name",bo.getSupplierName());
        qlBasisSupplierVoQueryWrapper.eq("del_flag",0);
        QlBasisSupplier qlBasisSupplier = qlBasisSupplierMapper.selectOne(qlBasisSupplierVoQueryWrapper);

//        QlBasisSupplierVo qlBasisSupplierVo = qlBasisSupplierMapper.selectVoById(bo.getSupplierId());
        qlBasisSupplier.setPayed(qlBasisSupplier.getPayed().add( bo.getPayAmount()));
        qlBasisSupplier.setUnpaid(qlBasisSupplier.getUnpaid().subtract( bo.getPayAmount()));
//        QlBasisSupplier qlBasisSupplier = BeanUtil.toBean(qlBasisSupplier, QlBasisSupplier.class);
        qlBasisSupplierMapper.updateById(qlBasisSupplier);
        */
        return true;
    }


    /**
     * 修改供应商付款
     */
    @Override
    public Boolean updateByBo(QlFinPaymentBo bo) {
        QlFinPaymentVo qlFinPaymentVo = baseMapper.selectVoById(bo.getId());

        /*
        // 1. 更新供应商已付款金额= 付款金额 - 上一次付款金额 + 修改后付款金额，同理未付款金额重新更新
        QlBasisSupplierVo qlBasisSupplierVo = qlBasisSupplierMapper.selectVoById(bo.getSupplierId());
        qlBasisSupplierVo.setPayed(qlBasisSupplierVo.getPayed().add( bo.getPayAmount()).subtract(qlFinPaymentVo.getPayAmount()));
        qlBasisSupplierVo.setUnpaid(qlBasisSupplierVo.getUnpaid().subtract( bo.getPayAmount()).add(qlFinPaymentVo.getPayAmount()));
        QlBasisSupplier qlBasisSupplier = BeanUtil.toBean(qlBasisSupplierVo, QlBasisSupplier.class);
        qlBasisSupplierMapper.updateById(qlBasisSupplier);
        */
        //2 . 更新此次付款为修改后的信息
        QlFinPayment update = BeanUtil.toBean(bo, QlFinPayment.class);
        BigDecimal payAmount = bo.getPaymentWarehousingRels().stream().map(QlPaymentWarehousingRelBo::getPayAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        update.setPayAmount(payAmount);
        validEntityBeforeSave(update);

        LambdaQueryWrapper<QlPaymentWarehousingRel> ql = new LambdaQueryWrapper<>();
        ql.eq(QlPaymentWarehousingRel::getPaymentId, bo.getId());
        List<QlPaymentWarehousingRel> qlPaymentWarehousingRels = paymentWarehousingRelMapper.selectList(ql);
        if(CollUtil.isNotEmpty(qlPaymentWarehousingRels)) {
            paymentWarehousingRelMapper.deleteBatchIds(qlPaymentWarehousingRels);
        }
        for (QlPaymentWarehousingRelBo paymentWarehousingRel : bo.getPaymentWarehousingRels()) {
            paymentWarehousingRel.setId(null);
            paymentWarehousingRel.setPaymentId(bo.getId());
            paymentWarehousingRelService.insertByBo(paymentWarehousingRel);
        }
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
        boolean deleteResult = baseMapper.deleteBatchIds(ids) > 0;
        if (deleteResult) {
            List<Long> idList = ids.stream().distinct().collect(Collectors.toList());
            if (CollUtil.isEmpty(idList)) {
                return true;
            }
            QlPaymentWarehousingRelBo qlPaymentWarehousingRelBo = new QlPaymentWarehousingRelBo();
            qlPaymentWarehousingRelBo.setPaymentIds(idList);
            List<QlPaymentWarehousingRelVo> qlPaymentWarehousingRelVos = paymentWarehousingRelService.queryList(qlPaymentWarehousingRelBo);
            if(CollUtil.isEmpty(qlPaymentWarehousingRelVos)) {
                return true;
            }
            paymentWarehousingRelService.deleteWithValidByIds(qlPaymentWarehousingRelVos.stream().map(QlPaymentWarehousingRelVo::getId).distinct().collect(Collectors.toList()), true);
        }
        return deleteResult;
    }
}
