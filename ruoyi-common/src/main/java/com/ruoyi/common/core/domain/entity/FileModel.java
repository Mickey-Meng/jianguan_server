package com.ruoyi.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件传输对象
 *
 * @author qiaoxulin
 * @since 2022-05-11
 */
@Data
@ApiModel(value = "FileModel", description = "文件传输对象")
public class FileModel {


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String uploadTime;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    /**
     * fileId
     */
    @ApiModelProperty(value = "fileId")
    private String fileId;
    /**
     * 上传人
     */
    @ApiModelProperty(value = "上传人")
    private String creatorName;
}
