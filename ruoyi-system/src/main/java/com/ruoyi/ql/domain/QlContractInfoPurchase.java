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
 * 采购合同 对象 ql_contract_info_purchase
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_contract_info_purchase")
public class QlContractInfoPurchase extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 合同id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 合同编码
     */
    private String contractCode;
    /**
     * 合同名称
     */
    private String contractName;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 供应商id
     */
    private String supplierId;
    /**
     * 总金额
     */
    private BigDecimal amount;
    /**
     * 合同签订时间
     */
    private Date contactDate;
    /**
     * 税率
     */
    private BigDecimal rate;
    /**
     * 1已签订 0未签订
     */
    private String contractStatus;
    /**
     * 附件
     */
    private String fj;
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
