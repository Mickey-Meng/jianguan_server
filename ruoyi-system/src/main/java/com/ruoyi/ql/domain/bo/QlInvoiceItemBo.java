package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】业务对象 ql_invoice_item
 *
 * @author mickey
 * @date 2023-11-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlInvoiceItemBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 来源Id
     */
    @NotNull(message = "来源Id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sourceId;

    /**
     * 来源Id
     */
    @NotNull(message = "来源Id不能为空", groups = { AddGroup.class, EditGroup.class })
    private List<Long> sourceIds;

    /**
     * 来源类型 outbound：出库，warehousing：入库
     */
    @NotBlank(message = "来源类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sourceType;

    /**
     * 单号
     */
    @NotBlank(message = "单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 合同编码
     */
    @NotBlank(message = "合同编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractCode;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amount;

    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    @NotNull(message = "状态 0 审批中 1 审批完成 2 驳回不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

}
