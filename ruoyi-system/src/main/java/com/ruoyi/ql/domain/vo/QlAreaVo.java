package com.ruoyi.ql.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 省市区视图对象 ql_area
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlAreaVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long Id;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String Name;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long Pid;


}
