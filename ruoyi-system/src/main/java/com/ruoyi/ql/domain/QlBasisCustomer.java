package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户资料对象 ql_basis_customer
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_basis_customer")
public class QlBasisCustomer extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 客户资料id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 客户编码
     */
    private String customerCode;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 营业执照(税号)
     */
    private String businessLicense;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 手机
     */
    private String mobilePhone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 客户所属地区
     */
    private String area;
    /**
     * 地址描述
     */
    private String address;
    /**
     * 联系人1
     */
    private String contactPersonOne;
    /**
     * 电话1
     */
    private String telephoneOne;
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
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     *
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 公共户开户行
     */
    private String publicBankName;
    /**
     * 私户开户行
     */
    private String privateBankName;
    /**
     * 公户账号
     */
    private String publicBankNo;
    /**
     * 私户账号
     */
    private String privateBankNo;
}
