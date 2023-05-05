package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 项目信息视图对象 ql_project_info
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlProjectInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

    /**
     * 项目名称
     */
    @ExcelProperty(value = "项目名称")
    private String projectName;

    /**
     * 项目所属地区
     */
    @ExcelProperty(value = "项目所属地区", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String area;

    /**
     * 项目金额
     */
    private BigDecimal projectAmount;

    /**
     * 项目简述
     */
    private String projectResume;

    /**
     * 项目描述
     */
    private String projectDistribute;

    /**
     * 宣传图
     */
    private String photo;

    /**
     * 附件
     */
    private String fj;

    /**
     *
     */
    private String remark;
    /**
     * 项目类型
     */
    @ExcelProperty(value = "项目类型")
    @ApiModelProperty(value = "项目类型", required = true)
    private String projectType;

    /**
     * 项目开工日期
     */
    @ExcelProperty(value = "项目开工日期")
    @ApiModelProperty(value = "项目开工日期", required = true)
    private Date projectStartDate;

    /**
     * 项目工期(天)
     */
    @ExcelProperty(value = "项目工期(天)")
    @ApiModelProperty(value = "项目工期(天)", required = true)
    private Integer projectDays;

}
