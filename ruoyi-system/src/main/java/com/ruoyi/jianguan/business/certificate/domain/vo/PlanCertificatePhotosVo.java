package com.ruoyi.jianguan.business.certificate.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.vo.NewBaseVo;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 计划管理-证照管理视图对象
 *
 * @author mickey
 * @date 2023-06-19
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "证照管理", description = "证照管理VO")
public class PlanCertificatePhotosVo extends NewBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 证照名称
     */
    @ExcelProperty(value = "证照名称")
    @ApiModelProperty(value = "证照名称", required = true)
    private String name;

    /**
     * 证照内容
     */
    @ExcelProperty(value = "证照内容")
    @ApiModelProperty(value = "证照内容", required = true)
    private String contents;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    @ApiModelProperty(value = "开始时间", required = true)
    private Date startTime;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    @ApiModelProperty(value = "结束时间", required = true)
    private Date endTime;

    /**
     * 上报时间
     */
    @ExcelProperty(value = "上报时间")
    @ApiModelProperty(value = "上报时间", required = true)
    private Date reportTime;

    /**
     * 上报人
     */
    @ExcelProperty(value = "上报人")
    @ApiModelProperty(value = "上报人", required = true)
    private String reportUser;

    /**
     * 责任人
     */
    @ExcelProperty(value = "责任人")
    @ApiModelProperty(value = "责任人", required = true)
    private String owner;

    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    @ExcelProperty(value = "状态 0 审批中 1 审批完成 2 驳回")
    @ApiModelProperty(value = "状态 0 审批中 1 审批完成 2 驳回", required = true)
    private Integer status;

    /**
     * 项目id
     */
    @ExcelProperty(value = "项目id")
    @ApiModelProperty(value = "项目id", required = true)
    private Long projectId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

}
