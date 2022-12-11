package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户账户信息业务对象 ql_basis_customer_accinfo
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlBasisCustomerAccinfoBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 客户id
     */
    @NotBlank(message = "客户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerId;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    /**
     * 卡号
     */
    @NotBlank(message = "卡号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankNo;

    /**
     * 开户行
     */
    @NotBlank(message = "开户行不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankName;

    /**
     * 账户类型
     */
    @NotBlank(message = "账户类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String accType;

    /**
     *
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;


}
