package com.ruoyi.jianguan.quality.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 质量检测
 *
 * @author qiaoxulin
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_quality_detection")
@ApiModel(value = "QualityDetection对象", description = "质量检测")
public class QualityDetection extends NewBaseEntity {

    private static final long serialVersionUID = 1L;


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
    private String detectionInfo;


    /**
     * 试验检测报告
     */
    @ApiModelProperty(value = "试验检测报告")
    private String detectionReport;

    /**
     * 出厂信息
     */
    @ApiModelProperty(value = "出厂信息")
    private String factoryInfo;

    /**
     * 其他附件
     */
    @ApiModelProperty(value = "其他附件")
    private String otherAttachment;


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
    private Integer deletedFlag;

    /**
     * 是否为草稿 0是 1不是
     */
    @ApiModelProperty(value = "是否为草稿 0是 默认1不是")
    private Integer draftFlag;

    /**
     * 状态 0 进行中 1已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1已完成")
    private Integer status;

}
