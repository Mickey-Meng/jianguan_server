package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品信息业务对象 ql_shop_goods
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlShopGoodsBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 供应商ID
     */
    @NotNull(message = "供应商ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long supplierId;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;

    /**
     * 商品类别ID
     */
    @NotNull(message = "商品类别ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long goodsTypeId;

    /**
     * 商品编码
     */
    @NotBlank(message = "商品编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsCode;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsName;

    /**
     * 商品条码
     */
    private String goodsBarcode;

    /**
     * 商品规格
     */
    @NotBlank(message = "商品规格不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "商品单位【关联字典管理】不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 安全库存
     */
    @NotNull(message = "安全库存不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long safetyStock;

    /**
     * 实时库存
     */
    private Long stockNumber;

    /**
     * 商品品牌
     */
    private String goodsBrand;
}
