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
 * 销售开票业务对象 ql_fin_invoice_sale
 *
 * @author ruoyi
 * @date 2023-05-05
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlFinInvoiceSaleBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 合同id
     */
    @NotBlank(message = "合同id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractId;

    /**
     * 合同编号
     */
    @NotBlank(message = "合同编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractCode;

    /**
     * 合同名称
     */
    @NotBlank(message = "合同名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractName;

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
     * 发票编号
     */
    @NotBlank(message = "发票编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String invoiceNo;

    /**
     * 本次开票金额
     */
    @NotNull(message = "本次开票金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal invoiceAmount;

    /**
     * 开票日期
     */
    @NotNull(message = "开票日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date invoiceDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 附件
     */
    private String fj;

    /**
     * 部门ID
     */
    private Long deptId;


}
