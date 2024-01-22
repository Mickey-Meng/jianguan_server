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
import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.bo.QlWarehousingDetailBo;
import com.ruoyi.ql.domain.vo.QlOutboundVo;
import com.ruoyi.ql.domain.vo.QlWarehousingDetailVo;
import com.ruoyi.ql.domain.vo.QlWarehousingVo;
import com.ruoyi.ql.mapper.QlContractInfoPurchaseMapper;
import com.ruoyi.ql.mapper.QlOutboundMapper;
import com.ruoyi.ql.mapper.QlWarehousingMapper;
import com.ruoyi.ql.mapstruct.QlOutboundAndWarehousingMapstruct;
import com.ruoyi.ql.service.IQlOutboundService;
import com.ruoyi.ql.service.IQlWarehousingDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 出库管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@RequiredArgsConstructor
@Service
public class QlOutboundServiceImpl implements IQlOutboundService {

    private final QlOutboundMapper baseMapper;
    private final QlWarehousingMapper qlWarehousingMapper;

    private final QlContractInfoPurchaseMapper qlContractInfoPurchaseMapper;

    private final IQlWarehousingDetailService warehousingDetailService;

    /**
     * 查询出库管理
     */
    @Override
    public QlOutboundVo queryById(Long id){
        QlOutboundVo qlOutboundVo = baseMapper.selectVoById(id);
        List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(CollUtil.newArrayList(qlOutboundVo));
        qlOutboundVo.setWarehousingDetails(warehousingDetails);
        return qlOutboundVo;
    }

    /**
     * 查询出库管理列表
     */
    @Override
    public TableDataInfo<QlOutboundVo> queryPageList(QlOutboundBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlOutbound> lqw = buildQueryWrapper(bo);
        Page<QlOutboundVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(result);
        setWarehousingDetail(result, warehousingDetails);
        return TableDataInfo.build(result);
    }

    /**
     * 查询出库管理列表
     */
    @Override
    public List<QlOutboundVo> queryList(QlOutboundBo bo) {
        LambdaQueryWrapper<QlOutbound> lqw = buildQueryWrapper(bo);
        List<QlOutboundVo> qlOutboundVos = baseMapper.selectVoList(lqw);
        List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(qlOutboundVos);
        setWarehousingDetail(qlOutboundVos, warehousingDetails);
        return qlOutboundVos;
    }

    private LambdaQueryWrapper<QlOutbound> buildQueryWrapper(QlOutboundBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlOutbound> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getOutboundCode()), QlOutbound::getOutboundCode, bo.getOutboundCode());
        lqw.eq(bo.getOutboundDate() != null, QlOutbound::getOutboundDate, bo.getOutboundDate());
        lqw.eq(StringUtils.isNotBlank(bo.getSalesperson()), QlOutbound::getSalesperson, bo.getSalesperson());
        lqw.eq(StringUtils.isNotBlank(bo.getSaleContractCode()), QlOutbound::getSaleContractCode, bo.getSaleContractCode());
        lqw.eq(StringUtils.isNotBlank(bo.getPurchaseContractCode()), QlOutbound::getPurchaseContractCode, bo.getPurchaseContractCode());
        lqw.like(StringUtils.isNotBlank(bo.getCustomerName()), QlOutbound::getCustomerName, bo.getCustomerName());
        lqw.eq(StringUtils.isNotBlank(bo.getTelephone()), QlOutbound::getTelephone, bo.getTelephone());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), QlOutbound::getAddress, bo.getAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getProudctId()), QlOutbound::getProudctId, bo.getProudctId());
        lqw.like(StringUtils.isNotBlank(bo.getProudctName()), QlOutbound::getProudctName, bo.getProudctName());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsSearchstandard()), QlOutbound::getGoodsSearchstandard, bo.getGoodsSearchstandard());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsUnit()), QlOutbound::getGoodsUnit, bo.getGoodsUnit());
        lqw.eq(bo.getBasePrice() != null, QlOutbound::getBasePrice, bo.getBasePrice());
        lqw.eq(bo.getExtraPrice() != null, QlOutbound::getExtraPrice, bo.getExtraPrice());
        lqw.eq(StringUtils.isNotBlank(bo.getFj()), QlOutbound::getFj, bo.getFj());
        lqw.eq(bo.getSaleDate() != null, QlOutbound::getSaleDate, bo.getSaleDate());
        lqw.eq(bo.getSaleNumber() != null, QlOutbound::getSaleNumber, bo.getSaleNumber());
        lqw.eq(bo.getSaleAmount() != null, QlOutbound::getSaleAmount, bo.getSaleAmount());
        lqw.like(StringUtils.isNotBlank(bo.getOutboundUsername()), QlOutbound::getOutboundUsername, bo.getOutboundUsername());
        lqw.eq(StringUtils.isNotBlank(bo.getOutboundReleaseuser()), QlOutbound::getOutboundReleaseuser, bo.getOutboundReleaseuser());
        lqw.eq(bo.getOutboundNumber() != null, QlOutbound::getOutboundNumber, bo.getOutboundNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectId()), QlOutbound::getProjectId, bo.getProjectId());
        lqw.like(StringUtils.isNotBlank(bo.getProjectName()), QlOutbound::getProjectName, bo.getProjectName());
        lqw.in(CollUtil.isNotEmpty(bo.getIds()), QlOutbound::getId, bo.getIds());
        lqw.eq(ObjectUtil.isNotNull(bo.getId()), QlOutbound::getId, bo.getIds());
        lqw.eq(StringUtils.isNotBlank(bo.getLockStatus()), QlOutbound::getLockStatus, bo.getLockStatus());
        return lqw;
    }

    private List<QlWarehousingDetailVo> findWarehousingDetails(Page<QlOutboundVo> result) {
        if (ObjectUtil.isNull(result) || CollUtil.isEmpty(result.getRecords())) {
            return new ArrayList<>();
        }
        return findWarehousingDetails(result.getRecords());
    }

    private List<QlWarehousingDetailVo> findWarehousingDetails(List<QlOutboundVo> outboundVos) {
        if(CollUtil.isEmpty(outboundVos)) {
            return new ArrayList<>();
        }

        List<Long> inventoryIds = outboundVos.stream().map(QlOutboundVo::getId).collect(Collectors.toList());
        if(CollUtil.isEmpty(inventoryIds)) {
            return new ArrayList<>();
        }
        QlWarehousingDetailBo warehousingDetail = new QlWarehousingDetailBo();
        warehousingDetail.setInventoryDirection("outbound");
        warehousingDetail.setInventoryIds(inventoryIds);
        List<QlWarehousingDetailVo> warehousingDetails = warehousingDetailService.queryList(warehousingDetail);
        if (CollUtil.isEmpty(warehousingDetails)) {
            return new ArrayList<>();
        }
        return warehousingDetails;
    }

    private void setWarehousingDetail(Page<QlOutboundVo> result, List<QlWarehousingDetailVo> warehousingDetails) {
        if (ObjectUtil.isNull(result) || CollUtil.isEmpty(result.getRecords()) || CollUtil.isEmpty(warehousingDetails)) {
            return;
        }
        setWarehousingDetail(result.getRecords(), warehousingDetails);
    }

    private void setWarehousingDetail(List<QlOutboundVo> outboundVos, List<QlWarehousingDetailVo> warehousingDetails) {
        Map<Long, List<QlWarehousingDetailVo>> warehousingDetailMap = warehousingDetails.stream().collect(Collectors.groupingBy(QlWarehousingDetailVo::getInventoryId));
        for (QlOutboundVo warehousing : outboundVos) {
            if (!warehousingDetailMap.containsKey(warehousing.getId())) {
                continue;
            }
            List<QlWarehousingDetailVo> warehousingDetailsList = warehousingDetailMap.get(warehousing.getId());
            warehousing.setWarehousingDetails(warehousingDetailsList);
        }
    }

    /**
     * 新增出库管理
     */
    @Override
    public Boolean insertByBo(QlOutboundBo bo) {
        QlOutbound add = BeanUtil.toBean(bo, QlOutbound.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        for (QlWarehousingDetailBo warehousingDetail : bo.getWarehousingDetails()) {
            warehousingDetail.setInventoryDate(bo.getOutboundDate());
        }
        batchInsertDetail(bo.getWarehousingDetails(), add);
        return flag;
    }

    @Override
    public void batchInsertBo(List<QlOutboundBo> bos) {
        List<QlOutbound> entity = QlOutboundAndWarehousingMapstruct.INSTANCES.toEos(bos);
        baseMapper.insertBatch(entity);
        Map<String, QlOutbound> qlOutboundMap = entity.stream().collect(Collectors.toMap(QlOutbound::getOutboundCode, outbound -> outbound));
        for (QlOutboundBo bo : bos) {
            QlOutbound qlOutbound = qlOutboundMap.get(bo.getOutboundCode());
            for (QlWarehousingDetailBo warehousingDetail : bo.getWarehousingDetails()) {
                warehousingDetail.setInventoryDate(bo.getOutboundDate());
            }
            batchInsertDetail(bo.getWarehousingDetails(), qlOutbound);
        }
    }

    /**
     * 修改出库管理
     */
    @Override
    public Boolean updateByBo(QlOutboundBo bo) {
        QlOutbound update = BeanUtil.toBean(bo, QlOutbound.class);
        validEntityBeforeSave(update);
        Boolean updateResult = baseMapper.updateById(update) > 0;
        List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(CollUtil.newArrayList(BeanUtil.toBean(bo, QlOutboundVo.class)));
        if(CollUtil.isNotEmpty(warehousingDetails)) {
            List<Long> detailIds = warehousingDetails.stream().map(QlWarehousingDetailVo::getId).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(detailIds)) {
                warehousingDetailService.deleteWithValidByIds(detailIds, true);
            }
        }
        for (QlWarehousingDetailBo warehousingDetail : bo.getWarehousingDetails()) {
            warehousingDetail.setInventoryDate(bo.getOutboundDate());
        }
        batchInsertDetail(bo.getWarehousingDetails(), update);
        return updateResult;
    }

    private void batchInsertDetail(List<QlWarehousingDetailBo> warehousingDetails, QlOutbound outbound) {
        for (QlWarehousingDetailBo warehousingDetail : warehousingDetails) {
            warehousingDetail.setId(null);
            warehousingDetail.setInventoryDirection("outbound");
            warehousingDetail.setInventoryId(outbound.getId());
            warehousingDetail.setInventoryCode(outbound.getOutboundCode());
            warehousingDetailService.insertByBo(warehousingDetail);
        }
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlOutbound entity){
        String purchaseContractCode = entity.getPurchaseContractCode();
        Date outboundDate = entity.getOutboundDate();
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("contract_code",purchaseContractCode);
        objectObjectHashMap.put("del_flag", "0");
        List<QlWarehousingVo> qlWarehousingVos = qlWarehousingMapper.selectVoByMap(objectObjectHashMap);

        if (qlWarehousingVos.isEmpty()){
            throw new RuntimeException("根据采购合同"+purchaseContractCode+"查询入库记录不存在");
        }
        QlWarehousingVo qlWarehousingVo = qlWarehousingVos.get(0);
        Date arrivalDate = qlWarehousingVo.getArrivalDate();
        if(outboundDate.before(arrivalDate)){
            throw new RuntimeException("出库时期不能早于入库日期");
        }
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除出库管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        boolean deleteResult = baseMapper.deleteBatchIds(ids) > 0;
        if(deleteResult) {
            List<Long> idList = ids.stream().distinct().collect(Collectors.toList());
            if (CollUtil.isEmpty(idList)) {
                return true;
            }
            List<QlOutboundVo> inventorys = new ArrayList<>();
            for (Long inventoryId : idList) {
                QlOutboundVo outbound = new QlOutboundVo();
                outbound.setId(inventoryId);
                inventorys.add(outbound);
            }
            List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(inventorys);
            if(CollUtil.isEmpty(warehousingDetails)) {
                return true;
            }
            List<Long> detailIds = warehousingDetails.stream().map(QlWarehousingDetailVo::getId).distinct().collect(Collectors.toList());
            warehousingDetailService.deleteWithValidByIds(detailIds, true);
        }
        return deleteResult;
    }


}
