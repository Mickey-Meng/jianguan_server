package com.ruoyi.ql.domain.vo;


import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


/**
 * 合同与商品关系视图对象 ql_contract_goods_rel
 *
 * @author ruoyi
 * @date 2023-05-16
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "合同与商品关系", description = "合同与商品关系VO")
public class QlContractGoodsRelVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 合同Id
     */
    @ExcelProperty(value = "合同Id")
    @ApiModelProperty(value = "合同Id", required = true)
    private Long contractId;

    /**
     * 商品Id
     */
    @ExcelProperty(value = "商品Id")
    @ApiModelProperty(value = "商品Id", required = true)
    private Long goodsId;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    @ApiModelProperty(value = "商品名称", required = true)
    private String goodsName;

    /**
     * 单价
     */
    @ExcelProperty(value = "单价")
    @ApiModelProperty(value = "单价", required = true)
    private BigDecimal price;

    /**
     * 商品数量
     */
    @ExcelProperty(value = "商品数量")
    @ApiModelProperty(value = "商品数量", required = true)
    private String goodsNum;

    /**
     * 合同类型，purchase：采购合同、sale：销售合同
     */
    @ExcelProperty(value = "合同类型，purchase：采购合同、sale：销售合同")
    @ApiModelProperty(value = "合同类型，purchase：采购合同、sale：销售合同", required = true)
    private String contractType;

    /**
     * 规格
     */
    private String goodsSearchstandard;

    /**
     * 单位
     */
    private String goodsUnit;


    private BigDecimal totalAmountDollar;

    private String remark;

}