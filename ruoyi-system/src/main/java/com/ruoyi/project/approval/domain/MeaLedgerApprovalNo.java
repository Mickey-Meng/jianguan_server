package com.ruoyi.project.approval.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 期数管理对象 mea_ledger_approval_no
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_ledger_approval_no")
public class MeaLedgerApprovalNo extends BaseEntity {

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
     * 申请期次
     */
    private String sqqc;
    /**
     * 申报日期
     */
    private Date sbsj;
    /**
     * 申报结束日期
     */
    private Date sbjsrq;
    /**
     * 申报状态
     */
    private String spzt;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 申报状态（1申报中 2完成申报 3申报打回）
     */
    private String reviewCode;
    /**
     * 备注
     */
    private String remark;

}
