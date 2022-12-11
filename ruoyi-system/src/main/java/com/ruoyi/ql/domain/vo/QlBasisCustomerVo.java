package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import java.util.List;


/**
 * 客户资料视图对象 ql_basis_customer
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlBasisCustomerVo {

    private static final long serialVersionUID = 1L;

    /**
     * 客户资料id
     */
    @ExcelProperty(value = "客户资料id")
    private Long id;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

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

    private List<QlBasisCustomerAccinfoVo> qlBasisCustomerAccinfoVoList;

}
