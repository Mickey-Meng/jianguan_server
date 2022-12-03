package com.ruoyi.project.materialprepayment.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料预付款对象 mea_material_prepayment
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_material_prepayment")
public class MeaMaterialPrepayment extends BaseEntity {

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
     * 计量期次编号
     */
    private String jlqsbh;
    /**
     * 材料编号，预付款编号
     */
    private String clbh;
    /**
     * 材料名称
     */
    private String clmc;
    /**
     * 材料名称
     */
    private String dw;
    /**
     * 单价
     */
    private BigDecimal dj;
    /**
     * 数量
     */
    private BigDecimal sl;
    /**
     * 金额
     */
    private BigDecimal je;
    /**
     * 预付比例
     */
    private BigDecimal yfbl;
    /**
     * 预付金额
     */
    private BigDecimal yfje;
    /**
     * 材料来源
     */
    private String clly;
    /**
     * 单据编号
     */
    private String djbh;
    /**
     * 质保书编号
     */
    private String zbsbh;
    /**
     *  抽检报告编号
     */
    private String cjbgbh;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
