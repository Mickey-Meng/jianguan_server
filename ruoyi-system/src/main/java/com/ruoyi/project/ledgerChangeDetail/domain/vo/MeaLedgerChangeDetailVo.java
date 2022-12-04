package com.ruoyi.project.ledgerChangeDetail.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 台账变更/工程变更 明细视图对象 mea_ledger_change_detail
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@Data
@ExcelIgnoreUnannotated
public class MeaLedgerChangeDetailVo {

    private static final long serialVersionUID = 1L;

    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    private String bdbh;

    /**
     * 变更编号
     */
    @ExcelProperty(value = "变更编号")
    private String bgbh;

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
     * 工程部位
     */
    @ExcelProperty(value = "工程部位")
    private String gcbw;

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
     * 新增单价
     */
    @ExcelProperty(value = "新增单价")
    private BigDecimal xzdj;

    /**
     * 合同数量
     */
    @ExcelProperty(value = "合同数量")
    private BigDecimal htsl;

    /**
     * 合同金额
     */
    @ExcelProperty(value = "合同金额")
    private BigDecimal htje;

    /**
     * 审核数量
     */
    @ExcelProperty(value = "审核数量")
    private BigDecimal shsl;

    /**
     * 审核金额
     */
    @ExcelProperty(value = "审核金额")
    private BigDecimal shje;

    /**
     * 修正数量
     */
    @ExcelProperty(value = "修正数量")
    private BigDecimal xzsl;

    /**
     * 修正金额
     */
    @ExcelProperty(value = "修正金额")
    private BigDecimal xzje;

    /**
     * 已计量数量
     */
    @ExcelProperty(value = "已计量数量")
    private BigDecimal yjlsl;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;


}
