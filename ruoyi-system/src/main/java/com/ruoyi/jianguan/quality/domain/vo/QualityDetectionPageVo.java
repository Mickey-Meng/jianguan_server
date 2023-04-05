package com.ruoyi.jianguan.quality.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

/**
 * 质量检测分页列表返回VO
 *
 * @author qiaoxulin
 * @since 2022-05-11
 */
@Data
@ApiModel(value = "QualityDetectionPageVo", description = "质量检测分页列表返回VO")
public class QualityDetectionPageVo extends NewBaseVo {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
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
     * 检测信息
     */
    @ApiModelProperty(value = "检测信息")
    private String detectionInfo;
    /**
     * 材料名称
     */
    @ApiModelProperty(value = "材料名称")
    private Set<String> materialsName;
    /**
     * 填报日期
     */
    @ApiModelProperty(value = "填报日期")
    private LocalDate fillDate;
    /**
     * 材料规格
     */
    @ApiModelProperty(value = "材料规格")
    private Set<String> materialSpecification;
    /**
     * 工程部位
     */
    @ApiModelProperty(value = "工程部位")
    private Set<String> projectParts;
    /**
     * 检测结果
     */
    @ApiModelProperty(value = "检测结果")
    private Set<String> testResult;
}
