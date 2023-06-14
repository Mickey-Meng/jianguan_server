package com.ruoyi.jianguan.manage.project.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 项目信息视图对象 jg_project_info
 *
 * @author ruoyi
 * @date 2023-04-19
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "项目信息", description = "项目信息VO")
public class JgProjectInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @ExcelProperty(value = "自增主键")
    @ApiModelProperty(value = "自增主键", required = true)
    private Long id;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    @ApiModelProperty(value = "名称", required = true)
    private String projectName;

    /**
     * 编码
     */
    @ExcelProperty(value = "编码")
    @ApiModelProperty(value = "编码", required = true)
    private String projectCode;

    /**
     * 父级ID
     */
    @ExcelProperty(value = "父级ID")
    @ApiModelProperty(value = "父级ID", required = true)
    private Long parentId;

    /**
     * 部门类型
     */
    @ExcelProperty(value = "部门类型")
    @ApiModelProperty(value = "部门类型", required = true)
    private String projectArea;

    /**
     * 级别
     */
    @ExcelProperty(value = "级别")
    @ApiModelProperty(value = "级别", required = true)
    private Integer groupLevel;

    /**
     * 状态（0正常 1停用）|-1: 删除, 0: 冻结, 1: 正常
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用")
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

    /**
     * 是否显示
     */
    @ExcelProperty(value = "是否显示")
    @ApiModelProperty(value = "是否显示", required = true)
    private String visible;

    /**
     * 顺序
     */
    @ExcelProperty(value = "顺序")
    @ApiModelProperty(value = "顺序", required = true)
    private Long orderNum;

    /**
     * 组织ID
     */
    @ExcelProperty(value = "组织ID")
    @ApiModelProperty(value = "组织ID", required = true)
    private String groupId;

    /**
     * 是否自管：0-否，1-是
     */
    @ExcelProperty(value = "是否自管：0-否，1-是")
    @ApiModelProperty(value = "是否自管：0-否，1-是", required = true)
    private Long isAuto;

    /**
     * 项目照片
     */
    @ExcelProperty(value = "项目照片")
    @ApiModelProperty(value = "项目照片", required = true)
    private String projectPic;

    /**
     * 合同号
     */
    @ExcelProperty(value = "合同号")
    @ApiModelProperty(value = "合同号", required = true)
    private String contractNum;

    /**
     * 坐标
     */
    @ExcelProperty(value = "坐标")
    @ApiModelProperty(value = "坐标", required = true)
    private String coordinate;

    /**
     * 投资金额（万元）
     */
    @ExcelProperty(value = "投资金额", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "万=元")
    @ApiModelProperty(value = "投资金额", required = true)
    private Long investment;

    /**
     * 项目类型（交通类、市政类、房建类、其他类）
     */
    @ExcelProperty(value = "项目类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "交=通类、市政类、房建类、其他类")
    @ApiModelProperty(value = "项目类型", required = true)
    private String projectType;

    /**
     * 项目点
     */
    @ExcelProperty(value = "项目点")
    @ApiModelProperty(value = "项目点", required = true)
    private String projectPoint;

    /**
     * 项目线
     */
    @ExcelProperty(value = "项目线")
    @ApiModelProperty(value = "项目线", required = true)
    private String projectLine;

    /**
     * 项目面
     */
    @ExcelProperty(value = "项目面")
    @ApiModelProperty(value = "项目面", required = true)
    private String projectSurface;

    /**
     * 项目简介
     */
    @ExcelProperty(value = "项目简介")
    @ApiModelProperty(value = "项目简介", required = true)
    private String introduction;
    /**
     * 项目简介
     */
    @ExcelProperty(value = "项目地图")
    @ApiModelProperty(value = "项目地图", required = true)
    private String mapUrl;

    /**
     * 工序库id
     */
    @ExcelProperty(value = "工序库")
    @ApiModelProperty(value = "工序库", required = true)
    private Long libraryId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;


}
