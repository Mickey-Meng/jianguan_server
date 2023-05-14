package com.ruoyi.ql.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;




/**
 * 付款与入库关系视图对象 ql_payment_warehousing_rel
 *
 * @author ruoyi
 * @date 2023-05-10
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "付款与入库关系", description = "付款与入库关系VO")
public class QlPaymentWarehousingRelVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 付款单ID
     */
    @ExcelProperty(value = "付款单ID")
    @ApiModelProperty(value = "付款单ID", required = true)
    private Long paymentId;

    /**
     * 入库单编号
     */
    @ExcelProperty(value = "入库单编号")
    @ApiModelProperty(value = "入库单编号", required = true)
    private String warehousingCode;

    /**
     * 本次付款金额
     */
    @ExcelProperty(value = "本次付款金额")
    @ApiModelProperty(value = "本次付款金额", required = true)
    private BigDecimal payAmount;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    @ApiModelProperty(value = "供应商名称", required = true)
    private String supplierName;

    /**
     * 合同编号
     */
    @ExcelProperty(value = "合同编号")
    @ApiModelProperty(value = "合同编号", required = true)
    private String contractCode;

}