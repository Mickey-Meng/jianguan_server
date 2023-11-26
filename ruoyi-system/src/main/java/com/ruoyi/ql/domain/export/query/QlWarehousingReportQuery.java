package com.ruoyi.ql.domain.export.query;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.ql.domain.bo.QlWarehousingDetailBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 入库管理业务对象 ql_warehousing
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlWarehousingReportQuery extends BaseReportQueryEntity {

    /**
     * 锁定状态
     */
    private String lockStatus;

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * id集合
     */
    private List<Long> ids;

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
    @NotNull(message = "销售总额不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 供应商Id
     */
    private Long supplierId;

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
    @NotBlank(message = "入库复核人不能为空", groups = { AddGroup.class, EditGroup.class })
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
//    @NotNull(message = "进货日期，默认系统当天日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date incomeDate;

    /**
     * 最后付款日期
     */
    @NotNull(message = "最后付款日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date lastPaymentDate;


    /**
     * 规格
     */
    private String goodsSearchstandard;

    /**
     * 单位
     */
    private String goodsUnit;
    /**
     * 入库单明细表
     */
    private List<QlWarehousingDetailBo> warehousingDetails;

    /**
     * 税率
     */
    @NotNull(message = "税率不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal rate;

    /**
     * 不含税金额
     */
    @NotNull(message = "不含税金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amountNotax;

    /**
     * 税额
     */
    @NotNull(message = "税额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amountTax;

    /**
     * 车牌号码
     */
    private String licensePlate;
}

