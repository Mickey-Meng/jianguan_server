package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.jianguan.business.quality.domain.dto.QualityDetectionSaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

/**
 * 质量检测详情返回VO
 *
 * @author qiaoxulin
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "QualityDetectionDetailVo", description = "质量检测详情返回VO")
public class QualityDetectionDetailVo extends NewBaseEntity {
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
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    /**
     * 报验单编号
     */
    @ApiModelProperty(value = "报验单编号")
    private String inspectionCode;


    /**
     * 填报日期
     */
    @ApiModelProperty(value = "填报日期")
    private LocalDate fillDate;


    /**
     * 检测信息
     */
    @ApiModelProperty(value = "检测信息")
    private List<QualityDetectionSaveDTO.DetectionInfo> detectionInfo;


    /**
     * 试验检测报告
     */
    @ApiModelProperty(value = "试验检测报告")
    private List<FileModel> detectionReport;

    /**
     * 出厂信息
     */
    @ApiModelProperty(value = "出厂信息")
    private List<FileModel> factoryInfo;

    /**
     * 其他附件
     */
    @ApiModelProperty(value = "其他附件")
    private List<FileModel> otherAttachment;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人")
    private Integer detectionUser;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag = 1;

    /**
     * 是否为草稿 0是 1不是
     */
    @ApiModelProperty(value = "是否为草稿 0是 默认1不是")
    private Integer draftFlag;

    /**
     * 状态 0 审批中 1已审批
     */
    @ApiModelProperty(value = "状态 0 审批中 1已审批")
    private Integer status;
}
