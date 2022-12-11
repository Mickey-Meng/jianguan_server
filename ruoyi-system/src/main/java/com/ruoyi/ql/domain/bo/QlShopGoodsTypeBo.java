package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 商品类别业务对象 ql_shop_goods_type
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlShopGoodsTypeBo extends TreeEntity<QlShopGoodsTypeBo> {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 祖级列表
     */
    @NotBlank(message = "祖级列表不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ancestors;

    /**
     * 商品类别编码
     */
    @NotBlank(message = "商品类别编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsTypeCode;

    /**
     * 商品类别名称
     */
    @NotBlank(message = "商品类别名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsTypeName;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    @NotNull(message = "部门ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deptId;


}
