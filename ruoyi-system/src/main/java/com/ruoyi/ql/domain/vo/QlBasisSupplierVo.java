package com.ruoyi.ql.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


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
    @ExcelProperty(value = "电话")
    private String telephone;
    @ExcelProperty(value = "邮箱")
    private String email;
    @ExcelProperty(value = "地址")
    private String address;
    /**
     * 手机
     */
    @ExcelProperty(value = "手机")
    private String mobilePhone;
    @ExcelProperty(value = "供应商名称")
    private String supplierLevel;


    @ExcelProperty(value = "发票抬头")
    private String invoiceLookedUp;

    @ExcelProperty(value = "发票税率")
    private BigDecimal invoiceTax;

    @ExcelProperty(value = "发票种类")
    private String invoiceType;
    /**
     * 总付款金额
     */
    @ExcelProperty(value = "总付款金额")
    @ApiModelProperty(value = "总付款金额", required = true)
    private BigDecimal payed;

    /**
     * 总欠款金额
     */
    @ExcelProperty(value = "总欠款金额")
    @ApiModelProperty(value = "总欠款金额", required = true)
    private BigDecimal unpaid;

    /**
     * 已开发票金额
     */
    @ExcelProperty(value = "已开发票金额")
    @ApiModelProperty(value = "已开发票金额", required = true)
    private BigDecimal invoiceAmount;

    /**
     * 欠发票金额
     */
    @ExcelProperty(value = "欠发票金额")
    @ApiModelProperty(value = "欠发票金额", required = true)
    private BigDecimal uninvoiceAmount;

    @ApiModelProperty(value = "采购合同编号", required = true)
    private List<String> contractPurchaseList;

    /**
     * 营业执照
     */
    private String fj;

    /**
     * 备注
     */
    private String remark;


    private String supplierEnglishName;

}
