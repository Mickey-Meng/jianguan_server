package com.ruoyi.ql.domain.importvo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出库单excel导出
 *
 * @author bx 2023/5/10
 */
@Data
public class QlOutboundImport {

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
     * 产品名称
     */
    private String goodsName;

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
     * 销售价
     */
    private BigDecimal salePrice;

    /**
     * 附加价格
     */
    private BigDecimal extraPrice;

    /**
     * 附件
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
     * 出库对接人 todo
     */
    private String outboundUsername;

    /**
     * 出库审核人 todo
     */
    private String outboundReleaseuser;

    /**
     * 备注
     */
    private String remark;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 销售数量
     */
    private BigDecimal inventoryNumber;

}
