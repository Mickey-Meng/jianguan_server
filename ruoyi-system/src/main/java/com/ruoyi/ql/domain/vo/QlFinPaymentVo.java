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
 * 供应商付款视图对象 ql_fin_payment
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlFinPaymentVo {

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
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;
    /**
     * 本次付款金额
     */
    @ExcelProperty(value = "本次付款金额")
    private BigDecimal payAmount;

    /**
     * 付款方式
     */
    @ExcelProperty(value = "付款方式")
    private String payType;

    /**
     * 欠付款金额
     */
    @ExcelProperty(value = "欠付款金额")
    private BigDecimal unpaid;

    /**
     * 付款时间
     */
    @ExcelProperty(value = "付款时间")
    private Date paymentDate;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    private Long deptId;


}
