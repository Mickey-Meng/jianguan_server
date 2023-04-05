package com.ruoyi.jianguan.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 监理巡视分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/6/10 17:23
 */
@Data
@ApiModel(value = "SupervisionPatrolPageDTO", description = "监理巡视分页查询dto")
public class SupervisionPatrolPageDTO extends PageDTO {

    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;
    /**
     * 发起时间
     */
    @ApiModelProperty(value = "发起开始时间")
    private String startDateStart;

    /**
     * 发起时间
     */
    @ApiModelProperty(value = "发起结束时间")
    private String startDateEnd;
    /**
     * 巡视地点
     */
    @ApiModelProperty(value = "巡视地点")
    private String patrolPlace;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createName;
}
