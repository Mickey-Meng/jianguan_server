package com.ruoyi.project.ledger.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 台账分解对象 mea_ledger_breakdown
 *
 * @author ruoyi
 * @date 2022-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_ledger_breakdown")
public class MeaLedgerBreakdown extends TreeEntity<MeaLedgerBreakdown> {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 标段编号
     */
    private String bdbh;
    /**
     * 台账分解编号
     */
    private String tzfjbh;
    /**
     * 台账分解编号父节点
     */
    private String tzfjbhParent;
    /**
     * 台账分解编号祖级列表
     */
    private String tzfjbhAncestors;
    /**
     * 台账分解名称
     */
    private String tzfjmc;
    /**
     * 分解类型（0正常 1变更）
     */
    private String fjlx;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     *  '数据是否是变更获取（1 变更数据 0正常合同）',
     */
    private String isChange;
}
