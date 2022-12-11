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
 * 供应商管理对象 ql_basis_supplier
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_basis_supplier")
public class QlBasisSupplier extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 供应商管理id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 单位关联主键
     */
    private Long warehouseId;
    /**
     * 供应商编码
     */
    private String supplierCode;
    /**
     * 供应商名称
     */
    private String supplierName;
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

}
