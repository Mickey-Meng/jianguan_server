package com.ruoyi.jianguan.quality.domain.dto;

import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 管理目标保存dto
 * @author qiaoxulin
 * @date : 2022/5/13 14:35
 */
@Data
@ApiModel(value = "ManageTargetSaveDTO", description = "管理目标保存dto")
public class ManageTargetSaveDTO {
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
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private LocalDate publishDate;


    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;


    /**
     * 文件
     */
    @ApiModelProperty(value = "文件")
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
