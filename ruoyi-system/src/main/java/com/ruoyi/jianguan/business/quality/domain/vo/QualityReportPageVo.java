package com.ruoyi.jianguan.business.quality.domain.vo;


import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 质量简报分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/2 10:51
 */
@Data
@ApiModel(value = "QualityReportPageVo", description = "质量简报分页列表返回VO")
public class QualityReportPageVo extends NewBaseVo {
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
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;

    /**
     * 简报名称
     */
    @ApiModelProperty(value = "简报名称")
    private String title;

    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private Integer status;

    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private String statusStr;
}
