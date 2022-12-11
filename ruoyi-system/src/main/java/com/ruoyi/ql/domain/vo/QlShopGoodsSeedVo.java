package com.ruoyi.ql.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 商品库存信息视图对象 ql_shop_goods_seed
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlShopGoodsSeedVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 商品信息id
     */
    @ExcelProperty(value = "商品信息id")
    private Long goodsId;

    /**
     * 安全库存
     */
    @ExcelProperty(value = "安全库存")
    private Long safetyStock;

    /**
     * 库存数量
     */
    @ExcelProperty(value = "库存数量")
    private Long stockNumber;


}
