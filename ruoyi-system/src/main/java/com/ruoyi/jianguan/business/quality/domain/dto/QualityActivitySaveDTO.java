package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 质量活动保存dto
 *
 * @author qiaoxulin
 * @date : 2022/6/1 14:54
 */
@Data
@ApiModel(value = "QualityActivitySaveDTO", description = "质量活动保存dto")
public class QualityActivitySaveDTO extends SaveDTO {

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
     * 活动内容概述
     */
    @ApiModelProperty(value = "活动内容概述")
    private String activityInfo;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private List<FileModel> attachment;


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
