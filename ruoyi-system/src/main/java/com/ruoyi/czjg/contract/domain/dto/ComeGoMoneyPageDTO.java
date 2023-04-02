package com.ruoyi.czjg.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 往来款分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/5/27 17:55
 */
@Data
@ApiModel(value = "ComeGoMoneyPageDTO", description = "往来款分页查询dto")
public class ComeGoMoneyPageDTO extends PageDTO {
    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;

    /**
     * 账单编号
     */
    @ApiModelProperty(value = "账单编号")
    private String billCode;

}
