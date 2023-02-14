package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品信息对象 ql_shop_goods
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_shop_goods")
public class QlShopGoods extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 供应商ID
     */
    private Long supplierId;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 商品类别ID
     */
    private Long goodsTypeId;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品条码
     */
    private String goodsBarcode;
    /**
     * 商品规格
     */
    private String goodsSearchstandard;
    /**
     * 商品颜色
     */
    private String goodsColor;
    /**
     * 所属库位主键
     */
    private Long storageId;
    private String storageName;
    /**
     * 商品单位【关联字典管理】
     */
    private String goodsUnit;
    /**
     * 商品重量(单位:千克)
     */
    private BigDecimal goodsWeight;
    /**
     * 成本价
     */
    private BigDecimal costPrice;
    /**
     * 零售价
     */
    private BigDecimal retailPrice;
    /**
     * 销售价
     */
    private BigDecimal sellingPrice;
    /**
     * 批发价
     */
    private BigDecimal wholesalePrice;
    /**
     * 成本价锁定
     */
    private String isCost;
    /**
     * 安全库存
     */
    private Long safetyStock;
    /**
     * 实时库存
     */
    private Long stockNumber;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;

}
