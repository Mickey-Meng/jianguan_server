package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 施工方案分页查询dto
 *
 * @author qiaoxulin
 * @since 2022-05-25
 */
@Data
@ApiModel(value = "BuildPlanPageDTO", description = "施工方案分页查询dto")
public class BuildPlanPageDTO extends PageDTO {

    /**
     * 专项施工方案名称
     */
    @ApiModelProperty(value = "专项施工方案名称")
    private String buildPlanName;
    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;

    /**
     * 监理标段名称
     */
    @ApiModelProperty(value = "监理标段名称")
    private String supervisionSectionName;
}
