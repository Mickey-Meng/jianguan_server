package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 供应商付款对象 ql_fin_payment
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_fin_payment")
public class QlFinPayment extends BaseEntity {

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
     * 合同名称
     */
    private String contractName;
    /**
     * 供应商id
     */
    private String supplierId;
    //供应商名称
    private String supplierName;

    /**
     * 本次付款金额
     */
    private BigDecimal payAmount;
    /**
     * 付款方式
     */
    private String payType;
    /**
     * 欠付款金额
     */
    private BigDecimal unpaid;
    /**
     * 付款时间
     */
    private Date paymentDate;
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

}
