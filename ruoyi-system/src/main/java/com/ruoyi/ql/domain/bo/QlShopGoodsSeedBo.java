package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品库存信息业务对象 ql_shop_goods_seed
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlShopGoodsSeedBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商品信息id
     */
    @NotNull(message = "商品信息id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long goodsId;

    /**
     * 安全库存
     */
    @NotNull(message = "安全库存不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long safetyStock;

    /**
     * 库存数量
     */
    @NotNull(message = "库存数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long stockNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;


}
