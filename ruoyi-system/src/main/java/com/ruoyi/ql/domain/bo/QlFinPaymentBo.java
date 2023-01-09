package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商付款业务对象 ql_fin_payment
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlFinPaymentBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 供应商id
     */
    @NotBlank(message = "供应商id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierId;
    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;


    /**
     * 本次付款金额
     */
    @NotNull(message = "本次付款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal payAmount;

    /**
     * 付款方式
     */
    @NotBlank(message = "付款方式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String payType;

    /**
     * 欠付款金额
     */
    @NotNull(message = "欠付款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal unpaid;

    /**
     * 付款时间
     */
    @NotNull(message = "付款时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date paymentDate;

    /**
     *
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;


}
