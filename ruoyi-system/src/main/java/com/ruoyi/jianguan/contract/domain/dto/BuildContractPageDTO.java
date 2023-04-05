package com.ruoyi.jianguan.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 施工专业分包合同分页查询dto
 *
 * @author qiaoxulin
 * @since 2022-05-18
 */
@Data
@ApiModel(value = "BuildContractPageDTO", description = "施工专业分包合同分页查询dto")
public class BuildContractPageDTO extends PageDTO {

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;
    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractCode;
}
