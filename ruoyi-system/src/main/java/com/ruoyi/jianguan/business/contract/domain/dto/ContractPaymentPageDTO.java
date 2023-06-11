package com.ruoyi.jianguan.business.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 合同付款dto
 *
 * @author G.X.L
 * @date : 2023/03/29
 */
@Data
@ApiModel(value = "ContractPaymentPageDTO", description = "合同付款查询dto")
public class ContractPaymentPageDTO extends PageDTO {

    /**
     * 填报日期
     */
    private Date recordTime;

    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
}
