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

import javax.validation.constraints.NotNull;


/**
 * 工序信息视图对象 pub_produce
 *
 * @author ruoyi
 * @date 2023-06-03
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "工序信息", description = "工序信息VO")
public class PubProduceVo {

    private static final long serialVersionUID = 1L;

    /**
     * 业务主键ID
     */
    @ExcelProperty(value = "业务主键ID")
    @ApiModelProperty(value = "业务主键ID", required = true)
    private Long id;

    /**
     * 构建类型ID
     */
    @ExcelProperty(value = "构建类型ID")
    @ApiModelProperty(value = "构建类型ID", required = true)
    private Long componentTypeId;

    /**
     * 构建类型编码
     */
    @ExcelProperty(value = "构建类型编码")
    @ApiModelProperty(value = "构建类型编码", required = true)
    private String componentTypeCode;

    /**
     * 工序名称
     */
    @ExcelProperty(value = "工序名称")
    @ApiModelProperty(value = "工序名称", required = true)
    private String name;

    /**
     * 工序顺序
     */
    @ExcelProperty(value = "工序顺序")
    @ApiModelProperty(value = "工序顺序", required = true)
    private Integer orderNum;

    /**
     * 
     */
    @ExcelProperty(value = "是否有效")
    @ApiModelProperty(value = "是否有效", required = true)
    private Integer isEffect;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;


}
