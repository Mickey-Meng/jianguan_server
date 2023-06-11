package com.ruoyi.jianguan.business.contract.domain.dto;


import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 合同付款业务对象 zz_contract_payment
 *
 * @author mickey
 * @date 2023-06-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ContractPaymentSaveDTO extends SaveDTO {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 款项类型
     */
    @NotBlank(message = "款项类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String type;

    /**
     * 款项金额
     */
    @NotNull(message = "款项金额不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal amount;

    /**
     * 填报日期
     */
    @NotNull(message = "填报日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date recordTime;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String remark;

    /**
     * 附件
     */
    @NotBlank(message = "附件不能为空", groups = {AddGroup.class, EditGroup.class})
    private List<FileModel> attachment;

    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    @NotNull(message = "状态 0 审批中 1 审批完成 2 驳回不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer status;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;


}