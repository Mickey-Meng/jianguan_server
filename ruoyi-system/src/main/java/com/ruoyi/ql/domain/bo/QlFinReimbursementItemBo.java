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
 * 费用报销明细业务对象 ql_fin_reimbursement_item
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlFinReimbursementItemBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 报销订单ID
     */
    @NotBlank(message = "报销订单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String reimbursementOrderId;

    /**
     * 报销类型
     */
    @NotBlank(message = "报销类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String finReimbursementType;

    /**
     * 消费日期
     */
    @NotNull(message = "消费日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date reimbursementDate;

    /**
     * 消费金额
     */
    @NotNull(message = "消费金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal finAmount;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
