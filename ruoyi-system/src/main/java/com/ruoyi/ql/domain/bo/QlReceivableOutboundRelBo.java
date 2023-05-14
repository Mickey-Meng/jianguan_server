package com.ruoyi.ql.domain.bo;


import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.ql.domain.vo.QlReceivableOutboundRelVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 付款与入库关系业务对象 ql_receivable_outbound_rel
 *
 * @author ruoyi
 * @date 2023-05-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlReceivableOutboundRelBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 收款单ID
     */
    @NotNull(message = "收款单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long receivableId;

    /**
     * 收款单ID集合
     */
    private List<Long> receivableIds;

    /**
     * 出库单编号
     */
    @NotBlank(message = "出库单编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String outboundCode;

    /**
     * 本次收款金额
     */
    @NotNull(message = "本次收款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amount;

    /**
     * 客户姓名
     */
    @NotBlank(message = "客户姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    /**
     * 销售合同编码
     */
    @NotBlank(message = "销售合同编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String saleContractCode;




}