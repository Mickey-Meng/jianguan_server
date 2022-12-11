package com.ruoyi.project.ledgerChangeDetail.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账变更/工程变更 明细业务对象 mea_ledger_change_detail
 *
 * @author ruoyi
 * @date 2022-12-04
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaLedgerChangeDetailBo extends BaseEntity {

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String id;

    /**
     * 标段编号
     */
    @NotBlank(message = "标段编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bdbh;

    /**
     * 变更编号
     */
    @NotBlank(message = "变更编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bgbh;

    /**
     * 子目号
     */
//    @NotBlank(message = "子目号不能为空", groups = { EditGroup.class })
    private String zmh;

    /**
     * 子目名称
     */
//    @NotBlank(message = "子目名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zmmc;

    /**
     * 工程部位
     */
    @NotBlank(message = "工程部位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String gcbw;

    /**
     * 单位
     */
    @NotBlank(message = "单位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dw;

    /**
     * 合同单价
     */
    @NotNull(message = "合同单价不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "审核数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal shsl;

    /**
     * 审核金额
     */
//    @NotNull(message = "审核金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal shje;

    /**
     * 修正数量
     */
//    @NotNull(message = "修正数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal xzsl;

    /**
     * 修正金额
     */
//    @NotNull(message = "修正金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal xzje;

    /**
     * 已计量数量
     */
//    @NotNull(message = "已计量数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal yjlsl;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
