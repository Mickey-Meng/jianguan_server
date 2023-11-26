package com.ruoyi.ql.domain.export.query;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseReportQueryEntity extends BaseEntity {

    /**
     * 导入全部： 1：导出全部，0：导出当前页
     */
    private String exportAll = "1";

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
}
