package com.ruoyi.jianguan.manage.produce.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 构建类型视图对象 pub_conponent_type
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "构建类型", description = "构建类型VO")
public class PubComponentTypeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 业务主键ID
     */
    @ExcelProperty(value = "业务主键ID")
    @ApiModelProperty(value = "业务主键ID", required = true)
    private Integer id;

    /**
     * 工序库id
     */
    @ExcelProperty(value = "工序库id")
    @ApiModelProperty(value = "工序库id", required = true)
    private String libraryId;

    /**
     * 构建类型名称
     */
    @ExcelProperty(value = "构建类型名称")
    @ApiModelProperty(value = "构建类型名称", required = true)
    private String name;

    /**
     * 构建类型编号
     */
    @ExcelProperty(value = "构建类型编号")
    @ApiModelProperty(value = "构建类型编号", required = true)
    private String code;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;


}
