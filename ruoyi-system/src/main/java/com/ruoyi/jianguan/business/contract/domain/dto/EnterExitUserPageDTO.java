package com.ruoyi.jianguan.business.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 进退场人员分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/5/27 17:55
 */
@Data
@ApiModel(value = "EnterExitUserPageDTO", description = "进退场人员分页查询dto")
public class EnterExitUserPageDTO extends PageDTO {

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 报审类型 0 进场 1退场
     */
    @ApiModelProperty(value = "报审类型 0 进场 1退场")
    private Integer type;

}
