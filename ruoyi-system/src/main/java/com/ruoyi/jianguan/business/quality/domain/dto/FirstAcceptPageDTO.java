package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 首件认可分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/6/1 18:28
 */
@Data
@ApiModel(value = "FirstAcceptPageDTO", description = "首件认可分页查询dto")
public class FirstAcceptPageDTO extends PageDTO {
    /**
     * 首件工程名称
     */
    @ApiModelProperty(value = "首件工程名称")
    private String firstProjectName;

    /**
     * 具体分项
     */
    @ApiModelProperty(value = "具体分项")
    private String subProjectDetail;


    /**
     * 实施开始日期
     */
    @ApiModelProperty(value = "实施开始日期")
    private String buildDateStart;

    /**
     * 实施结束日期
     */
    @ApiModelProperty(value = "实施结束日期")
    private String buildDateEnd;
}
