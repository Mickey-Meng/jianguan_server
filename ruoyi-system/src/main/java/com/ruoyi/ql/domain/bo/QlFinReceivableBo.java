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
 * 收款记录业务对象 ql_fin_receivable
 *
 * @author ruoyi
 * @date 2023-05-05
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlFinReceivableBo extends BaseEntity {

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
     * 本次收款款金额
     */
    @NotNull(message = "本次收款款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal receivableAmount;

    /**
     * 收款日期
     */
    @NotNull(message = "收款日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date receivableDate;

    /**
     * 收款摘要
     */
    @NotBlank(message = "收款摘要不能为空", groups = { AddGroup.class, EditGroup.class })
    private String receivableSummary;

    /**
     * 收款账号
     */
    @NotBlank(message = "收款账号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String accountNo;

    /**
     * 开户银行
     */
    @NotBlank(message = "开户银行不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankName;

    /**
     * 发票编号
     */
    @NotBlank(message = "发票编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String invoiceNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 附件
     */
    @NotBlank(message = "附件不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fj;


}
