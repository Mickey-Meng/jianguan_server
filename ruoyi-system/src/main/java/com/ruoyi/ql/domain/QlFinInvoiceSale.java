package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售开票对象 ql_fin_invoice_sale
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_fin_invoice_sale")
public class QlFinInvoiceSale extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 合同id
     */
    private String contractId;
    /**
     * 合同编号
     */
    private String contractCode;
    /**
     * 合同名称
     */
    private String contractName;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 发票编号
     */
    private String invoiceNo;
    /**
     * 本次开票金额
     */
    private BigDecimal invoiceAmount;
    /**
     * 开票日期
     */
    private Date invoiceDate;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 附件
     */
    private String fj;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 折扣
     */
    private String discount;
}
