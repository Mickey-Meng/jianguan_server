package com.ruoyi.jianguan.business.certificate.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
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
public class ProgressCertificatePhotosVo extends NewBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    @ApiModelProperty(value = "ID", required = true)
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
     * 附件
     */
    @ExcelProperty(value = "附件")
    @ApiModelProperty(value = "附件", required = true)
    private String attachment;

}

