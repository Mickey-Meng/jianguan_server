package com.ruoyi.jianguan.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 质量检测分页查询dto
 *
 * @author qiaoxulin
 * @since 2022-05-11
 */
@Data
@ApiModel(value = "QualityDetectionPageDTO", description = "质量检测分页查询dto")
public class QualityDetectionPageDTO extends PageDTO {


    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;
    /**
     * 材料名称
     */
    @ApiModelProperty(value = "材料名称")
    private String name;
    /**
     * 材料规格
     */
    @ApiModelProperty(value = "材料规格")
    private String specification;
    /**
     * 检测结果： 0合格 1不合格
     */
    @ApiModelProperty(value = "检测结果： 0合格 1不合格")
    private Integer detectionResult;
    /**
     * 创建开始时间
     */
    @ApiModelProperty(value = "创建开始时间")
    private Date createStartTime;
    /**
     * 创建结束时间
     */
    @ApiModelProperty(value = "创建结束时间")
    private Date createEndTime;

    /**
     * 是否为草稿 0是 1不是
     */
    @ApiModelProperty(value = "是否为草稿 0是 默认1不是")
    private Integer draftFlag;
}
