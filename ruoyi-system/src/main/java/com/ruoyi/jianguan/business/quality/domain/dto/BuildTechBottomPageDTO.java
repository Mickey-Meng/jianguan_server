package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 施工技术交底分页查询dto
 * @author qiaoxulin
 * @date : 2022/5/26 16:49
 */
@Data
@ApiModel(value = "BuildContractPageDTO", description = "施工技术交底分页查询dto")
public class BuildTechBottomPageDTO extends PageDTO {

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createName;

    /**
     * 登记开始时间
     */
    @ApiModelProperty(value = "登记开始时间")
    private String startCheckDate;

    /**
     * 登记结束时间
     */
    @ApiModelProperty(value = "登记结束时间")
    private String endCheckDate;
}
