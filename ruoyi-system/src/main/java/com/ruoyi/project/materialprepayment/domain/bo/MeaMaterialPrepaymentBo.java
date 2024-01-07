package com.ruoyi.project.materialprepayment.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 材料预付款业务对象 mea_material_prepayment
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaMaterialPrepaymentBo extends BaseEntity {

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
     * 计量期次编号
     */
    @NotBlank(message = "计量期次编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jlqsbh;

    /**
     * 材料编号，预付款编号
     */
    @NotBlank(message = "材料编号，预付款编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String clbh;

    /**
     * 材料名称
     */
    @NotBlank(message = "材料名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String clmc;

    /**
     * 材料名称
     */
    @NotBlank(message = "材料名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dw;

    /**
     * 单价
     */
    @NotNull(message = "单价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal dj;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal sl;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal je;

    /**
     * 预付比例
     */
    @NotNull(message = "预付比例不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal yfbl;

    /**
     * 预付金额
     */
    @NotNull(message = "预付金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal yfje;

    /**
     * 材料来源
     */
    @NotBlank(message = "材料来源不能为空", groups = { AddGroup.class, EditGroup.class })
    private String clly;

    /**
     * 单据编号
     */
    @NotBlank(message = "单据编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String djbh;

    /**
     * 质保书编号
     */
    @NotBlank(message = "质保书编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zbsbh;

    /**
     *  抽检报告编号
     */
    @NotBlank(message = " 抽检报告编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String cjbgbh;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     *  申请时间
     */
    @NotNull(message = " 申请时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private LocalDateTime sqsj;

}
