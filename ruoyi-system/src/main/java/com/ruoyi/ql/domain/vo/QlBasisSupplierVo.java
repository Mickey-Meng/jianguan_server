package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 供应商管理视图对象 ql_basis_supplier
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlBasisSupplierVo {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商管理id
     */
    @ExcelProperty(value = "供应商管理id")
    private Long id;

    /**
     * 供应商编码
     */
    @ExcelProperty(value = "供应商编码")
    private String supplierCode;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;

    /**
     * 联系人
     */
    @ExcelProperty(value = "联系人")
    private String contactPerson;

    /**
     * 手机
     */
    @ExcelProperty(value = "手机")
    private String mobilePhone;


}
