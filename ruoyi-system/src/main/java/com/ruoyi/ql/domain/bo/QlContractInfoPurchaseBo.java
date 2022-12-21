package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.ql.domain.vo.QlFinReimbursementItemVo;
import com.ruoyi.ql.domain.vo.QlWarehousingVo;
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
 * 采购合同 业务对象 ql_contract_info_purchase
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlContractInfoPurchaseBo extends BaseEntity {

    /**
     * 合同id
     */
    @NotNull(message = "合同id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 合同编码
     */
    @NotBlank(message = "合同编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractCode;

    /**
     * 合同名称
     */
    @NotBlank(message = "合同名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractName;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;

    /**
     * 供应商id
     */
    private String supplierId;

    /**
     * 总金额
     */
    @NotNull(message = "总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amount;

    /**
     * 合同签订时间
     */
    private Date contactDate;

    /**
     * 税率
     */
    private BigDecimal rate;

    /**
     * 1已签订 0未签订
     */
    private String contractStatus;

    /**
     * 附件
     */
    private String fj;

    /**
     *
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;
    private List<QlWarehousingVo> qlWarehousingVos;

}
