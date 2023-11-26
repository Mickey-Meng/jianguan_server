package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;


/**
 * 供应商开票视图对象 ql_fin_invoice
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlFinInvoiceVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 合同id
     */
    @ExcelProperty(value = "合同id")
    private String contractId;
    /**
     * 合同编号
     */
    @ExcelProperty(value = "合同编号")
    @ApiModelProperty(value = "合同编号", required = true)
    private String contractCode;

    /**
     * 合同名称
     */
    @ExcelProperty(value = "合同名称")
    private String contractName;

    /**
     * 供应商id
     */
    @ExcelProperty(value = "供应商id")
    private String supplierId;

    /**
     * 本次开票金额
     */
    @ExcelProperty(value = "本次开票金额")
    private BigDecimal invoiceAmount;

    /**
     * 欠开票金额
     */
    @ExcelProperty(value = "欠开票金额")
    private BigDecimal uninvoice;

    /**
     * 开票时间
     */
    @ExcelProperty(value = "开票时间")
    private Date invoiceDate;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;

    /**
     * 发票编号
     */
    @ExcelProperty(value = "发票编号")
    @ApiModelProperty(value = "发票编号", required = true)
    private String invoiceNo;

    /**
     * 备注
     */
    private String remark;
    /**
     * 附件
     */
    @ExcelProperty(value = "附件")
    @ApiModelProperty(value = "附件", required = true)
    private String fj;
    /**
     * 折扣
     */
    @ExcelProperty(value = "折扣")
    @ApiModelProperty(value = "折扣", required = true)
    private String discount;

    private List<QlInvoiceItemVo> invoiceItems;
}
