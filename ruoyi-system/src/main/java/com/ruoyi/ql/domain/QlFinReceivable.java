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
 * 收款记录对象 ql_fin_receivable
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_fin_receivable")
public class QlFinReceivable extends BaseEntity {

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
     * 本次收款款金额
     */
    private BigDecimal receivableAmount;
    /**
     * 收款日期
     */
    private Date receivableDate;
    /**
     * 收款摘要
     */
    private String receivableSummary;
    /**
     * 收款账号
     */
    private String accountNo;
    /**
     * 开户银行
     */
    private String bankName;
    /**
     * 发票编号
     */
    private String invoiceNo;
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
     * 部门ID
     */
    private Long deptId;
    /**
     * 附件
     */
    private String fj;

}
