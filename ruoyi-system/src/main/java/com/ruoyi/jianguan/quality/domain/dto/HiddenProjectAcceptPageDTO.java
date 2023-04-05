package com.ruoyi.jianguan.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 隐蔽工程验收记录分页查询dto
 *
 * @author qiaoxulin
 * @since 2022-05-12
 */
@Data
@ApiModel(value = "HiddenProjectAcceptPageDTO", description = "隐蔽工程验收记录分页查询dto")
public class HiddenProjectAcceptPageDTO extends PageDTO {

    /**
     * 工程编号
     */
    @ApiModelProperty(value = "工程编号")
    private String projectCode;
    /**
     * 分项工程
     */
    @ApiModelProperty(value = "分项工程")
    private String subProject;
}
