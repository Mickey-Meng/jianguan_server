package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 施工技术交底保存dto
 *
 * @author qiaoxulin
 * @date : 2022/5/26 16:34
 */
@Data
@ApiModel(value = "BuildPlanSaveDTO", description = "施工技术交底保存dto")
public class BuildTechBottomSaveDTO extends SaveDTO {

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
     * 施工标段
     */
    @ApiModelProperty(value = "施工标段")
    private Integer buildSection;


    /**
     * 登记时间
     */
    @ApiModelProperty(value = "登记时间")
    private LocalDate checkDate;


    /**
     * 施工技术交底概述
     */
    @ApiModelProperty(value = "施工技术交底概述")
    private String buildTechBottom;


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