package com.ruoyi.ql.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 仓库设置视图对象 ql_wh_warehouse
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlWhWarehouseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库设置id
     */
    @ExcelProperty(value = "仓库设置id")
    private Long id;

    /**
     * 仓库编码
     */
    @ExcelProperty(value = "仓库编码")
    private String warehouseCode;

    /**
     * 仓库名称
     */
    @ExcelProperty(value = "仓库名称")
    private String warehouseName;

    /**
     * 所在城市
     */
    @ExcelProperty(value = "所在城市", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String city;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    private String address;

    /**
     * 负责人
     */
    @ExcelProperty(value = "负责人")
    private String principal;

    /**
     * 联系方式
     */
    @ExcelProperty(value = "联系方式")
    private String telephone;


}
