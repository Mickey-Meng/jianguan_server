package com.ruoyi.project.ledgerChange.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.project.ledgerChangeDetail.domain.MeaLedgerChangeDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账变更/工程变更对象 mea_ledger_change
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_ledger_change")
public class MeaLedgerChange extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private String id;
    /**
     * 变更编号
     */
    private String bgbh;
    /**
     * 变更事项
     */
    private String bgsx;
    /**
     * 变更等级
     */
    private String bgdj;
    /**
     * 变更类型
     */
    private String bglx;
    /**
     * 桩号
     */
    private String zh;
    /**
     * 子目号
     */
    private String zmh;
    /**
     * 工程部位
     */
    private String gcbw;
    /**
     * 图号
     */
    private String th;
    /**
     * 申请日期
     */
    private Date sqrq;
    /**
     * 变更金额
     */
    private BigDecimal bgje;
    /**
     * 变更原因
     */
    private String bgyy;
    /**
     * 计算式
     */
    private String jss;
    /**
     * 数据状态
     */
    private String dataStatus;
    /**
     * 状态（0正常 1停用）
     */
    private String status;


    private String reviewCode;
    /**
     * 备注
     */
    private String remark;




}
