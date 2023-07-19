package com.ruoyi.jianguan.business.quality.domain.vo;

import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 项目简报详情返回VO
 *
 * @author qiaoxulin
 * @date : 2022/6/2 10:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "QualityReportDetailVo", description = "项目简报详情返回VO")
public class QualityReportDetailVo extends NewBaseEntity {

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
    private List<FileModel> reportAttachment;

    /**
     * 整改内容附件
     */
    @ApiModelProperty(value = "整改内容附件")
    private List<FileModel> replyPhotoAttachment;

    /**
     * 状态 0 审批中 1 已审批
     */
    @ApiModelProperty(value = "状态 0 审批中 1 已审批")
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
