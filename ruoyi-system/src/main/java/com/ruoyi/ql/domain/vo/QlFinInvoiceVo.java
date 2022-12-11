package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



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


}
