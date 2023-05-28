package com.ruoyi.ql.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ql.domain.QlContractGoodsRel;
import com.ruoyi.ql.domain.QlContractInfoSale;
import com.ruoyi.ql.domain.bo.QlContractGoodsRelBo;
import com.ruoyi.ql.domain.bo.QlContractInfoSaleBo;
import com.ruoyi.ql.domain.vo.QlContractGoodsRelVo;
import com.ruoyi.ql.domain.vo.QlContractInfoSaleVo;
import com.ruoyi.ql.mapper.QlContractInfoSaleMapper;
import com.ruoyi.ql.service.IQlContractGoodsRelService;
import com.ruoyi.ql.service.IQlContractInfoSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 合同管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlContractInfoSaleServiceImpl implements IQlContractInfoSaleService {

    private final QlContractInfoSaleMapper baseMapper;

    private final IQlContractGoodsRelService contractGoodsRelService;

    /**
     * 查询合同管理
     */
    @Override
    public QlContractInfoSaleVo queryById(Long id) {
        QlContractInfoSaleVo qlContractInfoSaleVo = baseMapper.selectVoById(id);
        if(ObjectUtil.isNotNull(qlContractInfoSaleVo.getId())) {
            QlContractGoodsRelBo qlContractGoodsRelBo = new QlContractGoodsRelBo();
            qlContractGoodsRelBo.setContractId(qlContractInfoSaleVo.getId());
            List<QlContractGoodsRelVo> qlContractGoodsRels = contractGoodsRelService.queryList(qlContractGoodsRelBo);
            qlContractInfoSaleVo.setContractGoodsRels(qlContractGoodsRels);
        }
        return qlContractInfoSaleVo;
    }

    /**
     * 查询合同管理列表
     */
    @Override
    public TableDataInfo<QlContractInfoSaleVo> queryPageList(QlContractInfoSaleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlContractInfoSale> lqw = buildQueryWrapper(bo);
        Page<QlContractInfoSaleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        if (ObjectUtil.isNull(result) || CollUtil.isEmpty(result.getRecords())) {
            return TableDataInfo.build(result);
        }
        findContractGoodsRels(result.getRecords());
        return TableDataInfo.build(result);
    }

    /**
     * 查询合同管理列表
     */
    @Override
    public List<QlContractInfoSaleVo> queryList(QlContractInfoSaleBo bo) {
        LambdaQueryWrapper<QlContractInfoSale> lqw = buildQueryWrapper(bo);
        List<QlContractInfoSaleVo> qlContractInfoSaleVos = baseMapper.selectVoList(lqw);
        findContractGoodsRels(qlContractInfoSaleVos);
        return qlContractInfoSaleVos;
    }

    private void findContractGoodsRels(List<QlContractInfoSaleVo> contractInfoSaleVos) {
        if(CollUtil.isEmpty(contractInfoSaleVos)) {
            return;
        }
        List<Long> paymentIds = contractInfoSaleVos.stream().map(QlContractInfoSaleVo::getId).collect(Collectors.toList());
        QlContractGoodsRelBo contractGoodsRelBo = new QlContractGoodsRelBo();
        contractGoodsRelBo.setContractIds(paymentIds);
        List<QlContractGoodsRelVo> contractGoodsRelVos = contractGoodsRelService.queryList(contractGoodsRelBo);
        if (CollUtil.isEmpty(contractGoodsRelVos)) {
            return;
        }
        Map<Long, List<QlContractGoodsRelVo>> contractGoodsRelMap = contractGoodsRelVos.stream().collect(Collectors.groupingBy(QlContractGoodsRelVo::getContractId));
        for (QlContractInfoSaleVo contractInfoSaleVo : contractInfoSaleVos) {
            if (!contractGoodsRelMap.containsKey(contractInfoSaleVo.getId())) {
                continue;
            }
            contractInfoSaleVo.setContractGoodsRels(contractGoodsRelMap.get(contractInfoSaleVo.getId()));
        }
    }

    private LambdaQueryWrapper<QlContractInfoSale> buildQueryWrapper(QlContractInfoSaleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlContractInfoSale> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getContractCode()), QlContractInfoSale::getContractCode, bo.getContractCode());
        lqw.like(StringUtils.isNotBlank(bo.getContractName()), QlContractInfoSale::getContractName, bo.getContractName());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlContractInfoSale::getCustomerName, bo.getCustomerName());
        lqw.eq(bo.getAmount() != null, QlContractInfoSale::getAmount, bo.getAmount());
        lqw.eq(bo.getContactDate() != null, QlContractInfoSale::getContactDate, bo.getContactDate());
        lqw.eq(bo.getRetentionDate() != null, QlContractInfoSale::getRetentionDate, bo.getRetentionDate());
        lqw.in(CollUtil.isNotEmpty(bo.getContractCodes()), QlContractInfoSale::getContractCode, bo.getContractCodes());
        return lqw;
    }

    /**
     * 新增合同管理
     */
    @Override
    public Boolean insertByBo(QlContractInfoSaleBo bo) {
        QlContractInfoSale add = BeanUtil.toBean(bo, QlContractInfoSale.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        if (CollUtil.isNotEmpty(bo.getContractGoodsRels())) {
            for (QlContractGoodsRelBo contractGoodsRel : bo.getContractGoodsRels()) {
                contractGoodsRel.setContractId(bo.getId());
                contractGoodsRel.setContractType("sale");
                contractGoodsRelService.insertByBo(contractGoodsRel);
            }
        }
        return flag;
    }

    /**
     * 修改合同管理
     */
    @Override
    public Boolean updateByBo(QlContractInfoSaleBo bo) {
        QlContractInfoSale update = BeanUtil.toBean(bo, QlContractInfoSale.class);
        validEntityBeforeSave(update);
        LambdaQueryWrapper<QlContractGoodsRel> ql = new LambdaQueryWrapper<>();
        ql.eq(QlContractGoodsRel::getContractId, bo.getId());

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
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlContractInfoSale entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除合同管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        boolean deleteResult = baseMapper.deleteBatchIds(ids) > 0;
        if (deleteResult) {
            List<Long> idList = ids.stream().distinct().collect(Collectors.toList());
            if(CollUtil.isEmpty(idList)) {
                return true;
            }
            QlContractGoodsRelBo qlContractGoodsRelBo = new QlContractGoodsRelBo();
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
}
