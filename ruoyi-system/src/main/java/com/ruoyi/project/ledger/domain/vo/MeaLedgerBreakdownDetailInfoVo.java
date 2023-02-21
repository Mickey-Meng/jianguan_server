package com.ruoyi.project.ledger.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


/**
 * 台账分解明细视图对象 mea_ledger_breakdown_detail
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@Data
@ExcelIgnoreUnannotated
public class MeaLedgerBreakdownDetailInfoVo {

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
     * 台账分解编号
     */
    @ExcelProperty(value = "台账分解编号")
    private String tzfjbh;

    /**
     * 父级目录
     */
    @ExcelProperty(value = "父级目录")
    private String tzfjmc;

    /**
     * 子目号
     */
    @ExcelProperty(value = "子目号")
    private String zmh;

    /**
     * 子目名称
     */
    @ExcelProperty(value = "子目名称")
    private String zmmc;

    /**
     * 单位
     */
    @ExcelProperty(value = "单位")
    private String dw;

    /**
     * 合同单价
     */
    @ExcelProperty(value = "合同单价")
    private BigDecimal htdj;

    /**
     * 设计数量
     */
    @ExcelProperty(value = "设计数量")
    private BigDecimal sjsl;

    /**
     * 分解数量
     */
    @ExcelProperty(value = "分解数量")
    private BigDecimal fjsl;

    /**
     * 变更数量
     */
    @ExcelProperty(value = "变更数量")
    private BigDecimal bgsl;

    /**
     * 变更分解数量
     */
    @ExcelProperty(value = "变更分解数量")
    private Long bgfjsl;
    /**
     * 复核数量
     */
    @ExcelProperty(value = "复核数量")
    private BigDecimal fhsl;

    /**
     * 已计量数量
     */
    @ExcelProperty(value = "已计量数量")
    private BigDecimal yjlsl;

    /**
     * 复核金额
     */
    @ExcelProperty(value = "复核金额")
    private BigDecimal fhje;

    /**
     * 状态（0正常 1变更）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "change_status")
    private String fjlx;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;

    private List<MeaLedgerBreakdownDetailVo> children;


}
