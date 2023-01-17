package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import liquibase.pro.packaged.S;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;



/**
 * 合同管理视图对象 ql_contract_info_sale
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlContractInfoSaleVo {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
    @ExcelProperty(value = "合同id")
    private Long id;

    /**
     * 合同编码
     */
    @ExcelProperty(value = "合同编码")
    private String contractCode;

    /**
     * 合同名称
     */
    @ExcelProperty(value = "合同名称")
    private String contractName;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

    private String customerId;
    /**
     * 总金额
     */
    @ExcelProperty(value = "总金额")
    private BigDecimal amount;
    private String fj;

    @ExcelProperty(value = "质保金")
    private BigDecimal retentionAmount;


    @ExcelProperty(value = "质保金到期时间")
    private Date retentionDate;

    @ExcelProperty(value = "税率")
    private BigDecimal rate;

    @ExcelProperty(value = "发货地不能为空")
    private String area;
    /**
     * 合同签订时间
     */
    @ExcelProperty(value = "合同签订时间")
    private Date contactDate;

    /**
     * 1已签订 0未签订
     */
    @ExcelProperty(value = "1已签订 0未签订")
    private String contractStatus;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;

}
