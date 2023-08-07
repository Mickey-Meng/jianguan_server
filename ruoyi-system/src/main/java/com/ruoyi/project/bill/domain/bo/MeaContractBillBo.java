package com.ruoyi.project.bill.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 工程量清单业务对象 mea_contract_bill
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaContractBillBo extends TreeEntity<MeaContractBillBo> {

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 标段编号
     */
    @NotBlank(message = "标段编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bdbh;

    /**
     * 子目号
     */
    @NotBlank(message = "子目号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zmh;

    /**
     * 子目名称
     */
    @NotBlank(message = "子目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zmmc;

    /**
     * 子目号父节点
     */
    @NotBlank(message = "子目号父节点不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zmhParent;

    /**
     * 子目号祖级列表
     */
//    @NotBlank(message = "子目号祖级列表不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zmhAncestors;

    /**
     * 单位
     */
//    @NotBlank(message = "单位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dw;

    /**
     * 合同单价
     */
//    @NotNull(message = "合同单价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal htdj;

    /**
     * 新增单价
     */
//    @NotNull(message = "新增单价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal xzdj;

    /**
     * 合同数量
     */
//    @NotNull(message = "合同数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal htsl;

    /**
     * 合同金额
     */
//    @NotNull(message = "合同金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal htje;

    /**
     * 审核数量
     */
//    @NotNull(message = "审核数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal shsl;

    /**
     * 审核金额
     */
//    @NotNull(message = "审核金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal shje;

    /**
     * 变更数量
     */
//    @NotNull(message = "变更数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal bgsl;

    /**
     * 变更金额
     */
//    @NotNull(message = "变更金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal bgje;

    /**
     * 总数量
     */
//    @NotNull(message = "总数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal zsl;

    /**
     * 总金额
     */
//    @NotNull(message = "总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal zje;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
//    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 数据是否是变更获取（1 变更数据 0正常合同）
     */
    private String isChange;
}
