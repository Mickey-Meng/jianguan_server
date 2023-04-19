package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 项目开工申请分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/6/1 15:08
 */
@Data
@ApiModel(value = "ProjectOpenPageDTO", description = "项目开工申请分页查询dto")
public class ProjectOpenPageDTO extends PageDTO {

    /**
     * 计划开工开始日期
     */
    @ApiModelProperty(value = "开工开始日期")
    private String openDateStart;

    /**
     * 计划开工结束日期
     */
    @ApiModelProperty(value = "计划开工结束日期")
    private String openDateEnd;

    /**
     * 计划完工开始日期
     */
    @ApiModelProperty(value = "计划完工开始日期")
    private String endDateStart;

    /**
     * 计划完工结束日期
     */
    @ApiModelProperty(value = "计划完工结束日期")
    private String endDateEnd;

}
