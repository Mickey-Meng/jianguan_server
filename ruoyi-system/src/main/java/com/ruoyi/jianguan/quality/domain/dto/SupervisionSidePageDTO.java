package com.ruoyi.jianguan.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 监理旁站分页查询dto
 * @author qiaoxulin
 * @date : 2022/6/10 16:26
 */
@Data
@ApiModel(value = "SupervisionSidePageDTO", description = "监理旁站分页查询dto")
public class SupervisionSidePageDTO extends PageDTO {
    /**
     * 旁站开始时间
     */
    @ApiModelProperty(value = "旁站开始时间")
    private String sideDateStart;
    /**
     * 旁站结束时间
     */
    @ApiModelProperty(value = "旁站结束时间")
    private String sideDateEnd;

    /**
     * 旁站人
     */
    @ApiModelProperty(value = "旁站人")
    private String createName;

    /**
     * 工程部位描述
     */
    @ApiModelProperty(value = "工程部位描述")
    private String projectPartDesc;

    /**
     * 旁站监理项目id
     */
    @ApiModelProperty(value = "旁站监理项目id")
    private Integer sideProjectId;
}
