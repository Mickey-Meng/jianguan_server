package com.ruoyi.ql.domain.bo;


import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 入库单明细业务对象 ql_warehousing_detail
 *
 * @author ruoyi
 * @date 2023-05-18
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlWarehousingDetailBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 入库单单据id，外键关联入库单表（ql_warehousing）
     */
    @NotNull(message = "库存id，外键关联入库单表、出库单表（ql_warehousing,ql_outbound），不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long inventoryId
    ;

    /**
     * 入库单单据id集合
     */
    private List<Long> inventoryIds;

    /**
     * 入库单单据编号，外键关联入库单表（ql_warehousing）
     */
    @NotBlank(message = "入库单单据编号，外键关联入库单表、出库单表（ql_warehousing,ql_outbound）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String inventoryCode;

    /**
     * 产品id
     */
    @NotBlank(message = "产品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsId;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsName;

    /**
     * 基准价
     */
    @NotNull(message = "基准价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal basePrice;


    /**
     * 销售价
     */
    private BigDecimal salePrice;

    /**
     * 进货价
     */
    private BigDecimal incomePrice;

    /**
     * 附加价格
     */
    @NotNull(message = "附加价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal extraPrice;

    /**
     * 采购数量
     */
    @NotNull(message = "库存数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal  inventoryNumber;

    /**
     * 采购总价 = 采购数量 * 进货价
     */
    @NotNull(message = "采购总价 = 采购数量 * 进货价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amount;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 部门ID
     */
    @NotNull(message = "部门ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deptId;


    /**
     * 库存方向，warehousing: 入库、outbound：出库
     */
    private String inventoryDirection;

    /**
     * 规格
     */
    private String goodsSearchstandard;

    /**
     * 单位
     */
    private String goodsUnit;

    /**
     *
     */
    private Date inventoryDate;
}