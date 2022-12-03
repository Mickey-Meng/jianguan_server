package com.ruoyi.project.contract.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同条款对象 mea_contract_info
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_contract_info")
public class MeaContractInfo extends BaseEntity {

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
     * 合同编号
     */
    private String htbh;
    /**
     * 开工日期
     */
    private Date kgrq;
    /**
     * 竣工日期
     */
    private Date jgrq;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 合同总金额
     */
    private BigDecimal htzje;
    /**
     * 合同段
     */
    private String htd;
    /**
     * 工程量清单金额
     */
    private BigDecimal gclqdje;
    /**
     * 开工预付款金额
     */
    private BigDecimal kgyfkje;
    /**
     * 暂列金金额
     */
    private BigDecimal zljje;
    /**
     * 合同工期)
     */
    private String htgq;
    /**
     * 开工预付款起扣比例
     */
    private BigDecimal kgyfkqkbl;
    /**
     * 开工预付款截止比例
     */
    private BigDecimal kgyfkjzbl;
    /**
     * 质保金扣款比例
     */
    private BigDecimal zbjkkbl;
    /**
     * 农民工工资保证金扣款比例
     */
    private BigDecimal nmggzbzjkkbl;
    /**
     * 质保金总额
     */
    private BigDecimal zbjze;
    /**
     * 计日工金额
     */
    private BigDecimal jrgje;
    /**
     * 标段长度(km)
     */
    private BigDecimal bdcd;
    /**
     * 起讫桩号
     */
    private String qqzh;
    /**
     * 开工预付款支付情况
     */
    private String kgyjkzfqk;
    /**
     * 开工预付款扣回规定
     */
    private String kgyfkkhgd;
    /**
     * 材料预付款支付情况
     */
    private String clykfzfqk;
    /**
     * 材料预付款扣回规定
     */
    private String clyfkkhgd;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
