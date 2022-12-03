package com.ruoyi.project.other.domain.vo;

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
 * 其他款项视图对象 mea_other_payment
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaOtherPaymentVo {

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
     * 计量期次
     */
    @ExcelProperty(value = "计量期次")
    private String jlqsbh;

    /**
     * 申请编号
     */
    @ExcelProperty(value = "申请编号")
    private String sqbh;

    /**
     * 申请日期
     */
    @ExcelProperty(value = "申请日期")
    private Date sqsj;

    /**
     * 所属单位
     */
    @ExcelProperty(value = "所属单位")
    private String ssdw;

    /**
     * 款项类型
     */
    @ExcelProperty(value = "款项类型")
    private String kxlx;

    /**
     * 款项金额
     */
    @ExcelProperty(value = "款项金额")
    private BigDecimal kxje;

    /**
     * 附件
     */
    @ExcelProperty(value = "附件")
    private String fj;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;


}
