package com.ruoyi.ql.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 出库单excel导出
 *
 * @author bx 2023/5/10
 */
@Data
public class OutboundVo {

    /**
     * 出库单号
     */
    @NotBlank(message = "出库单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String outboundCode;

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
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String telephone;

    /**
     * 地址描述
     */
    @NotBlank(message = "地址描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String address;

    @NotBlank(message = "产品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String proudctId;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String proudctName;

    /**
     * 商品规格
     */
    @NotBlank(message = "商品规格不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsSearchstandard;

    /**
     * 商品单位【关联字典管理】
     */
    @NotBlank(message = "商品单位【关联字典管理】不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsUnit;

    /**
     * 基准价
     */
    @NotNull(message = "基准价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal basePrice;

    /**
     * 进货价
     */
    @NotNull(message = "销售价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal salePrice;

    /**
     * 附加价格
     */
    @NotNull(message = "附加价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal extraPrice;

    @NotBlank(message = "附件--销售基准价截图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fj;

    /**
     * 销售日期
     */
    @NotNull(message = "销售日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date saleDate;

    /**
     * 销售数量
     */
    @NotNull(message = "销售数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal saleNumber;

    /**
     * 销售金额
     */
    @NotNull(message = "销售金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal saleAmount;

    /**
     * 出库对接人 todo
     */
    @NotBlank(message = "出库对接人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String outboundUsername;

    /**
     * 出库审核人 todo
     */
    @NotBlank(message = "出库审核人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String outboundReleaseuser;

    /**
     * 出库数量 todo
     */
//    private BigDecimal outboundNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID todo
     */
//    private Long deptId;

    /**
     * 项目id todo
     */
    @NotBlank(message = "项目id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectId;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectName;

    /**
     * 最后收款日期
     */
    private Date lastReceivableDate;

}
