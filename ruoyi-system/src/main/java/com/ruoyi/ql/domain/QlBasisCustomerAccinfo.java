package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户账户信息对象 ql_basis_customer_accinfo
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_basis_customer_accinfo")
public class QlBasisCustomerAccinfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 卡号
     */
    private String bankNo;
    /**
     * 开户行
     */
    private String bankName;
    /**
     * 账户类型
     */
    private String accType;
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
