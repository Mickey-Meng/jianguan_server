package com.ruoyi.jianguan.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 劳务分包合同分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/5/20 15:19
 */
@Data
@ApiModel(value = "LaborContractPageDTO", description = "劳务分包合同分页查询dto")
public class LaborContractPageDTO extends PageDTO {

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;
    /**
     * 拟劳务合作工程名称
     */
    @ApiModelProperty(value = "拟劳务合作工程名称")
    private String laborContractProjectName;

}
