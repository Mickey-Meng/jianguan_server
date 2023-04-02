package com.ruoyi.flowable.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 下一任务
 */
@Data
public class FlowNextTaskVo {

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "指定执行人")
    private String assignee;

    @ApiModelProperty(value = "表单参数")
    private String formKey;

    @ApiModelProperty(value = "任务定义Key")
    private String taskDefKey;

    @ApiModelProperty(value = "流程启动者")
    private String startUserId;
}
