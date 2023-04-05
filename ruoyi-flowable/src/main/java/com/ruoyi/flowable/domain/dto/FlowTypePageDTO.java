package com.ruoyi.flowable.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 流程类型分页查询dto
 *
 * @author qiaoxulin
 * @since 2022-05-18
 */
@Data
@ApiModel(value = "FlowTypePageDTO", description = "流程类型分页查询dto")
public class FlowTypePageDTO extends PageDTO {

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
}
