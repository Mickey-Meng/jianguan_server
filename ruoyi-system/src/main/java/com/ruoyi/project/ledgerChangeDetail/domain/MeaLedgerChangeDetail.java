package com.ruoyi.project.ledgerChangeDetail.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账变更/工程变更 明细对象 mea_ledger_change_detail
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_ledger_change_detail")
public class MeaLedgerChangeDetail extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    private String id;
    /**
     * 标段编号
     */
    private String bdbh;
    /**
     * 变更编号
     */
    private String bgbh;
    /**
     * 子目号
     */
    @TableId(value = "zmh")
    private String zmh;
    /**
     * 子目名称
     */
    private String zmmc;
    /**
     * 工程部位
     */
    private String gcbw;
    /**
     * 单位
     */
    private String dw;
    /**
     * 合同单价
     */
    private BigDecimal htdj;
    /**
     * 新增单价
     */
    private BigDecimal xzdj;
    /**
     * 合同数量
     */
    private BigDecimal htsl;
    /**
     * 合同金额
     */
    private BigDecimal htje;
    /**
     * 审核数量
     */
    private BigDecimal shsl;
    /**
     * 审核金额
     */
    private BigDecimal shje;
    /**
     * 变更数量
     */
    private BigDecimal bgsl;
    /**
     * 变更金额
     */
    private BigDecimal bgje;
    /**
     * 已计量数量
     */
    private BigDecimal yjlsl;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
