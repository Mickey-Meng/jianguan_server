package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入库管理对象 ql_warehousing
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_warehousing")
public class QlWarehousing extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 入库单号
     */
    private String warehousingCode;
    /**
     * 入库对接人
     */
    private String warehousingUsername;
    /**
     * 采购订单id
     */
    private String purchaseOrderId;
    /**
     * 产品id
     */
    private String proudctId;
    /**
     * 入库数量
     */
    private BigDecimal warehousingNumber;
    /**
     * 入库时间
     */
    private Date warehousingDate;

    /**
     * 入库状态（1：已入库 0 未入库）
     */
    private String warehousingStatus;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 采购数量
     */
    private BigDecimal orderNumber;
    /**
     * 采购金额
     */
    private BigDecimal amount;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     *
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 产品名称
     */
    private String proudctName;
    /**
     * 到货日期
     */
    private Date arrivalDate;
    /**
     * 采购员
     */
    private String purchaser;
    /**
     * 采购合同id
     */
    private String contractId;
    /**
     * 采购合同编码
     */
    private String contractCode;
    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 供应商电话
     */
    private String mobilePhone;
    /**
     * 供应商地址
     */
    private String address;

    /**
     * 入库复核人
     */
    private String warehousingReleaseuser;
    /**
     * 基准价
     */
    private BigDecimal basePrice;
    /**
     * 进货价
     */
    private BigDecimal incomePrice;
    /**
     * 附加价格
     */
    private BigDecimal extraPrice;
    /**
     * 附件--进货基准价截图
     */
    private String fj;
    /**
     * 进货日期，默认系统当天日期
     */
    private Date incomeDate;
    /**
     * 最后付款日期
     */
    private Date lastPaymentDate;
}
