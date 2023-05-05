package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.ql.domain.QlContractInfoSale;
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
    @ApiModelProperty(value = "客户资料id", required = true)
    private Long id;

    /**
     * 客户编码
     */
    @ExcelProperty(value = "客户编码")
    @ApiModelProperty(value = "客户编码", required = true)
    private String customerCode;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    @ApiModelProperty(value = "客户名称", required = true)
    private String customerName;

    /**
     * 营业执照(税号)
     */
    @ExcelProperty(value = "营业执照(税号)")
    @ApiModelProperty(value = "营业执照(税号)", required = true)
    private String businessLicense;

    /**
     * 联系人
     */
    @ExcelProperty(value = "联系人")
    @ApiModelProperty(value = "联系人", required = true)
    private String contactPerson;

    /**
     * 电话
     */
    @ExcelProperty(value = "电话")
    @ApiModelProperty(value = "电话", required = true)
    private String telephone;

    /**
     * 手机
     */
    @ExcelProperty(value = "手机")
    @ApiModelProperty(value = "手机", required = true)
    private String mobilePhone;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    /**
     * 客户所属地区
     */
    @ExcelProperty(value = "客户所属地区")
    @ApiModelProperty(value = "客户所属地区", required = true)
    private String area;

    /**
     * 地址描述
     */
    @ExcelProperty(value = "地址描述")
    @ApiModelProperty(value = "地址描述", required = true)
    private String address;

    /**
     * 联系人1
     */
    @ExcelProperty(value = "联系人1")
    @ApiModelProperty(value = "联系人1", required = true)
    private String contactPersonOne;

    /**
     * 电话1
     */
    @ExcelProperty(value = "电话1")
    @ApiModelProperty(value = "电话1", required = true)
    private String telephoneOne;

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
     * 发票抬头
     */
    @ExcelProperty(value = "发票抬头")
    @ApiModelProperty(value = "发票抬头", required = true)
    private String invoiceLookedUp;

    /**
     * 发票税率
     */
    @ExcelProperty(value = "发票税率")
    @ApiModelProperty(value = "发票税率", required = true)
    private BigDecimal invoiceTax;

    /**
     * 发票种类【数据字典】
     */
    @ExcelProperty(value = "发票种类【数据字典】")
    @ApiModelProperty(value = "发票种类【数据字典】", required = true)
    private String invoiceType;

    /**
     *
     */
    @ExcelProperty(value = "")
    @ApiModelProperty(value = "", required = true)
    private String remark;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    @ApiModelProperty(value = "部门ID", required = true)
    private Long deptId;

    private List<QlContractInfoSale> qlContractInfoSales ;
}
