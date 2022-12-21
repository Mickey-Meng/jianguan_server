package com.ruoyi.ql.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
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

    /**
     * 入库状态（1：已入库 0 未入库）
     */
    @ExcelProperty(value = "入库状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=：已入库,0=,未=入库")
    @ApiModelProperty(value = "入库状态", required = true)
    private String warehousingStatus;
    /**
     * 采购数量
     */
    @ExcelProperty(value = "采购数量")
    @ApiModelProperty(value = "采购数量", required = true)
    private Long orderNumber;

    /**
     * 采购合同id
     */
    @ExcelProperty(value = "采购合同id")
    @ApiModelProperty(value = "采购合同id", required = true)
    private String purchaseOrderId;
    /**
     * 入库数量
     */
    @ApiModelProperty(value = "入库数量")
    private Long warehousingNumber;

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id " )
    private String proudctId;
}
