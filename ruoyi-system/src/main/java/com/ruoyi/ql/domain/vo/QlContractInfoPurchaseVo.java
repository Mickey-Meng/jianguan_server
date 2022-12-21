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
import java.util.List;


/**
 * 采购合同 视图对象 ql_contract_info_purchase
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlContractInfoPurchaseVo {

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
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;

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

    private List<QlWarehousingVo> qlWarehousingVos;
}
