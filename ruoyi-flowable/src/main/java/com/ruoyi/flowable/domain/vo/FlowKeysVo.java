package com.ruoyi.flowable.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 流程key返回vo
 * @author Administrator
 */
@Data
@ApiModel(description = "流程key返回vo", value = "FlowKeysVo")
public class FlowKeysVo {
    /**
     * 流程任务Id。
     */
    @ApiModelProperty(value = "流程任务Id")
    private String taskId;

    /**
     * 流程任务名称。
     */
    @ApiModelProperty(value = "流程任务名称")
    private String taskName;

    /**
     * 流程任务标识。
     */
    @ApiModelProperty(value = "流程任务标识")
    private String taskKey;
    /**
     * 流程定义Id。
     */
    @ApiModelProperty(value = "流程定义Id")
    private String processDefinitionId;

    /**
     * 流程定义名称。
     */
    @ApiModelProperty(value = "流程定义名称")
    private String processDefinitionName;

    /**
     * 流程定义标识。
     */
    @ApiModelProperty(value = "流程定义标识")
    private String processDefinitionKey;


    /**
     * 流程实例Id。
     */
    @ApiModelProperty(value = "流程实例Id")
    private String processInstanceId;
}
