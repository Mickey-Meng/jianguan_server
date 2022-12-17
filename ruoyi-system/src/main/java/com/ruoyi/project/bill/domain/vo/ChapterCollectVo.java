package com.ruoyi.project.bill.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "章节汇总", description = "章节汇总VO")
public class ChapterCollectVo {


    @ApiModelProperty(value = "合同单价", required = true)
    private String htdj;
    @ApiModelProperty(value = "修正金额", required = true)
    private String xzje;
    @ApiModelProperty(value = "审核金额", required = true)
    private String shje;
    @ApiModelProperty(value = "账目名称", required = true)
    private String zmmc;
    @ApiModelProperty(value = "账目号", required = true)
    private String zmh;
    @ApiModelProperty(value = "总金额", required = true)
    private String zje;
    @ApiModelProperty(value = "总数量", required = true)
    private String zsl;

}
