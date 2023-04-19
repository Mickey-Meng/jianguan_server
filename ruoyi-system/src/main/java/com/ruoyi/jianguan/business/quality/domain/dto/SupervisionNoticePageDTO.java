package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 监理通知分页查询dto
 *
 * @author qiaoxulin
 * @date : 2022/6/1 16:58
 */
@Data
@ApiModel(value = "SupervisionNoticePageDTO", description = "监理通知分页查询dto")
public class SupervisionNoticePageDTO extends PageDTO {
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 创建开始时间
     */
    @ApiModelProperty(value = "创建开始时间")
    private String createStartTime;
    /**
     * 创建结束时间
     */
    @ApiModelProperty(value = "创建结束时间")
    private String createEndTime;
}
