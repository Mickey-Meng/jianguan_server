package com.ruoyi.ql.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 入库单明细对象 ql_warehousing_detail
 *
 * @author ruoyi
 * @date 2023-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_warehousing_detail")
public class QlWarehousingDetail extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 库存id，外键关联入库单表、出库单（ql_warehousing,ql_outbound）
     */
    private Long inventoryId;
    /**
     * 入库单单据编号，外键关联入库单表、出库单（ql_warehousing,ql_outbound）
     */
    private String inventoryCode;
    /**
     * 产品id
     */
    private String goodsId;
    /**
     * 产品名称
     */
    private String goodsName;
    /**
     * 基准价
     */
    private BigDecimal basePrice;
    /**
     * 进货价
     */
    private BigDecimal incomePrice;
    /**
     * 销售价格
     */
    private BigDecimal salePrice;
    /**
     * 附加价格
     */
    private BigDecimal extraPrice;
    /**
     * 库存数量
     */
    private BigDecimal  inventoryNumber;
    /**
     * 采购总价 = 采购数量 * 进货价
     */
    private BigDecimal amount;
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

    private Date inventoryDate;

}