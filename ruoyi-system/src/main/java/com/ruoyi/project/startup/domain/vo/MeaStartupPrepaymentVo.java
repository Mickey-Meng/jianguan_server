package com.ruoyi.project.startup.domain.vo;

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
 * 开工预付款视图对象 mea_startup_prepayment
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaStartupPrepaymentVo {

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
     * 计量期次编号
     */
    @ExcelProperty(value = "计量期次编号")
    private String jlqsbh;

    /**
     * 开工预付款申请编号
     */
    @ExcelProperty(value = "开工预付款申请编号")
    private String sqbh;

    /**
     * 申请日期
     */
    @ExcelProperty(value = "申请日期")
    private Date sqsj;

    /**
     * 申请类型
     */
    @ExcelProperty(value = "申请类型")
    private String sqlx;

    /**
     * 申请次数
     */
    @ExcelProperty(value = "申请次数")
    private Long sqcs;

    /**
     * 预付款金额
     */
    @ExcelProperty(value = "预付款金额")
    private BigDecimal yukje;

    /**
     * 申请依据
     */
    @ExcelProperty(value = "申请依据")
    private String sqyj;

    /**
     * 附件地址
     */
    @ExcelProperty(value = "附件地址")
    private String fj;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用")
    private String status;


}
