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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 销售开票视图对象 ql_fin_invoice_sale
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "销售开票", description = "销售开票VO")
public class QlFinInvoiceSaleVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 合同id
     */
    @ExcelProperty(value = "合同id")
    @ApiModelProperty(value = "合同id", required = true)
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
    @ApiModelProperty(value = "合同名称", required = true)
    private String contractName;

    /**
     * 客户id
     */
    @ExcelProperty(value = "客户id")
    @ApiModelProperty(value = "客户id", required = true)
    private String customerId;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    @ApiModelProperty(value = "客户名称", required = true)
    private String customerName;

    /**
     * 发票编号
     */
    @ExcelProperty(value = "发票编号")
    @ApiModelProperty(value = "发票编号", required = true)
    private String invoiceNo;

    /**
     * 本次开票金额
     */
    @ExcelProperty(value = "本次开票金额")
    @ApiModelProperty(value = "本次开票金额", required = true)
    private BigDecimal invoiceAmount;

    /**
     * 开票日期
     */
    @ExcelProperty(value = "开票日期")
    @ApiModelProperty(value = "开票日期", required = true)
    private Date invoiceDate;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 附件
     */
    @ExcelProperty(value = "附件")
    @ApiModelProperty(value = "附件", required = true)
    private String fj;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    @ApiModelProperty(value = "部门ID", required = true)
    private Long deptId;


}
