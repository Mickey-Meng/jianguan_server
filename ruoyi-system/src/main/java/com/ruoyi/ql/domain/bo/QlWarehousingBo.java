package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入库管理业务对象 ql_warehousing
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlWarehousingBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 入库单号
     */
    @NotBlank(message = "入库单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehousingCode;

    /**
     * 入库对接人
     */
    @NotBlank(message = "入库对接人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehousingUsername;

    /**
     * 采购订单id
     */
    private String purchaseOrderId;

    /**
     * 产品id
     */
    @NotBlank(message = "产品id不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "采购数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal orderNumber;

    /**
     * 采购金额
     */
    @NotNull(message = "采购金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amount;
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
    @NotBlank(message = "产品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String proudctName;


    /**
     * 到货日期
     */
    @NotNull(message = "到货日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date arrivalDate;

    /**
     * 采购员
     */
    @NotBlank(message = "采购员不能为空", groups = { AddGroup.class, EditGroup.class })
    private String purchaser;

    /**
     * 采购合同id
     */
    @NotBlank(message = "采购合同id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractId;

    /**
     * 采购合同编码
     */
    @NotBlank(message = "采购合同编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractCode;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "入库复核人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehousingReleaseuser;

    /**
     * 基准价
     */
    @NotNull(message = "基准价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal basePrice;

    /**
     * 进货价
     */
    @NotNull(message = "进货价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal incomePrice;

    /**
     * 附加价格
     */
    @NotNull(message = "附加价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal extraPrice;

    /**
     * 附件--进货基准价截图
     */
    private String fj;

    /**
     * 进货日期，默认系统当天日期
     */
    @NotNull(message = "进货日期，默认系统当天日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date incomeDate;

    /**
     * 最后付款日期
     */
    @NotNull(message = "最后付款日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date lastPaymentDate;


    /**
     * 规格
     */
    @NotBlank(message = "规格不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsSearchstandard;

    /**
     * 单位
     */
    @NotBlank(message = "单位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsUnit;
}
