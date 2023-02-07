package com.ruoyi.ql.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 员工信息管理视图对象 ql_fin_emp
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlFinEmpVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID ,主键
     */
    @ExcelProperty(value = "ID ,主键")
    private Long id;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String empName;

    /**
     * 年龄
     */
    @ExcelProperty(value = "年龄")
    private Long empAge;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String empGender;

    /**
     * 基础工资
     */
    @ExcelProperty(value = "基础工资")
    private Long empBasepay;

    /**
     * 身份证号码
     */
    @ExcelProperty(value = "身份证号码")
    private String empId;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    @ApiModelProperty(value = "手机号", required = true)
    private String mobilePhone;

    /**
     * 紧急联系人
     */
    @ExcelProperty(value = "紧急联系人")
    @ApiModelProperty(value = "紧急联系人", required = true)
    private String emergencyContact;

    /**
     * 紧急联系人电话
     */
    @ExcelProperty(value = "紧急联系人电话")
    @ApiModelProperty(value = "紧急联系人电话", required = true)
    private String emergencyPhone;

    /**
     * 部门
     */
    @ExcelProperty(value = "部门")
    @ApiModelProperty(value = "部门", required = true)
    private Long deptId;
}
