package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
     * 合同编号
     */
//    @NotBlank(message = "合同编号不能为空", groups = { AddGroup.class, EditGroup.class })
//    private String contractCode;
    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 供应商id
     */
    private String supplierId;
    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;


    /**
     * 本次付款金额
     */
//    @NotNull(message = "本次付款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal payAmount;

    /**
     * 付款方式
     */
    @NotBlank(message = "付款方式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String payType;

    /**
     * 欠付款金额
     */
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

    /**
     * 付款账号
     */
    @NotBlank(message = "付款账号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String accountNo;

    /**
     * 开户银行
     */
    @NotBlank(message = "开户银行不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankName;

    /**
     * 发票编号
     */
//    @NotBlank(message = "发票编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String invoiceNo;

    /**
     * 附件
     */
    private String fj;

    /**
     * 入库单号
     */
//    @NotBlank(message = "入库单号不能为空", groups = { AddGroup.class, EditGroup.class })
//    private String warehousingCode;

    /**
     * 付款与入库关系集合
     */
    @NotEmpty(message = "付款与入库关系集合不能为空", groups = { AddGroup.class, EditGroup.class })
    private List<QlPaymentWarehousingRelBo> paymentWarehousingRels;
}
