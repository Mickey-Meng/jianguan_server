package com.ruoyi.jianguan.manage.produce.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;


/**
 * 工序库视图对象 pub_produce_library
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "工序库", description = "工序库VO")
public class PubProduceLibraryVo {

    private static final long serialVersionUID = 1L;

    /**
     * 业务主键ID
     */
    @ExcelProperty(value = "业务主键ID")
    @ApiModelProperty(value = "业务主键ID", required = true)
    private Long id;

    /**
     * 项目id
     */
    @ExcelProperty(value = "项目id")
    @ApiModelProperty(value = "项目id", required = true)
    private String projectId;

    /**
     * 工序库名称
     */
    @ExcelProperty(value = "工序库名称")
    @ApiModelProperty(value = "工序库名称", required = true)
    private String name;

    /**
     * 工序库编号
     */
    @ExcelProperty(value = "工序库编号")
    @ApiModelProperty(value = "工序库编号", required = true)
    private String code;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    @ApiModelProperty(value = "排序", required = true)
    private Integer orderNum;
    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

}
