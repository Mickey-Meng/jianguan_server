package com.ruoyi.system.domain;

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
 * 合同条款对象 contract_info
 *
 * @author ruoyi
 * @date 2022-12-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("contract_info")
public class ContractInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 标段编号
     */
    private String BDBH;
    /**
     * 开工日期,YYYY-MM-DD
     */
    private Date KGRQ;
    /**
     * 竣工日期,YYYY-MM-DD
     */
    private Date JGRQ;
    /**
     * 项目名称
     */
    private String XMMC;
    /**
     * 合同编号
     */
    @TableId(value = "HTBH")
    private String HTBH;
    /**
     * 合同总金额
     */
    private BigDecimal HTZJE;
    /**
     * 合同段
     */
    private String HTD;
    /**
     * 工程量清单金额
     */
    private BigDecimal GCLQDJE;
    /**
     * 开工预付款金额
     */
    private BigDecimal KGYFKJE;
    /**
     * 暂列金金额（元）
     */
    private BigDecimal ZLJJE;
    /**
     * 合同工期 (月/天  24/730)
     */
    private String HTGQ;
    /**
     * 开工预付款起扣比例（%）
     */
    private BigDecimal KGYFKQKBL;
    /**
     * 开工预付款截止比例（%）
     */
    private BigDecimal KGYFKJZBL;
    /**
     * 质保金扣款比例（%）
     */
    private BigDecimal ZBJKKBL;
    /**
     * 农民工工资保证金扣款比例（（%）
     */
    private BigDecimal NMGGZBZJKKBL;
    /**
     * 质保金总额（元）
     */
    private BigDecimal ZBJZE;
    /**
     * 计日工金额（元）
     */
    private BigDecimal JRGJE;
    /**
     * 标段长度（KM）
     */
    private BigDecimal BDCD;
    /**
     * 起讫桩号
     */
    private String QQZH;
    /**
     * 开工预付款支付情况
     */
    private String KGYJKZFQK;
    /**
     * 开工预付款扣回规定
     */
    private String KGYFKKHGD;
    /**
     * 材料预付款支付情况
     */
    private String CLYKFZFQK;
    /**
     * 材料预付款扣回规定
     */
    private String CLYFKKHGD;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
