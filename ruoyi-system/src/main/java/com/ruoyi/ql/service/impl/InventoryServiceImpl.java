package com.ruoyi.ql.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.ql.dao.InventoryDAO;
import com.ruoyi.ql.domain.report.query.QlInventoryQuery;
import com.ruoyi.ql.domain.report.vo.InventoryDetail;
import com.ruoyi.ql.service.IInventoryService;
import com.ruoyi.ql.service.IQlOutboundService;
import com.ruoyi.ql.service.IQlWarehousingDetailService;
import com.ruoyi.ql.service.IQlWarehousingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventoryServiceImpl implements IInventoryService {

    @Autowired
    private InventoryDAO inventoryDao;

    @Autowired
    private IQlWarehousingDetailService iQlWarehousingDetailService;

    @Autowired
    private IQlWarehousingService iQlWarehousingService;

    @Autowired
    private IQlOutboundService iQlOutboundService;

    public List<InventoryDetail> monthlySummary(QlInventoryQuery inventoryQuery) {
        if (StrUtil.isBlank(inventoryQuery.getStartMonth())) {
            inventoryQuery.setStartMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
        }
        if (StrUtil.isBlank(inventoryQuery.getEndMonth())) {
            inventoryQuery.setEndMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
        }

        String startMonth = inventoryQuery.getStartMonth();
        String endMonth = inventoryQuery.getEndMonth();
        LocalDate startDate = DateUtil.parse((inventoryQuery.getStartMonth() + "-01"), "yyyy-MM-dd").toLocalDateTime().toLocalDate();
        LocalDate endDate = DateUtil.endOfMonth(DateUtil.parse((inventoryQuery.getEndMonth() + "-01"), "yyyy-MM-dd")).toLocalDateTime().toLocalDate();
        inventoryQuery.setStartDate(startDate);
        inventoryQuery.setEndDate(endDate);

        // 查询startDate至endDate出库和入库记录
        List<InventoryDetail> outboundInventoryDetails = inventoryDao.queryOutbound(inventoryQuery);
        List<InventoryDetail> warehousingInventoryDetails = inventoryDao.queryWarehousing(inventoryQuery);
        if (CollUtil.isEmpty(outboundInventoryDetails) && CollUtil.isEmpty(warehousingInventoryDetails)) {
            return Collections.emptyList();
        }

        List<String> goodsIds = new ArrayList<>();
        goodsIds.addAll(outboundInventoryDetails.stream().map(InventoryDetail::getGoodsId).collect(Collectors.toList()));
        goodsIds.addAll(warehousingInventoryDetails.stream().map(InventoryDetail::getGoodsId).collect(Collectors.toList()));
        goodsIds = goodsIds.stream().distinct().collect(Collectors.toList());


        inventoryQuery.setStartMonth(null);
        inventoryQuery.setGoodsIds(goodsIds);
        // 查询endDate出库和入库累计出库和出库记录
        List<InventoryDetail> outboundInventoryDetailList = inventoryDao.queryOutbound(inventoryQuery);
        List<InventoryDetail> warehousingInventoryDetailList = inventoryDao.queryWarehousing(inventoryQuery);

        Map<String, List<InventoryDetail>> outboundInventoryDetailGroup = outboundInventoryDetails.stream().collect(Collectors.groupingBy(InventoryDetail::getGoodsId));
        Map<String, List<InventoryDetail>> warehousingInventoryDetailGroup = warehousingInventoryDetails.stream().collect(Collectors.groupingBy(InventoryDetail::getGoodsId));

        Map<String, List<InventoryDetail>> outboundGroup = outboundInventoryDetailList.stream().collect(Collectors.groupingBy(InventoryDetail::getGoodsId));
        Map<String, List<InventoryDetail>> warehousingGroup = warehousingInventoryDetailList.stream().collect(Collectors.groupingBy(InventoryDetail::getGoodsId));


        Map<String, InventoryDetail> inventoryDetailMap = new HashMap<>();

        for (String goodsId : goodsIds) {
            InventoryDetail inventoryDetail = null;
            if (inventoryDetailMap.containsKey(goodsId)) {
                inventoryDetail = inventoryDetailMap.get(goodsId);
            } else {

                inventoryDetail = new InventoryDetail();

                InventoryDetail detail = null;
                if (warehousingInventoryDetailGroup.containsKey(goodsId)) {
                    detail = warehousingInventoryDetailGroup.get(goodsId).get(0);
                } else{
                    detail = outboundInventoryDetailGroup.get(goodsId).get(0);
                }

                inventoryDetail.setGoodsId(goodsId);
                inventoryDetail.setGoodsCode(detail.getGoodsCode());
                inventoryDetail.setGoodsName(detail.getGoodsName());
                inventoryDetail.setGoodsSearchstandard(detail.getGoodsSearchstandard());
                inventoryDetail.setGoodsUnit(detail.getGoodsUnit());
                inventoryDetail.setInventoryCode(detail.getInventoryCode());

                inventoryDetail.setStartMonth(startMonth);
                inventoryDetail.setEndMonth(endMonth);
            }

            inventoryDetailMap.put(goodsId, inventoryDetail);

            // 入库数量和入库金额-汇总入库单的数量和金额
            if (warehousingInventoryDetailGroup.containsKey(goodsId)) {
                List<InventoryDetail> inventoryDetails = warehousingInventoryDetailGroup.get(goodsId);
                for (InventoryDetail detail : inventoryDetails) {
                    inventoryDetail.setWarehousingQuantity(inventoryDetail.getWarehousingQuantity().add(detail.getWarehousingQuantity()));
                    inventoryDetail.setWarehousingAmount(inventoryDetail.getWarehousingAmount().add(detail.getWarehousingAmount()));
                }
            }

            // 入库数量和入库金额-汇总出库单的数量和金额
            if (outboundInventoryDetailGroup.containsKey(goodsId)) {
                List<InventoryDetail> inventoryDetails = outboundInventoryDetailGroup.get(goodsId);
                for (InventoryDetail detail : inventoryDetails) {
                    inventoryDetail.setOutboundQuantity(inventoryDetail.getOutboundQuantity().add(detail.getOutboundQuantity()));
                    inventoryDetail.setOutboundAmount(inventoryDetail.getOutboundAmount().add(detail.getOutboundAmount()));
                }
            }

            // 累计库存-增加入库数量 累计金额-增加入库金额
            if (warehousingGroup.containsKey(goodsId)) {
                List<InventoryDetail> inventoryDetails = warehousingGroup.get(goodsId);
                for (InventoryDetail detail : inventoryDetails) {
                    inventoryDetail.setAccumulateQuantity(inventoryDetail.getAccumulateQuantity().add(detail.getWarehousingQuantity()));
                    inventoryDetail.setAccumulateAmount(inventoryDetail.getAccumulateAmount().add(detail.getWarehousingAmount()));
                }
            }

            // 累计库存-减去出库数量 累计金额-减去出库金额
            if(outboundGroup.containsKey(goodsId)) {
                List<InventoryDetail> inventoryDetails = outboundGroup.get(goodsId);
                for (InventoryDetail detail : inventoryDetails) {
                    inventoryDetail.setAccumulateQuantity(inventoryDetail.getAccumulateQuantity().subtract(detail.getOutboundQuantity()));
                    inventoryDetail.setAccumulateAmount(inventoryDetail.getAccumulateAmount().subtract(detail.getAccumulateAmount()));
                }
            }
        }

        return new ArrayList<>(inventoryDetailMap.values());
    }

    @Override
    public List<InventoryDetail> annualSummary(QlInventoryQuery inventoryQuery) {
        if (StrUtil.isBlank(inventoryQuery.getStartMonth())) {
            inventoryQuery.setStartMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
        }
        if (StrUtil.isBlank(inventoryQuery.getEndMonth())) {
            inventoryQuery.setEndMonth(DateUtil.format(DateUtil.date(), "yyyy-MM"));
        }
        LocalDate startDate = DateUtil.parse(DateUtil.year(DateUtil.parse((inventoryQuery.getStartMonth() + "-01"), "yyyy-MM-dd")) + "-01-01", "yyyy-MM-dd").toLocalDateTime().toLocalDate();
        LocalDate endDate = DateUtil.endOfMonth(DateUtil.parse((inventoryQuery.getEndMonth() + "-01"), "yyyy-MM-dd")).toLocalDateTime().toLocalDate();
        inventoryQuery.setStartDate(startDate);
        inventoryQuery.setEndDate(endDate);

        // 查询startDate至endDate出库和入库记录
        List<InventoryDetail> outboundInventoryDetails = inventoryDao.queryOutbound(inventoryQuery);
        List<InventoryDetail> warehousingInventoryDetails = inventoryDao.queryWarehousing(inventoryQuery);
        if (CollUtil.isEmpty(outboundInventoryDetails) && CollUtil.isEmpty(warehousingInventoryDetails)) {
            return Collections.emptyList();
        }

        List<String> goodsIds = new ArrayList<>();
        goodsIds.addAll(outboundInventoryDetails.stream().map(InventoryDetail::getGoodsId).collect(Collectors.toList()));
        goodsIds.addAll(warehousingInventoryDetails.stream().map(InventoryDetail::getGoodsId).collect(Collectors.toList()));
        goodsIds = goodsIds.stream().distinct().collect(Collectors.toList());

        inventoryQuery.setStartMonth(null);
        inventoryQuery.setGoodsIds(goodsIds);
        // 查询endDate出库和入库累计出库和出库记录
        List<InventoryDetail> outboundInventoryDetailList = inventoryDao.queryOutbound(inventoryQuery);
        List<InventoryDetail> warehousingInventoryDetailList = inventoryDao.queryWarehousing(inventoryQuery);

        List<InventoryDetail> inventoryDetails = new ArrayList<>();

        InventoryDetail inventoryDetail = new InventoryDetail();

        for (InventoryDetail detail : warehousingInventoryDetails) {
            inventoryDetail.setWarehousingQuantity(inventoryDetail.getWarehousingQuantity().add(detail.getWarehousingQuantity()));
            inventoryDetail.setWarehousingAmount(inventoryDetail.getWarehousingAmount().add(detail.getWarehousingAmount()));
        }

        for (InventoryDetail detail : outboundInventoryDetails) {
            inventoryDetail.setOutboundQuantity(inventoryDetail.getOutboundQuantity().add(detail.getOutboundQuantity()));
            inventoryDetail.setOutboundAmount(inventoryDetail.getOutboundAmount().add(detail.getOutboundAmount()));
        }

        for (InventoryDetail detail : warehousingInventoryDetailList) {
            inventoryDetail.setAccumulateQuantity(inventoryDetail.getAccumulateQuantity().add(detail.getWarehousingQuantity()));
            inventoryDetail.setAccumulateAmount(inventoryDetail.getAccumulateAmount().add(detail.getWarehousingAmount()));
        }

        for (InventoryDetail detail : outboundInventoryDetailList) {
            inventoryDetail.setAccumulateQuantity(inventoryDetail.getAccumulateQuantity().subtract(detail.getOutboundQuantity()));
            inventoryDetail.setAccumulateAmount(inventoryDetail.getAccumulateAmount().subtract(detail.getOutboundAmount()));
        }

        inventoryDetails.add(inventoryDetail);

        return inventoryDetails;
    }

    @Override
    public List<InventoryDetail> detail(QlInventoryQuery inventoryQuery) {
        log.info("InventoryServiceImpl.query.inventoryQuery: {}", inventoryQuery);
        List<InventoryDetail> inventoryDetails = inventoryDao.query(inventoryQuery);
        if (CollUtil.isEmpty(inventoryDetails)) {
            return inventoryDetails;
        }
        for (InventoryDetail inventoryDetail : inventoryDetails) {
            LocalDate inventoryDate = inventoryDetail.getInventoryDate();
            inventoryDetail.setMonth(inventoryDate.getMonth().getValue());
            inventoryDetail.setDay(inventoryDate.getDayOfMonth());
        }

        // 查询累计库存
        Map<String, InventoryDetail> cumulativeInventoryMap = queryCumulativeInventory(inventoryQuery);

        // 计算累计库存
        calCumulativeInventory(inventoryDetails, cumulativeInventoryMap);

        inventoryDetails.sort(Comparator.comparing(InventoryDetail::getInventoryDate));

        log.info("InventoryServiceImpl.query.inventoryDetails: {}", inventoryDetails);
        return inventoryDetails;
    }

    private Map<String, InventoryDetail> queryCumulativeInventory(QlInventoryQuery inventoryQuery) {
        List<InventoryDetail> cumulativeInventoryList = inventoryDao.queryCumulativeInventory(inventoryQuery);
        if(CollUtil.isEmpty(cumulativeInventoryList)) {
            return new HashMap<>();
        }
        return cumulativeInventoryList.stream().collect(Collectors.toMap(InventoryDetail::getGoodsId, inventoryDetail -> inventoryDetail));
    }

    private void calCumulativeInventory(List<InventoryDetail> inventoryDetails, Map<String, InventoryDetail> cumulativeInventoryMap) {
        if(CollUtil.isEmpty(inventoryDetails)) {
            return;
        }


        inventoryDetails.sort(Comparator.comparing(InventoryDetail::getInventoryDate));

        Map<String, List<InventoryDetail>> inventoryDetailGroup = inventoryDetails.stream().collect(Collectors.groupingBy(InventoryDetail::getGoodsId));

        inventoryDetailGroup.forEach((goodsId, inventoryDetailList) -> {
            InventoryDetail cumulativeInventory = cumulativeInventoryMap.get(goodsId);
            BigDecimal accumulateQuantity = new BigDecimal("0.0");
            BigDecimal accumulateAmount = new BigDecimal("0.0");
            BigDecimal accumulatePrice = new BigDecimal("0.0");
            if(ObjUtil.isNotNull(cumulativeInventory)) {
                accumulateQuantity = cumulativeInventory.getAccumulateQuantity();
                accumulateAmount = cumulativeInventory.getAccumulateAmount();
                accumulatePrice = cumulativeInventory.getAccumulatePrice();
            }

            for (int i = 0; i < inventoryDetailList.size(); i++) {
                // 当前时间的库存明细第一条去上月末的剩余库存数量，与自己的库存数量相加，等于累计库存数量，如果第一条是出库，则相减
                // 如果上月末没有库存，则第一条就是当前累计库存量，第一条库存不能为出库

                // 采购成本单价=库存单价=（（上期结存数量×库存单价）+本期入库总金金额）/（库存数量+本期入库数量）


                InventoryDetail inventoryDetail = inventoryDetailList.get(i);
                if(i == 0) {
                    BigDecimal price = accumulateQuantity.add(accumulatePrice).add(inventoryDetail.getWarehousingAmount()).divide(accumulateQuantity.add(inventoryDetail.getWarehousingQuantity()));
                    inventoryDetail.setAccumulatePrice(price);
                    inventoryDetail.setAccumulateQuantity(inventoryDetail.getWarehousingQuantity().add(accumulateQuantity));
                    inventoryDetail.setAccumulateAmount(inventoryDetail.getAccumulatePrice().multiply(inventoryDetail.getAccumulateQuantity()).add(accumulateAmount));
                } else {
                    InventoryDetail beforeInventoryDetail = inventoryDetails.get(i - 1);
                    if(inventoryDetail.getInventoryDirection().equals("warehousing")) {
                        BigDecimal price = accumulateQuantity.add(accumulatePrice).add(inventoryDetail.getWarehousingAmount()).divide(accumulateQuantity.add(inventoryDetail.getWarehousingQuantity()));
                        inventoryDetail.setAccumulateQuantity(inventoryDetail.getWarehousingQuantity().add(beforeInventoryDetail.getWarehousingQuantity()));
                        inventoryDetail.setAccumulatePrice(price);
                        inventoryDetail.setAccumulateAmount(inventoryDetail.getWarehousingAmount().add(beforeInventoryDetail.getAccumulateAmount()));
                    }else {
                        inventoryDetail.setAccumulateQuantity(inventoryDetail.getWarehousingQuantity().add(beforeInventoryDetail.getWarehousingQuantity()));
                        inventoryDetail.setAccumulatePrice(beforeInventoryDetail.getAccumulatePrice());

                        inventoryDetail.setOutboundPrice(beforeInventoryDetail.getAccumulatePrice());
                        inventoryDetail.setOutboundAmount(inventoryDetail.getOutboundQuantity().multiply(inventoryDetail.getOutboundPrice()));

                        inventoryDetail.setAccumulateAmount(beforeInventoryDetail.getAccumulateAmount().subtract(inventoryDetail.getOutboundAmount()));
                    }
                }
            }

        });


    }

}
