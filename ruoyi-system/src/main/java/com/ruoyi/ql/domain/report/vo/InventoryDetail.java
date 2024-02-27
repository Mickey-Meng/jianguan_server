package com.ruoyi.ql.domain.report.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InventoryDetail {

    /**
     * 月
     */
    private Integer month;

    /**
     * 日
     */
    private Integer day;

    /**
     * 物料代码
     */
    private String goodsId;


    /**
     * 物料代码
     */
    private String goodsCode;

    /**
     * 物料名称
     */
    private String goodsName;

    /**
     * 规格型号
     */
    private String goodsSearchstandard;

    /**
     * 单位
     */
    private String goodsUnit;

    /**
     * 出入库单号
     */
    private String inventoryCode;

    /**
     * 入库: warehousing, 出库: outbound
     */
    private String inventoryDirection;

    /**
     * 对应合同
     */
    private String contractCode;



    /**
     * 入库数量
     */
    private BigDecimal warehousingQuantity = new BigDecimal("0.0");

    /**
     * 入库单价
     */
    private BigDecimal warehousingPrice = new BigDecimal("0.0");


    /**
     * 入库金额
     */
    private BigDecimal warehousingAmount = new BigDecimal("0.0");



    /**
     * 出库数量
     */
    private BigDecimal outboundQuantity = new BigDecimal("0.0");

    /**
     * 入库单价
     */
    private BigDecimal outboundPrice = new BigDecimal("0.0");

    /**
     * 出库金额
     */
    private BigDecimal outboundAmount = new BigDecimal("0.0");

    /**
     * 累计数量
     */
    private BigDecimal accumulateQuantity = new BigDecimal("0.0");

    /**
     * 累计单价
     */
    private BigDecimal accumulatePrice = new BigDecimal("0.0");

    /**
     * 累计金额
     */
    private BigDecimal accumulateAmount = new BigDecimal("0.0");

    /**
     * 出库/入库日期
     */
    private LocalDate inventoryDate;

    /**
     * 起始月
     */
    private String startMonth;

    /**
     * 结束月
     */
    private String endMonth;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 客户名称
     */
    private String customerName;

}
