package com.ruoyi.project.measurementNo.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 中间计量期数管理视图对象 mea_measurement_no
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaMeasurementNoVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private String id;

    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    private String bdbh;

    /**
     * 计量期数编号
     */
    @ExcelProperty(value = "计量期数编号")
    private String jlqsbh;

    /**
     * 计量期数文字表达
     */
    @ExcelProperty(value = "计量期数文字表达")
    private String jlqs;

    /**
     * 开始日期
     */
    @ExcelProperty(value = "开始日期")
    private Date ksrq;

    /**
     * 结束日期
     */
    @ExcelProperty(value = "结束日期")
    private Date jsrq;

    /**
     * 默认日期
     */
    @ExcelProperty(value = "默认日期")
    private Date mrrq;

    /**
     * 报表编号
     */
    @ExcelProperty(value = "报表编号")
    private String bbbh;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;



    private String name;


}
