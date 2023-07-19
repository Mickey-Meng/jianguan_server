package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 首件认可分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/1 18:31
 */
@Data
@ApiModel(value = "FirstAcceptPageVo", description = "首件认可分页列表返回VO")
public class FirstAcceptPageVo extends NewBaseVo {
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
     * 首件工程名称
     */
    @ApiModelProperty(value = "首件工程名称")
    private String firstProjectName;


    /**
     * 分部分项id
     */
    @ApiModelProperty(value = "分部分项id")
    private Integer subProject;

    /**
     * 单位工程
     */
    @ApiModelProperty(value = "单位工程")
    private String unitProjectName;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createName;

    /**
     * 具体分项
     */
    @ApiModelProperty(value = "具体分项")
    private String subProjectDetail;

    /**
     * 首件工程通过情况
     */
    @ApiModelProperty(value = "首件工程通过情况")
    private String firstPassExplain;

    /**
     * 实施日期
     */
    @ApiModelProperty(value = "实施日期")
    private LocalDate buildDate;

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
}
