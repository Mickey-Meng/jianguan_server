package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 质量活动分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/6/1 14:54
 */
@Data
@ApiModel(value = "QualityActivityPageDTO", description = "质量活动分页查询dto")
public class QualityActivityPageDTO extends PageDTO {

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private Integer buildSectionName;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private Integer createUserName;
    /**
     * 创建开始时间
     */
    @ApiModelProperty(value = "创建开始时间")
    private String createStartTime;

    /**
     * 创建结束时间
     */
    @ApiModelProperty(value = "创建结束时间")
    private String createEndTime;

}
