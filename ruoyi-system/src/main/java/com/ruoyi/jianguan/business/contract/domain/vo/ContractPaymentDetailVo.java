package com.ruoyi.jianguan.business.contract.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.domain.NewBaseEntity;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 合同付款视图对象 zz_contract_payment
 *
 * @author mickey
 * @date 2023-06-07
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "合同付款", description = "合同付款VO")
public class ContractPaymentDetailVo extends NewBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 款项类型
     */
    @ExcelProperty(value = "款项类型")
    @ApiModelProperty(value = "款项类型", required = true)
    private String type;

    /**
     * 款项金额
     */
    @ExcelProperty(value = "款项金额")
    @ApiModelProperty(value = "款项金额", required = true)
    private BigDecimal amount;

    /**
     * 填报日期
     */
    @ExcelProperty(value = "填报日期")
    @ApiModelProperty(value = "填报日期", required = true)
    private Date recordTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 附件
     */
    @ExcelProperty(value = "附件")
    @ApiModelProperty(value = "附件", required = true)
    private List<FileModel> attachment;


    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    @ExcelProperty(value = "状态 0 审批中 1 审批完成 2 驳回")
    @ApiModelProperty(value = "状态 0 审批中 1 审批完成 2 驳回", required = true)
    private Integer status;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;
}