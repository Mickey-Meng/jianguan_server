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
 * 出库管理视图对象 ql_outbound
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlOutboundVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 出库对接人
     */
    @ExcelProperty(value = "出库对接人")
    private String outboundUsername;

    /**
     * 出库时间
     */
    @ExcelProperty(value = "出库时间")
    private Date outboundDate;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String proudctName;


}
