package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 【请填写功能名称】视图对象 ql_invoice_item
 *
 * @author mickey
 * @date 2023-11-07
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "【请填写功能名称】", description = "【请填写功能名称】VO")
public class QlInvoiceItemVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 来源Id
     */
    @ExcelProperty(value = "来源Id")
    @ApiModelProperty(value = "来源Id", required = true)
    private Long sourceId;

    /**
     * 来源类型
     */
    @ExcelProperty(value = "来源类型")
    @ApiModelProperty(value = "来源类型", required = true)
    private String sourceType;

    /**
     * 单号
     */
    @ExcelProperty(value = "单号")
    @ApiModelProperty(value = "单号", required = true)
    private String code;

    /**
     * 合同编码
     */
    @ExcelProperty(value = "合同编码")
    @ApiModelProperty(value = "合同编码", required = true)
    private String contractCode;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    @ApiModelProperty(value = "金额", required = true)
    private BigDecimal amount;

    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    @ExcelProperty(value = "状态 0 审批中 1 审批完成 2 驳回")
    @ApiModelProperty(value = "状态 0 审批中 1 审批完成 2 驳回", required = true)
    private Integer status;



}
