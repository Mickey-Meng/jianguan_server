package com.ruoyi.project.approval.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdown;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账报审对象 mea_ledger_approval
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_ledger_approval")
public class MeaLedgerApproval extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 标段编号
     */
    private String bdbh;
    /**
     * 申请期次
     */
    private String sqqc;
//    MeaLedgerApprovalNo meaLedgerApprovalNo;//add by yangaogao 20221207

    /**
     * 台账分解编号,需要关联台账分解对象，用于台账分解明细展示
     */
    private String tzfjbh;
//    MeaLedgerBreakdownDetail meaLedgerBreakdownDetail;//add by yangaogao 20221207
    /**
     * 工程部位
     */
    private String gcbw;
    /**
     * 数据状态
     */
    private String dataStatus;
    /**
     * 申报状态
     */
    private String spzt;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注 ，关联其次表和台账分解表。 不同期次可能对同一个台账分解单元进行报审。
报审的时候，是选择台账分解明细表里的数据，启动流程。
     */
    private String remark;

}
