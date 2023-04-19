package com.ruoyi.jianguan.business.quality.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 质量简报
 *
 * @author qiaoxulin
 * @since 2022-06-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_quality_report")
@ApiModel(value = "QualityReport对象", description = "质量简报")
public class QualityReport extends NewBaseEntity {

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
     * 简报名称
     */
    @ApiModelProperty(value = "简报名称")
    private String title;

    /**
     * 简报附件
     */
    @ApiModelProperty(value = "简报附件")
    private String reportAttachment;

    /**
     * 整改内容附件
     */
    @ApiModelProperty(value = "整改内容附件")
    private String replyPhotoAttachment;

    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private Integer status;


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
