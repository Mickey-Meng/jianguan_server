package com.ruoyi.flowable.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 流程审批人员设置分页查询dto
 *
 * @author qiaoxulin
 * @since 2022-05-18
 */
@Data
@ApiModel(value = "FlowAuditEntryPageDTO", description = "流程审批人员设置分页查询dto")
public class FlowAuditEntryPageDTO extends PageDTO {
    /**
     * 流程定义key
     */
    @ApiModelProperty(value = "流程定义key")
    private String flowKey;


    /**
     * 流程节点key
     */
    @ApiModelProperty(value = "流程节点key")
    private String entryKey;


    /**
     * 流程节点名称
     */
    @ApiModelProperty(value = "流程节点名称")
    private String entryName;
}
