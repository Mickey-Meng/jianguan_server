package com.ruoyi.jianguan.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * 施工技术交底列表返回VO
 * @author qiaoxulin
 * @date : 2022/5/26 16:56
 */
@Data
@ApiModel(value = "BuildTechBottomPageVo", description = "施工技术交底列表返回VO")
public class BuildTechBottomPageVo extends NewBaseVo {



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
     * 施工标段
     */
    @ApiModelProperty(value = "施工标段")
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

    /**
     * 登记时间
     */
    @ApiModelProperty(value = "登记时间")
    private LocalDate checkDate;


    /**
     * 施工技术交底概述
     */
    @ApiModelProperty(value = "施工技术交底概述")
    private String buildTechBottom;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createName;


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
