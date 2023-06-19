package com.ruoyi.jianguan.business.contract.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.domain.NewBaseEntity;
import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;



@Data
@ExcelIgnoreUnannotated
public class DailyReportDetailVo extends NewBaseEntity {


    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    private Long id;


    /**
     * 晨检内容
     */
    private String content;

    /**
     * 上报人
     */
    private String reportPeople;

    /**
     * 上报时间
     */
    private Date reportTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 附件
     */
    @ExcelProperty(value = "附件")
    @ApiModelProperty(value = "附件", required = true)
    private List<FileModel> attachment;


    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    @ExcelProperty(value = "状态 0 审批中 1 审批完成 2 驳回")
    @ApiModelProperty(value = "状态 0 审批中 1 审批完成 2 驳回", required = true)
    private Integer status;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;
}