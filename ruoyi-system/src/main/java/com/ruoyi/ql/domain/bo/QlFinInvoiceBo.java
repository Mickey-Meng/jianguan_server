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
 * 供应商开票业务对象 ql_fin_invoice
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlFinInvoiceBo extends BaseEntity {

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
     * 本次开票金额
     */
    @NotNull(message = "本次开票金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal invoiceAmount;

    /**
     * 欠开票金额
     */
    @NotNull(message = "欠开票金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal uninvoice;

    /**
     * 开票时间
     */
    @NotNull(message = "开票时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date invoiceDate;

    /**
     *
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;


}
