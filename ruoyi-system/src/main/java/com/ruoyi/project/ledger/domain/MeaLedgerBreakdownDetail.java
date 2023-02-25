package com.ruoyi.project.ledger.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账分解明细对象 mea_ledger_breakdown_detail
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_ledger_breakdown_detail")
public class MeaLedgerBreakdownDetail extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private String id;
    /**
     * 标段编号
     */
    private String bdbh;
    /**
     * 台账分解编号
     */
    private String tzfjbh;
    /**
     * 父级目录
     */
    private String fjmulu;
    /**
     * 子目号
     */
    private String zmh;
    /**
     * 子目名称
     */
    private String zmmc;
    /**
     * 单位
     */
    private String dw;
    /**
     * 合同单价
     */
    private BigDecimal htdj;
    /**
     * 设计数量
     */
    private BigDecimal sjsl;
    /**
     * 分解数量
     */
    private BigDecimal fjsl;
    /**
     * 变更数量
     */
    private BigDecimal bgsl;
    /**
     * 变更分解数量
     */
    private Long bgfjsl;
    /**
     * 复核数量
     */
    private BigDecimal fhsl;
    /**
     * 已计量数量
     */
    private BigDecimal yjlsl;
    /**
     * 复核金额
     */
    private BigDecimal fhje;
    /**
     * 状态（0正常 1变更）
     */
    private String fjlx;
    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 审批状态（0:未审批 1:审批中 2：已审批）
     */
    private String reviewCode;
    /**
     * 备注
     */
    private String remark;
    /**
     *  '数据是否是变更获取（1 变更数据 0正常合同）',
     */
    private String isChange;
}
