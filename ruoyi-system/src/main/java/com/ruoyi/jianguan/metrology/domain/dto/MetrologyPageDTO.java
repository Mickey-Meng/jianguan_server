package com.ruoyi.jianguan.metrology.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 计量分页查询dto
 *
 * @author G.X.L
 * @date : 2023/03/29
 */
@Data
@ApiModel(value = "MetrologyPageDTO", description = "计量分页查询dto")
public class MetrologyPageDTO extends PageDTO {

    /**
     * 计量编号
     */
    @ApiModelProperty(value = "计量编号")
    private String metrologyNo;


    /**
     * 申请单位
     */
    @ApiModelProperty(value = "申请单位")
    private String applyUnit;

}
