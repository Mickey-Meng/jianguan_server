package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 ql_invoice_item
 *
 * @author mickey
 * @date 2023-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_invoice_item")
public class QlInvoiceItem extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 来源Id
     */
    private Long sourceId;
    /**
     * 来源类型
     */
    private String sourceType;
    /**
     * 单号
     */
    private String code;
    /**
     * 合同编码
     */
    private String contractCode;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
}
