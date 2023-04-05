package com.ruoyi.flowable.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 流程类型详情返回VO
 *
 * @author qiaoxulin
 * @since 2022-05-18
 */
@Data
@ApiModel(value = "FlowTypeDetailVo", description = "流程类型详情返回VO")
public class FlowTypeDetailVo {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 流程定义key
     */
    @ApiModelProperty(value = "流程定义key")
    private String flowKey;


    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称")
    private String flowName;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;

    /**
     * 流程节点数目
     */
    @ApiModelProperty(value = "流程节点数目")
    private Integer count;
}
