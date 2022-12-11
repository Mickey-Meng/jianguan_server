package com.ruoyi.ql.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 库区设置视图对象 ql_wh_reservoir
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlWhReservoirVo {

    private static final long serialVersionUID = 1L;

    /**
     * 库区设置id
     */
    @ExcelProperty(value = "库区设置id")
    private Long id;

    /**
     * 库区编码
     */
    @ExcelProperty(value = "库区编码")
    private String reservoirCode;

    /**
     * 库区名称
     */
    @ExcelProperty(value = "库区名称")
    private String reservoirName;

    /**
     * 所属仓库
     */
    @ExcelProperty(value = "所属仓库")
    private Long warehouseId;


}
