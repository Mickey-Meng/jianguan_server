package com.ruoyi.common.core.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页参数dto
 *
 * @author qiaoxulin
 * @date : 2022/5/13 14:51
 */
@Data
@ApiModel(value = "PageDTO", description = "分页参数dto")
public class PageDTO {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer pageNum;
    /**
     * 每页数据大小
     */
    @ApiModelProperty(value = "每页数据大小")
    private Integer pageSize;
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
     * 是否为草稿 0是 1不是
     */
    @ApiModelProperty(value = "是否为草稿 0是 默认1不是")
    private Integer draftFlag;
}
