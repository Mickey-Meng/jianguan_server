package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出库管理对象 ql_outbound
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_outbound")
public class QlOutbound extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 出库单号
     */
    private String outboundCode;
    /**
     * 出库日期
     */
    private Date outboundDate;
    /**
     * 销售员
     */
    private String salesperson;
    /**
     * 销售合同编号
     */
    private String saleContractCode;
    /**
     * 采购合同编号
     */
    private String purchaseContractCode;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 地址描述
     */
    private String address;
    /**
     * 产品id
     */
    private String proudctId;
    /**
     * 产品名称
     */
    private String proudctName;
    /**
     * 商品规格
     */
    private String goodsSearchstandard;
    /**
     * 商品单位【关联字典管理】
     */
    private String goodsUnit;
    /**
     * 基准价
     */
    private BigDecimal basePrice;
    /**
     * 进货价
     */
    private BigDecimal salePrice;
    /**
     * 附加价格
     */
    private BigDecimal extraPrice;
    /**
     * 附件--销售基准价截图
     */
    private String fj;
    /**
     * 销售日期
     */
    private Date saleDate;
    /**
     * 销售数量
     */
    private BigDecimal saleNumber;
    /**
     * 销售金额
     */
    private BigDecimal saleAmount;
    /**
     * 出库对接人
     */
    private String outboundUsername;
    /**
     * 出库审核人
     */
    private String outboundReleaseuser;
    /**
     * 出库数量
     */
    private BigDecimal outboundNumber;
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
     * 项目id
     */
    private String projectId;
    /**
     * 项目名称
     */
    private String projectName;
}
