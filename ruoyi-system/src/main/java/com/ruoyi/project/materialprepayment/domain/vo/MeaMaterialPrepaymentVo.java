package com.ruoyi.project.materialprepayment.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 材料预付款视图对象 mea_material_prepayment
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaMaterialPrepaymentVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private String id;

    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    private String bdbh;

    /**
     * 计量期次编号
     */
    @ExcelProperty(value = "计量期次编号")
    private String jlqsbh;

    /**
     * 材料编号，预付款编号
     */
    @ExcelProperty(value = "材料编号，预付款编号")
    private String clbh;

    /**
     * 材料名称
     */
    @ExcelProperty(value = "材料名称")
    private String clmc;

    /**
     * 材料名称
     */
    @ExcelProperty(value = "材料名称")
    private String dw;

    /**
     * 单价
     */
    @ExcelProperty(value = "单价")
    private BigDecimal dj;

    /**
     * 数量
     */
    @ExcelProperty(value = "数量")
    private BigDecimal sl;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    private BigDecimal je;

    /**
     * 预付比例
     */
    @ExcelProperty(value = "预付比例")
    private BigDecimal yfbl;

    /**
     * 预付金额
     */
    @ExcelProperty(value = "预付金额")
    private BigDecimal yfje;

    /**
     * 材料来源
     */
    @ExcelProperty(value = "材料来源")
    private String clly;

    /**
     * 单据编号
     */
    @ExcelProperty(value = "单据编号")
    private String djbh;

    /**
     * 质保书编号
     */
    @ExcelProperty(value = "质保书编号")
    private String zbsbh;

    /**
     *  抽检报告编号
     */
    @ExcelProperty(value = " 抽检报告编号")
    private String cjbgbh;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;


}
