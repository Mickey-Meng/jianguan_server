package com.ruoyi.ql.domain.vo;


import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 入库单明细视图对象 ql_warehousing_detail
 *
 * @author ruoyi
 * @date 2023-05-18
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "入库单明细", description = "入库单明细VO")
public class QlWarehousingDetailVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;


    /**
     * 入库单单据id，外键关联入库单表（ql_warehousing）
     */
    @ExcelProperty(value = "库存id，外键关联入库单表、出库单", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "q=l_warehousing")
    @ApiModelProperty(value = "库存id，外键关联入库单表、出库单", required = true)
    private Long inventoryId;

    /**
     * 入库单单据编号，外键关联入库单表（ql_warehousing）
     */
    @ExcelProperty(value = "入库单单据编号，外键关联入库单表、出库单", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "q=l_warehousing")
    @ApiModelProperty(value = "入库单单据编号，外键关联入库单表、出库单", required = true)
    private String inventoryCode;

    /**
     * 产品id
     */
    @ExcelProperty(value = "产品id")
    @ApiModelProperty(value = "产品id", required = true)
    private String goodsId;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    @ApiModelProperty(value = "产品名称", required = true)
    private String goodsName;

    /**
     * 基准价
     */
    @ExcelProperty(value = "基准价")
    @ApiModelProperty(value = "基准价", required = true)
    private BigDecimal basePrice;

    /**
     * 进货价
     */
    @ExcelProperty(value = "进货价")
    @ApiModelProperty(value = "进货价", required = true)
    private BigDecimal incomePrice;


    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 附加价格
     */
    @ExcelProperty(value = "附加价格")
    @ApiModelProperty(value = "附加价格", required = true)
    private BigDecimal extraPrice;

    /**
     * 采购数量
     */
    @ExcelProperty(value = "库存数量")
    @ApiModelProperty(value = "库存数量", required = true)
    private BigDecimal  inventoryNumber;

    /**
     * 采购总价 = 采购数量 * 进货价
     */
    @ExcelProperty(value = "采购总价 = 采购数量 * 进货价")
    @ApiModelProperty(value = "采购总价 = 采购数量 * 进货价", required = true)
    private BigDecimal amount;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    @ApiModelProperty(value = "部门ID", required = true)
    private Long deptId;


    /**
     * 库存方向，warehousing: 入库、outbound：出库
     */
    private String inventoryDirection;

    /**
     * 规格
     */
    private String goodsSearchstandard;

    /**
     * 单位
     */
    private String goodsUnit;

    private Date inventoryDate;

}