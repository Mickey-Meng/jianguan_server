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
 * 出库管理业务对象 ql_outbound
 *
 * @author ruoyi
 * @date 2023-05-05
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlOutboundBo extends BaseEntity {

    private List<Long> ids;

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 出库单号
     */
    @NotBlank(message = "出库单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String outboundCode;

    /**
     * 出库日期
     */
    @NotNull(message = "出库日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date outboundDate;

    /**
     * 销售员
     */
    @NotBlank(message = "销售员不能为空", groups = { AddGroup.class, EditGroup.class })
    private String salesperson;

    /**
     * 销售合同编号
     */
    @NotBlank(message = "销售合同编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String saleContractCode;

    /**
     * 采购合同编号
     */
    @NotBlank(message = "采购合同编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String purchaseContractCode;

    /**
     * 客户Id
     */
    @NotNull(message = "客户Id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long customerId;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 地址描述
     */
    @NotBlank(message = "地址描述不能为空", groups = { AddGroup.class, EditGroup.class })
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
//    @NotBlank(message = "附件--销售基准价截图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fj;

    /**
     * 销售日期
     */
    @NotNull(message = "销售日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date saleDate;

    /**
     * 销售数量
     */
    private BigDecimal saleNumber;

    /**
     * 销售金额
     */
    @NotNull(message = "销售金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal saleAmount;

    /**
     * 出库对接人
     */
    private String outboundUsername;

    /**
     * 出库审核人
     */
    @NotBlank(message = "出库审核人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String outboundReleaseuser;

    /**
     * 出库对接人id
     */
    private Long outboundUserId;

    /**
     * 出库审核人
     */
     private Long outboundReleaseuserId;

    /**
     * 出库数量
     */
    private BigDecimal outboundNumber;

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
    @NotBlank(message = "项目id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectId;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectName;

    /**
     * 出库单明细表
     */
    private List<QlWarehousingDetailBo> warehousingDetails;
    /**
     * 最后收款日期
     */
    @NotNull(message = "最后收款日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date lastReceivableDate;

    private String lockStatus;
}

