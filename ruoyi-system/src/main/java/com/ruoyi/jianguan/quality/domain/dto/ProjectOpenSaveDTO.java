package com.ruoyi.jianguan.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 项目申请开工保存dto
 *
 * @author qiaoxulin
 * @date : 2022/6/1 14:54
 */
@Data
@ApiModel(value = "ProjectOpenSaveDTO", description = "项目申请开工保存dto")
public class ProjectOpenSaveDTO extends SaveDTO {

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
     * 标段id
     */
    @ApiModelProperty(value = "标段id")
    private Integer buildSection;

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
     * 施工组织申请说明
     */
    @ApiModelProperty(value = "施工组织申请说明")
    private String buildApplyExplain;


    /**
     * 工程划分申请说明
     */
    @ApiModelProperty(value = "工程划分申请说明")
    private String projectApplyExplain;


    /**
     * 技术安全质量申请说明
     */
    @ApiModelProperty(value = "技术安全质量申请说明")
    private String techApplyExplain;


    /**
     * 试验检测工作申请说明
     */
    @ApiModelProperty(value = "试验检测工作申请说明")
    private String checkApplyExplain;


    /**
     * 设计交底申请说明
     */
    @ApiModelProperty(value = "设计交底申请说明")
    private String designApplyExplain;


    /**
     * 原始基准点、地面线复测情况申请说明
     */
    @ApiModelProperty(value = "原始基准点、地面线复测情况申请说明")
    private String againApplyExplain;


    /**
     * 开工预付款担保及保险情况申请说明
     */
    @ApiModelProperty(value = "开工预付款担保及保险情况申请说明")
    private String insuranceApplyExplain;


    /**
     * 合同段施工安全风险评估情况申请说明
     */
    @ApiModelProperty(value = "合同段施工安全风险评估情况申请说明")
    private String securityApplyExplain;


    /**
     * 其他有关情况申请说明
     */
    @ApiModelProperty(value = "其他有关情况申请说明")
    private String otherApplyExplain;


    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private List<FileModel> attachment;


    /**
     * 是否为草稿 0是 1不是
     */
    @ApiModelProperty(value = "是否为草稿 0是 1不是")
    private Integer draftFlag;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;
}
