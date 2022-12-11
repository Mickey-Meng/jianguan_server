package com.ruoyi.ql.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
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


}
