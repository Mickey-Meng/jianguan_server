package com.ruoyi.jianguan.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 监理指令分页返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/15 9:53
 */
@Data
@ApiModel(value = "SupervisionOrderPageVo", description = "监理指令分页返回VO")
public class SupervisionOrderPageVo extends NewBaseVo {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 指令编号
     */
    @ApiModelProperty(value = "指令编号")
    private String orderCode;

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
     * 创建人id
     */
    @ApiModelProperty(value = "创建人名称")
    private String createUserName;

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
     * 指令标题
     */
    @ApiModelProperty(value = "指令标题")
    private String orderTitle;

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

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
