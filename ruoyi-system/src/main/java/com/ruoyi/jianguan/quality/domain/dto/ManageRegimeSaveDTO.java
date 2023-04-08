package com.ruoyi.jianguan.quality.domain.dto;

import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 管理制度保存dto
 *
 * @author qiaoxulin
 * @date : 2022/5/13 14:35
 */
@Data
@ApiModel(value = "ManageRegimeSaveDTO", description = "管理制度保存dto")
public class ManageRegimeSaveDTO {

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
     * 编制人
     */
    @ApiModelProperty(value = "编制人")
    private String compileUser;


    /**
     * 编制日期
     */
    @ApiModelProperty(value = "编制日期")
    private LocalDate compileDate;


    /**
     * 制度内容
     */
    @ApiModelProperty(value = "制度内容")
    private String regimeContent;


    /**
     * 制度文件
     */
    @ApiModelProperty(value = "制度文件")
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