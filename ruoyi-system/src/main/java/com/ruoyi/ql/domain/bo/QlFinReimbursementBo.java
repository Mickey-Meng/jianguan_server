package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.ql.domain.vo.QlFinReimbursementItemVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 费用报销业务对象 ql_fin_reimbursement
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlFinReimbursementBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 报销订单ID，唯一标识某一次具体报销事件
     */
    private String reimbursementOrderId;

    /**
     * 报销日期
     */
    @NotNull(message = "报销日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date finReimbursementDate;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal finAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 员工姓名
     */
    @NotBlank(message = "员工姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empName;

    /**
     * 员工id
     */
    private String empId;

    private List<QlFinReimbursementItemVo> qlFinReimbursementItemVoList;


}
