package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 工资管理视图对象 ql_fin_wages
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlFinWagesVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 月份
     */
    @ExcelProperty(value = "月份")
    private Date payDate;

    /**
     * 出勤天数
     */
    @ExcelProperty(value = "出勤天数")
    private BigDecimal attendanceDays;

    /**
     * 加班天数
     */
    @ExcelProperty(value = "加班天数")
    private Long workOvertimeDays;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工姓名")
    private String empName;


    @ExcelProperty(value = "税前工资")
    private String preTaxPay;

    @ExcelProperty(value = "到手工资")
    private String afterTaxPay;


}
