package com.ruoyi.ql.domain.bo;


import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 付款与入库关系业务对象 ql_payment_warehousing_rel
 *
 * @author ruoyi
 * @date 2023-05-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlPaymentWarehousingRelBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 付款单ID
     */
    @NotNull(message = "付款单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long paymentId;

    /**
     * 付款单ID集合
     */
    private List<Long> paymentIds;

    /**
     * 入库单编号
     */
    @NotBlank(message = "入库单编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehousingCode;


    /**
     * 本次付款金额
     */
    @NotBlank(message = "本次付款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal payAmount;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;

    /**
     * 合同编号
     */
    @NotBlank(message = "合同编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractCode;
}