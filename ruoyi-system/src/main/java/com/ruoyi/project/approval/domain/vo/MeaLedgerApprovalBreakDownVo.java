package com.ruoyi.project.approval.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 台账报审视图对象 mea_ledger_approval
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaLedgerApprovalBreakDownVo {

    private static final long serialVersionUID = 1L;
    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    @ApiModelProperty(value = "标段编号", required = true)
    private String id;

    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    @ApiModelProperty(value = "标段编号", required = true)
    private String bdbh;

    /**
     * 申请期次
     */
    @ExcelProperty(value = "申请期次")
    @ApiModelProperty(value = "申请期次", required = true)
    private String sqqc;

    /**
     * 台账分解编号
     */
    @ExcelProperty(value = "台账分解编号")
    @ApiModelProperty(value = "台账分解编号", required = true)
    private String tzfjbh;

    /**
     * 工程部位
     */
    @ExcelProperty(value = "工程部位")
    @ApiModelProperty(value = "工程部位", required = true)
    private String gcbw;

    /**
     * 数据状态
     */
    @ExcelProperty(value = "数据状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    @ApiModelProperty(value = "数据状态", required = true)
    private String dataStatus;

    /**
     * 申报状态
     */
    @ExcelProperty(value = "申报状态")
    @ApiModelProperty(value = "申报状态", required = true)
    private String spzt;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;


    /**
     * 父级目录
     */
    @ExcelProperty(value = "父级目录")
    private String fjmulu;

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

}
