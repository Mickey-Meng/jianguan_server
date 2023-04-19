package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 施工方案分页列表返回VO
 *
 * @author qiaoxulin
 * @since 2022-05-18
 */
@Data
@ApiModel(value = "BuildPlanPageVo", description = "施工方案分页列表返回VO")
public class BuildPlanPageVo extends NewBaseVo {


    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;
    /**
     * 监理标段名称
     */
    @ApiModelProperty(value = "监理标段名称")
    private String supervisionSectionName;
    /**
     * 合同号
     */
    @ApiModelProperty(value = "合同号")
    private String contractCode;

    /**
     * 工程编号
     */
    @ApiModelProperty(value = "工程编号")
    private String projectCode;


    /**
     * 专项施工方案名称
     */
    @ApiModelProperty(value = "专项施工方案名称")
    private String buildPlanName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String statusStr;
}
