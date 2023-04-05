package com.ruoyi.jianguan.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分项开工申请分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/6/1 16:58
 */
@Data
@ApiModel(value = "ProjectOpenPageDTO", description = "分项开工申请分页查询dto")
public class SubitemOpenPageDTO extends PageDTO {

    /**
     * 地点或桩号
     */
    @ApiModelProperty(value = "地点或桩号")
    private String place;

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;
}
