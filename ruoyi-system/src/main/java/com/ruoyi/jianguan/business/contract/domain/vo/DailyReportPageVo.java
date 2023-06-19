package com.ruoyi.jianguan.business.contract.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "ContractPaymentPageVo", description = "合同付款VO")
public class DailyReportPageVo extends NewBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;
    /**
     * 晨检内容
     */
    private String content;
    /**
     * 上报人
     */
    private String reportPeople;
    /**
     * 上报时间
     */
    private Date reportTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件", required = true)
    private String attachment;

    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    @ApiModelProperty(value = "状态 0 审批中 1 审批完成 2 驳回", required = true)
    private Integer status;


}