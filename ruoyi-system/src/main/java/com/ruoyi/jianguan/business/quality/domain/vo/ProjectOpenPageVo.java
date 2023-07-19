package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * 项目开工申请分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/1 15:15
 */
@Data
@ApiModel(value = "ProjectOpenPageVo", description = "项目开工申请分页列表返回VO")
public class ProjectOpenPageVo extends NewBaseVo {


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
    private Set<String> buildSectionNames;


    /**
     * 开工日期
     */
    @ApiModelProperty(value = "开工日期")
    private LocalDate openDate;


    /**
     * 计划完工日期
     */
    @ApiModelProperty(value = "计划完工日期")
    private LocalDate endDate;


    /**
     * 合同规定工期起
     */
    @ApiModelProperty(value = "合同规定工期起")
    private LocalDate contractOpenDate;


    /**
     * 合同规定工期止
     */
    @ApiModelProperty(value = "合同规定工期止")
    private LocalDate contractEndDate;

    /**
     * 历时天数
     */
    @ApiModelProperty(value = "历时天数")
    private Integer days;

    /**
     * 状态 0 审批中 1已审批
     */
    @ApiModelProperty(value = "状态 0 审批中 1已审批")
    private Integer status;

    /**
     * 状态 0 审批中 1已审批
     */
    @ApiModelProperty(value = "状态 0 审批中 1已审批")
    private String statusStr;

}
