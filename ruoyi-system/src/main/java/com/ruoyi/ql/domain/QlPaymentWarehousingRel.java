package com.ruoyi.ql.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 付款与入库关系对象 ql_payment_warehousing_rel
 *
 * @author ruoyi
 * @date 2023-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_payment_warehousing_rel")
public class QlPaymentWarehousingRel extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 付款单ID
     */
    private Long paymentId;
    /**
     * 入库单编号
     */
    private String warehousingCode;
    /**
     * 本次付款金额
     */
    private BigDecimal payAmount;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 合同编号
     */
    private String contractCode;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}