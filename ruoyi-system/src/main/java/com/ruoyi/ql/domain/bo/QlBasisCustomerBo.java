package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.ql.domain.vo.QlBasisCustomerAccinfoVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户资料业务对象 ql_basis_customer
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlBasisCustomerBo extends BaseEntity {

    /**
     * 客户资料id
     */
    @NotNull(message = "客户资料id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 客户编码
     */
    private String customerCode;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    private List<String> customerNames;

    /**
     * 营业执照(税号)
     */
    private String businessLicense;

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
     * 客户所属地区
     */
    @NotBlank(message = "客户所属地区不能为空", groups = { AddGroup.class, EditGroup.class })
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
     *
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    //yangaogao 20221222 先暂时不做一对多，将公户信息和私户信息摊平放在客户信息表即可。
//    private List<QlBasisCustomerAccinfoVo> qlBasisCustomerAccinfoVoList;


    /**
     * 公共户开户行
     */
    @NotBlank(message = "公共户开户行不能为空", groups = { AddGroup.class, EditGroup.class })
    private String publicBankName;

    /**
     * 私户开户行
     */
    private String privateBankName;

    /**
     * 公户账号
     */
    @NotBlank(message = "公户账号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String publicBankNo;

    /**
     * 私户账号
     */
    private String privateBankNo;
}
