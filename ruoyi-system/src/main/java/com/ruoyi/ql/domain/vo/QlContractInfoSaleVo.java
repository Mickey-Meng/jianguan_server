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

    /**
     * 总金额
     */
    @ExcelProperty(value = "总金额")
    private BigDecimal amount;

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

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    private Long deptId;


}
