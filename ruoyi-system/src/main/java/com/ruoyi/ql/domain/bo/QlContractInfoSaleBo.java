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
 * 合同管理业务对象 ql_contract_info_sale
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlContractInfoSaleBo extends BaseEntity {

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
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    /**
     * 客户id
     */
    @NotBlank(message = "客户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerId;

    /**
     * 总金额
     */
    @NotNull(message = "总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amount;

    /**
     * 质保金
     */
    @NotNull(message = "质保金不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal retentionAmount;

    /**
     * 合同签订时间
     */
    @NotNull(message = "合同签订时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date contactDate;

    /**
     * 质保金到期时间
     */
    private Date retentionDate;

    /**
     * 税率
     */
    private BigDecimal rate;

    /**
     * 发货地
     */
    @NotBlank(message = "发货地不能为空", groups = { AddGroup.class, EditGroup.class })
    private String area;

    /**
     * 1已签订 0未签订
     */
    @NotBlank(message = "1已签订 0未签订不能为空", groups = { AddGroup.class, EditGroup.class })
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


}
