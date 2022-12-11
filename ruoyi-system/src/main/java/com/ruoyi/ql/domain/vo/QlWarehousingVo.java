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
 * 入库管理视图对象 ql_warehousing
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlWarehousingVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 入库单号
     */
    @ExcelProperty(value = "入库单号")
    private String warehousingCode;

    /**
     * 入库对接人
     */
    @ExcelProperty(value = "入库对接人")
    private String warehousingUsername;

    /**
     * 入库时间
     */
    @ExcelProperty(value = "入库时间")
    private Date warehousingDate;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String proudctName;


}
