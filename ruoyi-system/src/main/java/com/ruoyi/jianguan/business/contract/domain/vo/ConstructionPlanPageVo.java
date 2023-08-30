package com.ruoyi.jianguan.business.contract.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@ExcelIgnoreUnannotated
public class ConstructionPlanPageVo extends NewBaseVo {


    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;
    /**
     * 内容
     */
    private String content;
    private String name;

    /**
     * 上报人
     */
    private String reportPeople;

    /**
     * 上报时间
     */
    private Date reportTime;
    private String responsiblePerson;
    private String responsiblePersonId;

    private Date plainStartTime;
    private Date plainEndTime;

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
    //上报工作流状态 0 审批中 1 审批完成 2 驳回
    private Integer reportStatus;

}