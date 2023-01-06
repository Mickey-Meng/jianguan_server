package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 商品信息视图对象 ql_shop_goods
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlShopGoodsVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;

    /**
     * 商品类别ID
     */
    @ExcelProperty(value = "商品类别ID")
    private Long goodsTypeId;

    /**
     * 商品编码
     */
    @ExcelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品单位【关联字典管理】
     */
    @ExcelProperty(value = "商品单位【关联字典管理】")
    private String goodsUnit;

    /**
     * 安全库存
     */
    @ExcelProperty(value = "安全库存")
    @ApiModelProperty(value = "安全库存", required = true)
    private Long safetyStock;

    /**
     * 实时库存
     */
    @ExcelProperty(value = "实时库存")
    @ApiModelProperty(value = "实时库存", required = true)
    private Long stockNumber;
}
