package com.ruoyi.jianguan.quality.domain.vo;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 监理旁站分页列表返回VO
 * @author qiaoxulin
 * @date : 2022/6/10 16:31
 */
@Data
@ApiModel(value = "SupervisionSidePageVo", description = "监理旁站分页列表返回VO")
public class SupervisionSidePageVo extends NewBaseVo {
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
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private JSONObject address;


    /**
     * 旁站时间
     */
    @ApiModelProperty(value = "旁站时间")
    private LocalDate sideDate;

    /**
     * 旁站信息记录
     */
    @ApiModelProperty(value = "旁站信息记录")
    private String sideInfo;


    /**
     * 工程部位id
     */
    @ApiModelProperty(value = "工程部位id")
    private Integer projectPartId;

    /**
     * 工程部位名称
     */
    @ApiModelProperty(value = "工程部位名称")
    private String projectPartName;


    /**
     * 旁站监理项目id
     */
    @ApiModelProperty(value = "旁站监理项目id")
    private Integer sideProjectId;

    /**
     * 分项工程
     */
    @ApiModelProperty(value = "分项工程")
    private String subProject;

    /**
     * 工程部位名称
     */
    @ApiModelProperty(value = "工程部位名称")
    private String projectPartStr;

    /**
     * 工程部位描述
     */
    @ApiModelProperty(value = "工程部位描述")
    private String projectPartDesc;

    /**
     * 旁站监理项目名称
     */
    @ApiModelProperty(value = "旁站监理项目名称")
    private String sideProjectName;

    /**
     * 状态 0 进行中 1已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1已完成")
    private Integer status;

    /**
     * 状态 0 进行中 1已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1已完成")
    private String statusStr;

    /**
     * 旁站人
     */
    @ApiModelProperty(value = "旁站人")
    private String createName;
}
