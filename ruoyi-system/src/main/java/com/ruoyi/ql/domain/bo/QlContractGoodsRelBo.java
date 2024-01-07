package com.ruoyi.ql.domain.bo;


import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同与商品关系业务对象 ql_contract_goods_rel
 *
 * @author ruoyi
 * @date 2023-05-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlContractGoodsRelBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 合同Id
     */
    @NotNull(message = "合同Id", groups = { AddGroup.class, EditGroup.class })
    private Long contractId;

    /**
     * 合同Id
     */
    @NotNull(message = "合同Id", groups = { AddGroup.class, EditGroup.class })
    private List<Long> contractIds;

    /**
     * 商品Id
     */
    @NotNull(message = "商品Id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long goodsId;

    /**
     * 商品Id集合
     */
    private List<Long> goodsIds;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsName;

    /**
     * 单价
     */
    @NotNull(message = "单价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal price;

    /**
     * 商品数量
     */
    @NotBlank(message = "商品数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsNum;

    /**
     * 合同类型，purchase：采购合同、sale：销售合同
     */
    @NotBlank(message = "合同类型，purchase：采购合同、sale：销售合同不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractType;

    private String goodsUnit;

    private BigDecimal totalAmountDollar;

    private String remark;

}