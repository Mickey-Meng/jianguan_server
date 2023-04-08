package com.ruoyi.map.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 地图方案管理视图对象 map_plan
 *
 * @author ruoyi
 * @date 2023-04-08
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "地图方案管理", description = "地图方案管理VO")
public class MapPlanVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 方案名称
     */
    @ExcelProperty(value = "方案名称")
    @ApiModelProperty(value = "方案名称", required = true)
    private String planName;

    /**
     * 父级ID
     */
    @ExcelProperty(value = "父级ID")
    @ApiModelProperty(value = "父级ID", required = true)
    private Long parentId;

    /**
     * 层级
     */
    @ExcelProperty(value = "层级")
    @ApiModelProperty(value = "层级", required = true)
    private Long level;

    /**
     * 分组
     */
    @ExcelProperty(value = "分组")
    @ApiModelProperty(value = "分组", required = true)
    private String group;

    /**
     * 状态（0=正常,1=删除）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0==正常,1=删除")
    @ApiModelProperty(value = "状态", required = true)
    private String status;

    /**
     * 顺序编号
     */
    @ExcelProperty(value = "顺序编号")
    @ApiModelProperty(value = "顺序编号", required = true)
    private Long orderNumber;

    /**
     * 分组类型
     */
    @ExcelProperty(value = "分组类型")
    @ApiModelProperty(value = "分组类型", required = true)
    private String groupType;


}
