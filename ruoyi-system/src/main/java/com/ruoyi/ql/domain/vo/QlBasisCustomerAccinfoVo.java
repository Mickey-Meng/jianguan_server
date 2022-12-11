package com.ruoyi.ql.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 客户账户信息视图对象 ql_basis_customer_accinfo
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlBasisCustomerAccinfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

    /**
     * 卡号
     */
    @ExcelProperty(value = "卡号")
    private String bankNo;

    /**
     * 开户行
     */
    @ExcelProperty(value = "开户行")
    private String bankName;

    /**
     * 账户类型
     */
    @ExcelProperty(value = "账户类型")
    private String accType;


}
