package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 质量活动分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/1 17:00
 */
@Data
@ApiModel(value = "QualityActivityPageVo", description = "质量活动分页列表返回VO")
public class QualityActivityPageVo extends NewBaseVo {

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
     * 施工单位
     */
    @ApiModelProperty(value = "施工单位")
    private Set<String> buildUnits;
    private String constructdpts;
    /**
     * 活动内容概述
     */
    @ApiModelProperty(value = "活动内容概述")
    private String activityInfo;


    /**
     * 状态 0 审批中 1 已审批
     */
    @ApiModelProperty(value = "状态 0 审批中 1 已审批")
    private Integer status;

    /**
     * 状态 0 审批中 1 已审批
     */
    @ApiModelProperty(value = "状态 0 审批中 1 已审批")
    private String statusStr;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createName;
}
