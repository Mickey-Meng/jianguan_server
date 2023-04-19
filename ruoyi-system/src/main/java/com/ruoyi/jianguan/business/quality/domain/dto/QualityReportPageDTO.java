package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 质量简报分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/6/2 10:30
 */
@Data
@ApiModel(value = "QualityReportPageDTO", description = "质量简报分页查询dto")
public class QualityReportPageDTO extends PageDTO {
    /**
     * 简报名称
     */
    @ApiModelProperty(value = "简报名称")
    private String title;

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;
}
