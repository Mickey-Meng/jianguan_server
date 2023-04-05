package com.ruoyi.jianguan.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 进退场分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/5/27 17:55
 */
@Data
@ApiModel(value = "EnterExitPageDTO", description = "进退场分页查询dto")
public class EnterExitPageDTO extends PageDTO {


    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;
}
