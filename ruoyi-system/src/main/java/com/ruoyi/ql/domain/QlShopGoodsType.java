package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 商品类别对象 ql_shop_goods_type
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_shop_goods_type")
public class QlShopGoodsType extends TreeEntity<QlShopGoodsType> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 祖级列表
     */
    private String ancestors;
    /**
     * 商品类别编码
     */
    private String goodsTypeCode;
    /**
     * 商品类别名称
     */
    private String goodsTypeName;
    /**
     * 显示顺序
     */
    private Long orderNum;
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
