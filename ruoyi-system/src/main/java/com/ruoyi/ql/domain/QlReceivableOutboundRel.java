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
 * 付款与入库关系对象 ql_receivable_outbound_rel
 *
 * @author ruoyi
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_receivable_outbound_rel")
public class QlReceivableOutboundRel extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 收款单ID
     */
    private Long receivableId;
    /**
     * 出库单编号
     */
    private String outboundCode;
    /**
     * 本次收款金额
     */
    private BigDecimal amount;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 销售合同编码
     */
    private String saleContractCode;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private String delFlag;

}