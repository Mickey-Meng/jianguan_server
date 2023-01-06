package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
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
    /**
     * 公共户开户行
     */
    @ExcelProperty(value = "公共户开户行")
    @ApiModelProperty(value = "公共户开户行", required = true)
    private String publicBankName;

    /**
     * 私户开户行
     */
    @ExcelProperty(value = "私户开户行")
    @ApiModelProperty(value = "私户开户行", required = true)
    private String privateBankName;

    /**
     * 公户账号
     */
    @ExcelProperty(value = "公户账号")
    @ApiModelProperty(value = "公户账号", required = true)
    private String publicBankNo;

    /**
     * 私户账号
     */
    @ExcelProperty(value = "私户账号")
    @ApiModelProperty(value = "私户账号", required = true)
    private String privateBankNo;

    /**
     * 客户所属地区
     */
    @ExcelProperty(value = "地区")
    @ApiModelProperty(value = "地区", required = true)
    private String area;

//    private List<QlBasisCustomerAccinfoVo> qlBasisCustomerAccinfoVoList;

}
