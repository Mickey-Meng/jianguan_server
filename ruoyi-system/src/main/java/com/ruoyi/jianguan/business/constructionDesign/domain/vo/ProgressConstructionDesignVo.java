package com.ruoyi.jianguan.business.constructionDesign.domain.vo;

import lombok.Data;
import java.util.Date;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 进度管理-证照管理视图对象
 *
 * @author mickey
 * @date 2023-06-19
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "证照管理", description = "证照管理VO")
public class ProgressConstructionDesignVo extends PlanConstructionDesignVo {

    private static final long serialVersionUID = 1L;

    /**
     * 附件
     */
    @ExcelProperty(value = "附件")
    @ApiModelProperty(value = "附件", required = true)
    private String attachment;

}

