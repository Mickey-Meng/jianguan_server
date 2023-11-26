package com.ruoyi.ql.domain.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品信息对象 ql_shop_goods
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
public class QlShopGoodsExport {

    private static final long serialVersionUID=1L;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "产品编码")
    private String goodsCode;
    /**
     * 商品编码
     */
    @ExcelProperty(value = "产品名称")
    private String goodsName;
    /**
     * 品牌
     */
    @ExcelProperty(value = "品牌")
    private String goodsBrand;
    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;
    /**
     * 规格
     */
    @ExcelProperty(value = "规格")
    private String goodsSearchstandard;
    /**
     * 单位
     */
    @ExcelProperty(value = "单位")
    private String goodsUnit;
    /**
     * 成本价
     */
    @ExcelProperty(value = "成本价")
    private BigDecimal costPrice;
    /**
     * 预警库存
     */
    @ExcelProperty(value = "预警库存")
    private Long safetyStock;

}
