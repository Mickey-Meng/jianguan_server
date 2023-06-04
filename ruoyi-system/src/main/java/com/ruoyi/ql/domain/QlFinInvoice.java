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
 * 供应商开票对象 ql_fin_invoice
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_fin_invoice")
public class QlFinInvoice extends BaseEntity {

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
     * 供应商id
     */
    private String supplierId;
    /**
     * 本次开票金额
     */
    private BigDecimal invoiceAmount;
    /**
     * 欠开票金额
     */
    private BigDecimal uninvoice;
    /**
     * 开票时间
     */
    private Date invoiceDate;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     *
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 发票编号
     */
    private String invoiceNo;
    /**
     * 附件
     */
    private String fj;
}
