package com.ruoyi.jianguan.business.contract.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 进退场列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/5/27 18:00
 */
@Data
@ApiModel(value = "EnterExitPageVo", description = "进退场列表返回VO")
public class EnterExitPageVo extends NewBaseVo {


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
    private String constructDept;// yangaogao 20230512 这个是施工单位

    /**
     * 报审类型 0 进场 1退场
     */
    @ApiModelProperty(value = "报审类型 0 进场 1退场")
    private Integer type;

    /**
     * 报审类型 0 进场 1退场
     */
    @ApiModelProperty(value = "报审类型 0 进场 1退场")
    private String typeStr;


    /**
     * 人数
     */
    @ApiModelProperty(value = "人数")
    private Integer num;


    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String explaination;


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
