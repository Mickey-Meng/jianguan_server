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
import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.domain.bo.QlOutboundBo;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import com.ruoyi.ql.domain.bo.QlWarehousingDetailBo;
import com.ruoyi.ql.domain.vo.QlWarehousingDetailVo;
import com.ruoyi.ql.domain.vo.QlWarehousingVo;
import com.ruoyi.ql.mapper.QlOutboundMapper;
import com.ruoyi.ql.mapper.QlWarehousingMapper;
import com.ruoyi.ql.mapstruct.OutboundAndWarehousingMapstruct;
import com.ruoyi.ql.service.IQlOutboundService;
import com.ruoyi.ql.service.IQlWarehousingDetailService;
import com.ruoyi.ql.service.IQlWarehousingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 入库管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@RequiredArgsConstructor
@Service
public class QlWarehousingServiceImpl implements IQlWarehousingService {

    private final QlWarehousingMapper baseMapper;

    private final IQlWarehousingDetailService warehousingDetailService;
    private final QlOutboundMapper outboundMapper;

    /**
     * 查询入库管理
     */
    @Override
    public QlWarehousingVo queryById(Long id){
        QlWarehousingVo warehousing = baseMapper.selectVoById(id);
        List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(CollUtil.newArrayList(warehousing));
        warehousing.setWarehousingDetails(warehousingDetails);
        return warehousing;
    }

    /**
     * 查询入库管理列表
     */
    @Override
    public TableDataInfo<QlWarehousingVo> queryPageList(QlWarehousingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<QlWarehousing> lqw = buildQueryWrapper(bo);
        Page<QlWarehousingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(result);
        setWarehousingDetail(result, warehousingDetails);
        return TableDataInfo.build(result);
    }

    /**
     * 查询入库管理列表
     */
    @Override
    public List<QlWarehousingVo> queryList(QlWarehousingBo bo) {
        LambdaQueryWrapper<QlWarehousing> lqw = buildQueryWrapper(bo);
        List<QlWarehousingVo> warehousings = baseMapper.selectVoList(lqw);
        List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(warehousings);
        setWarehousingDetail(warehousings, warehousingDetails);
        return warehousings;
    }

    private LambdaQueryWrapper<QlWarehousing> buildQueryWrapper(QlWarehousingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<QlWarehousing> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getWarehousingCode()), QlWarehousing::getWarehousingCode, bo.getWarehousingCode());
        lqw.eq(StringUtils.isNotBlank(bo.getSupplierName()), QlWarehousing::getSupplierName, bo.getSupplierName());
        lqw.like(StringUtils.isNotBlank(bo.getWarehousingUsername()), QlWarehousing::getWarehousingUsername, bo.getWarehousingUsername());
        lqw.eq(StringUtils.isNotBlank(bo.getProudctId()), QlWarehousing::getProudctId, bo.getProudctId());
        lqw.eq(bo.getWarehousingNumber() != null, QlWarehousing::getWarehousingNumber, bo.getWarehousingNumber());
        lqw.eq(bo.getWarehousingDate() != null, QlWarehousing::getWarehousingDate, bo.getWarehousingDate());
        lqw.eq(StringUtils.isNotBlank(bo.getWarehousingStatus()), QlWarehousing::getWarehousingStatus, bo.getWarehousingStatus());
        lqw.eq(bo.getDeptId() != null, QlWarehousing::getDeptId, bo.getDeptId());
        lqw.like(StringUtils.isNotBlank(bo.getProudctName()), QlWarehousing::getProudctName, bo.getProudctName());
        lqw.eq(StringUtils.isNotBlank(bo.getPurchaseOrderId()), QlWarehousing::getPurchaseOrderId, bo.getPurchaseOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getLockStatus()), QlWarehousing::getLockStatus, bo.getLockStatus());
        lqw.in(CollUtil.isNotEmpty(bo.getIds()), QlWarehousing::getId, bo.getIds());
        return lqw;
    }

    private List<QlWarehousingDetailVo> findWarehousingDetails(Page<QlWarehousingVo> result) {
        if (ObjectUtil.isNull(result) || CollUtil.isEmpty(result.getRecords())) {
            return new ArrayList<>();
        }
        return findWarehousingDetails(result.getRecords());
    }

    private List<QlWarehousingDetailVo> findWarehousingDetails(List<QlWarehousingVo> warehousings) {
        if(CollUtil.isEmpty(warehousings)) {
            return new ArrayList<>();
        }

        List<Long> warehousingIds = warehousings.stream().map(QlWarehousingVo:: getId).collect(Collectors.toList());
        if(CollUtil.isEmpty(warehousingIds)) {
            return new ArrayList<>();
        }
        QlWarehousingDetailBo warehousingDetail = new QlWarehousingDetailBo();
        warehousingDetail.setInventoryDirection("warehousing");
        warehousingDetail.setInventoryIds(warehousingIds);
        List<QlWarehousingDetailVo> warehousingDetails = warehousingDetailService.queryList(warehousingDetail);
        if (CollUtil.isEmpty(warehousingDetails)) {
            return new ArrayList<>();
        }
        return warehousingDetails;
    }

    private void setWarehousingDetail(Page<QlWarehousingVo> result, List<QlWarehousingDetailVo> warehousingDetails) {
        if (ObjectUtil.isNull(result) || CollUtil.isEmpty(result.getRecords()) || CollUtil.isEmpty(warehousingDetails)) {
            return;
        }
        setWarehousingDetail(result.getRecords(), warehousingDetails);
    }

    private void setWarehousingDetail(List<QlWarehousingVo> warehousings, List<QlWarehousingDetailVo> warehousingDetails) {
        Map<Long, List<QlWarehousingDetailVo>> warehousingDetailMap = warehousingDetails.stream().collect(Collectors.groupingBy(QlWarehousingDetailVo::getInventoryId));
        for (QlWarehousingVo warehousing : warehousings) {
            if (!warehousingDetailMap.containsKey(warehousing.getId())) {
                continue;
            }
            List<QlWarehousingDetailVo> warehousingDetailsList = warehousingDetailMap.get(warehousing.getId());
            warehousing.setWarehousingDetails(warehousingDetailsList);
        }
    }

    /**
     * 新增入库管理
     */
    @Override
    @Transactional
    public Boolean insertByBo(QlWarehousingBo bo) {
        QlWarehousing add = BeanUtil.toBean(bo, QlWarehousing.class);
        validEntityBeforeSave(add);
        String productId = add.getProudctId();
        BigDecimal i = add.getWarehousingNumber();

        // TODO: 2023/5/18 修改实时库存
//        QlShopGoods qlShopGoods  = qlShopGoodsMapper.selectById(productId);
//        BigDecimal seedNumber = qlShopGoods.getStockNumber();
//
//        if ( i != null){
//            qlShopGoods.setStockNumber(seedNumber.add(i));
//            qlShopGoodsMapper.updateById(qlShopGoods);
//        }

        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        for (QlWarehousingDetailBo warehousingDetail : bo.getWarehousingDetails()) {
            warehousingDetail.setInventoryDate(bo.getArrivalDate());
        }
        batchInsertDetail(bo.getWarehousingDetails(), add);
        return flag;
    }


    @Override
    public void batchInsertBo(List<QlWarehousingBo> bos) {
        List<QlWarehousing> entity = OutboundAndWarehousingMapstruct.INSTANCES.toQlWarehousing(bos);
        baseMapper.insertBatch(entity);

        Map<String, QlWarehousing> qlOutboundMap = entity.stream().collect(Collectors.toMap(QlWarehousing::getWarehousingCode, warehousing -> warehousing));
        for (QlWarehousingBo bo : bos) {
            QlWarehousing warehousing = qlOutboundMap.get(bo.getWarehousingCode());
            batchInsertDetail(bo.getWarehousingDetails(), warehousing);
        }

    }

    /**
     * 修改入库管理
     */
    @Override
    @Transactional
    public Boolean updateByBo(QlWarehousingBo bo) {
        // 1 获取上一次入库清单

        QlWarehousing qlWarehousing = baseMapper.selectById(bo.getId());
        BigDecimal n = qlWarehousing.getWarehousingNumber();


        // 2  获取该产品实时库存数量
       /* QlShopGoods qlShopGoods  = qlShopGoodsMapper.selectById(bo.getProudctId());
        BigDecimal seedNumber = qlShopGoods.getStockNumber();
        // 3 修改后库存数据 =  实时库存 - 上一次入库数量 + 当前页面库存数量
        qlShopGoods.setStockNumber(seedNumber.add(bo.getWarehousingNumber()).subtract(n) );

        qlShopGoodsMapper.updateById(qlShopGoods);*/

        QlWarehousing update = BeanUtil.toBean(bo, QlWarehousing.class);
        validEntityBeforeSave(update);
        boolean updateResult = baseMapper.updateById(update) > 0;
        if(CollUtil.isEmpty(bo.getWarehousingDetails())) {
            return updateResult;
        }

        List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(CollUtil.newArrayList(BeanUtil.toBean(bo, QlWarehousingVo.class)));
        if (CollUtil.isNotEmpty(warehousingDetails)) {
            List<Long> detailIds = warehousingDetails.stream().map(QlWarehousingDetailVo::getId).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(detailIds)) {
                warehousingDetailService.deleteWithValidByIds(detailIds, true);
            }
        }
        for (QlWarehousingDetailBo warehousingDetail : bo.getWarehousingDetails()) {
            warehousingDetail.setInventoryDate(bo.getWarehousingDate());
        }
        batchInsertDetail(bo.getWarehousingDetails(), update);
        return updateResult;
    }

    private void batchInsertDetail(List<QlWarehousingDetailBo> warehousingDetails, QlWarehousing qlWarehousing){
        if (CollUtil.isEmpty(warehousingDetails)) {
            return;
        }
        for (QlWarehousingDetailBo warehousingDetail : warehousingDetails) {
            warehousingDetail.setId(null);
            warehousingDetail.setInventoryDirection("warehousing");
            warehousingDetail.setInventoryId(qlWarehousing.getId());
            warehousingDetail.setInventoryCode(qlWarehousing.getWarehousingCode());
            warehousingDetailService.insertByBo(warehousingDetail);
        }
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(QlWarehousing entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除入库管理
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
            List<QlWarehousingVo> warehousingList = new ArrayList<>();
            for (Long inventoryId : idList) {
                QlWarehousingVo warehousing = new QlWarehousingVo();
                warehousing.setId(inventoryId);
                warehousingList.add(warehousing);
            }
            List<QlWarehousingDetailVo> warehousingDetails = findWarehousingDetails(warehousingList);
            if(CollUtil.isEmpty(warehousingDetails)) {
                return true;
            }
            List<Long> detailIds = warehousingDetails.stream().map(QlWarehousingDetailVo::getId).distinct().collect(Collectors.toList());
            warehousingDetailService.deleteWithValidByIds(detailIds, true);
        }
        return deleteResult;
    }


    @Override
    public String getInventoryId(String type) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDateTime = currentDateTime.format(formatter);
        String str = "";
        if("CGRK".equals(type)){//入库单
            str = baseMapper.getInventoryId(formattedDateTime);
            if(null == str){
                str = "CGRK" + formattedDateTime + "-01";
            }else{
                String no = str.substring(str.length() - 2);
                Integer n = Integer.valueOf(no);
                n++;
                no = n.toString();
                if(no.length()<2){
                    no = "0" + no;
                }
                str = str.substring(0,str.length() - 2) + no;
            }
        }else  if("XSCK".equals(type)){//出库单
            str = outboundMapper.getInventoryId(formattedDateTime);
            if(null == str){
                str = "XSCK" + formattedDateTime + "-01";
            }else{
                String no = str.substring(str.length() - 2);
                Integer n = Integer.valueOf(no);
                n++;
                no = n.toString();
                if(no.length()<2){
                    no = "0" + no;
                }
                str = str.substring(0,str.length() - 2) + no;
            }
        }

        return str;
    }
}
