package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * 分项开工申请分页列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/1 17:00
 */
@Data
@ApiModel(value = "ProjectOpenPageVo", description = "分项开工申请分页列表返回VO")
public class SubitemOpenPageVo extends NewBaseVo {

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
     * 施工单位
     */
    @ApiModelProperty(value = "施工单位")
    private Set<String> buildUnits;
    private String constructdpts;

    /**
     * 工程编号
     */
    @ApiModelProperty(value = "工程编号")
    private String projectCode;


    /**
     * 建议开工日期
     */
    @ApiModelProperty(value = "建议开工日期")
    private LocalDate openDate;


    /**
     * 计划完工日期
     */
    @ApiModelProperty(value = "计划完工日期")
    private LocalDate endDate;


    /**
     * 地点或桩号
     */
    @ApiModelProperty(value = "地点或桩号")
    private String place;


    /**
     * 现场负责人
     */
    @ApiModelProperty(value = "现场负责人")
    private Integer liveUser;

    /**
     * 现场负责人
     */
    @ApiModelProperty(value = "现场负责人")
    private String liveUserName;


    /**
     * 施工员
     */
    @ApiModelProperty(value = "施工员")
    private Integer buildUser;

    /**
     * 施工员
     */
    @ApiModelProperty(value = "施工员")
    private String buildUserName;


    /**
     * 质检员
     */
    @ApiModelProperty(value = "质检员")
    private Integer checkUser;

    /**
     * 质检员
     */
    @ApiModelProperty(value = "质检员")
    private String checkUserName;

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
