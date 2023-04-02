package com.ruoyi.flowable.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lpeng
 * @Date: 2022-05-05 14:05
 * @Description:
 */
@ApiModel("FlowTaskHandleVo对象")
@Data
public class FlowTaskHandleVo {

    /**
     * 主键Id。
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 流程实例Id。
     */
    @ApiModelProperty(value = "流程实例Id")
    private String processInstanceId;

    /**
     * 流程定义Id。
     */
    private String processDefinitionId;

    /**
     * 任务Id。
     */
    @ApiModelProperty(value = "任务Id")
    private String taskId;

    /**
     * 任务标识。
     */
    @ApiModelProperty(value = "任务标识")
    private String taskKey;

    /**
     * 任务名称。
     */
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    /**
     * 处理状态。
     */
    @ApiModelProperty(value = "处理状态")
    private Integer taskHandleStatus;
    /**
     * 处理状态描述。
     */
    @ApiModelProperty(value = "处理状态描述")
    private String taskHandleStatusStr;
    /**
     * 处理人id。
     */
    @ApiModelProperty(value = "处理人id")
    private String taskHandleUserId;
    /**
     * 处理人登录名。
     */
    @ApiModelProperty(value = "处理人登录名")
    private String taskHandleUserLoginName;
    /**
     * 处理人用户名。
     */
    @ApiModelProperty(value = "处理人用户名")
    private String taskHandleUserName;

    /**
     * 创建者Id。
     */
    @ApiModelProperty(value = "创建者Id")
    private Long createUserId;

    /**
     * 创建者登录名。
     */
    @ApiModelProperty(value = "创建者登录名")
    private String createLoginName;

    /**
     * 创建者显示名。
     */
    @ApiModelProperty(value = "创建者显示名")
    private String createUsername;

    /**
     * 创建时间。
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
