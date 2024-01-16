package com.ruoyi.jianguan.business.onlineForms.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 评定填报关联信息视图对象 pub_check_report
 *
 * @author mickey
 * @date 2024-01-16
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "评定填报关联信息", description = "评定填报关联信息VO")
public class PubCheckReportVo {

    private static final long serialVersionUID = 1L;

    /**
     * 业务主键ID
     */
    @ExcelProperty(value = "业务主键ID")
    @ApiModelProperty(value = "业务主键ID", required = true)
    private Long id;

    /**
     * 评定构建ID
     */
    @ExcelProperty(value = "评定构建ID")
    @ApiModelProperty(value = "评定构建ID", required = true)
    private Long checkComponentId;

    /**
     * 评定工序ID
     */
    @ExcelProperty(value = "评定工序ID")
    @ApiModelProperty(value = "评定工序ID", required = true)
    private Long checkProduceId;

    /**
     * 填报构建ID
     */
    @ExcelProperty(value = "填报构建ID")
    @ApiModelProperty(value = "填报构建ID", required = true)
    private Long reportComponentId;

    /**
     * 填报工序ID
     */
    @ExcelProperty(value = "填报工序ID")
    @ApiModelProperty(value = "填报工序ID", required = true)
    private Long reportProduceId;

    /**
     * 实测项目是否合格
     */
    @ExcelProperty(value = "实测项目是否合格")
    @ApiModelProperty(value = "实测项目是否合格", required = true)
    private Long scxmStatus;

    /**
     * 外观质量
     */
    @ExcelProperty(value = "外观质量")
    @ApiModelProperty(value = "外观质量", required = true)
    private Long wgzlStatus;

    /**
     * 资料完整性
     */
    @ExcelProperty(value = "资料完整性")
    @ApiModelProperty(value = "资料完整性", required = true)
    private Long zlwzxStatus;

    /**
     * 评定结果
     */
    @ExcelProperty(value = "评定结果")
    @ApiModelProperty(value = "评定结果", required = true)
    private Long checkResult;

    /**
     * 审批状态
     */
    @ExcelProperty(value = "审批状态")
    @ApiModelProperty(value = "审批状态", required = true)
    private Long status;

    /**
     * 填报时间
     */
    @ExcelProperty(value = "填报时间")
    @ApiModelProperty(value = "填报时间", required = true)
    private Date reportTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;


}
