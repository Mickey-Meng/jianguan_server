package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 监理指令分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/6/15 9:44
 */
@Data
@ApiModel(value = "SupervisionOrderPageDTO", description = "监理指令分页查询dto")
public class SupervisionOrderPageDTO extends PageDTO {


    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人名称")
    private String createUserName;

    /**
     * 创建开始时间
     */
    @ApiModelProperty(value = "创建开始时间")
    private String createStartTime;

    /**
     * 创建开始时间
     */
    @ApiModelProperty(value = "创建结束时间")
    private String createEndTime;

}
