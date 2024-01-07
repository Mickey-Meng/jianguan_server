package com.ruoyi.ql.domain;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同与商品关系对象 ql_contract_goods_rel
 *
 * @author ruoyi
 * @date 2023-05-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_contract_goods_rel")
public class QlContractGoodsRel extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 合同Id
     */
    private Long contractId;
    /**
     * 商品Id
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 商品数量
     */
    private String goodsNum;
    /**
     * 合同类型，purchase：采购合同、sale：销售合同
     */
    private String contractType;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 单位
     */
    private String goodsUnit;

    private BigDecimal totalAmountDollar;

    private String remark;

}