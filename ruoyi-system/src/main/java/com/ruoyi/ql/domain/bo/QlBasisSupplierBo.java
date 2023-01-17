package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商管理业务对象 ql_basis_supplier
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlBasisSupplierBo extends BaseEntity {

    /**
     * 供应商管理id
     */
    private Long id;

    /**
     * 单位关联主键
     */
    private Long warehouseId;

    /**
     * 供应商编码
     */
    @NotBlank(message = "供应商编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierCode;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;

    /**
     * 联系人
     */
    @NotBlank(message = "联系人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contactPerson;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 手机
     */
    @NotBlank(message = "手机不能为空", groups = { AddGroup.class, EditGroup.class })
    private String mobilePhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 级别(关联字典)
     */
    private String supplierLevel;

    /**
     * 发票抬头
     */
    private String invoiceLookedUp;

    /**
     * 发票税率
     */
    private BigDecimal invoiceTax;

    /**
     * 发票种类【数据字典】
     */
    private String invoiceType;

    /**
     *
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 总付款金额
     */
    private BigDecimal payed;

    /**
     * 总欠款金额
     */
    private BigDecimal unpaid;

    /**
     * 已开发票金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 欠发票金额
     */
    private BigDecimal uninvoiceAmount;

}
