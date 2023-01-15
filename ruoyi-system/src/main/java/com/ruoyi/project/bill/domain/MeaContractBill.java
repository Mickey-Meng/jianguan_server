package com.ruoyi.project.bill.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 工程量清单对象 mea_contract_bill
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_contract_bill")
public class MeaContractBill extends TreeEntity<MeaContractBill> {

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
     * 子目号
     */
    private String zmh;
    /**
     * 子目名称
     */
    private String zmmc;
    /**
     * 子目号父节点
     */
    private String zmhParent;
    /**
     * 子目号祖级列表
     */
    private String zmhAncestors;
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
     * 修正数量
     */
    private BigDecimal xzsl;
    /**
     * 修正金额
     */
    private BigDecimal xzje;
    /**
     * 总数量
     */
    private BigDecimal zsl;
    /**
     * 总金额
     */
    private BigDecimal zje;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;



}
