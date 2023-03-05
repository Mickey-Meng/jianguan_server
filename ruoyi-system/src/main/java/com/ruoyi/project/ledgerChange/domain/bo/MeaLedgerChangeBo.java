package com.ruoyi.project.ledgerChange.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账变更/工程变更业务对象 mea_ledger_change
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaLedgerChangeBo extends BaseEntity implements Serializable {

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空", groups = { EditGroup.class })
    private String id;

    /**
     * 变更编号
     */
//    @NotBlank(message = "变更编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bgbh;

    /**
     * 变更事项
     */
//    @NotBlank(message = "变更事项不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bgsx;

    /**
     * 变更等级
     */
//    @NotBlank(message = "变更等级不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bgdj;

    /**
     * 变更类型
     */
//    @NotBlank(message = "变更类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bglx;

    /**
     * 桩号
     */
    //@NotBlank(message = "桩号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zh;

    /**
     * 子目号
     */
   // @NotBlank(message = "子目号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zmh;

    /**
     * 工程部位
     */
//    @NotBlank(message = "工程部位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String gcbw;

    /**
     * 图号
     */
//    @NotBlank(message = "图号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String th;

    /**
     * 申请日期
     */
//    @NotNull(message = "申请日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sqrq;

    /**
     * 变更金额
     */
//    @NotNull(message = "变更金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal bgje;

    /**
     * 变更原因
     */
//    @NotBlank(message = "变更原因不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bgyy;

    /**
     * 计算式
     */
//    @NotBlank(message = "计算式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jss;

    /**
     * 数据状态
     */
   // @NotBlank(message = "数据状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dataStatus;

    /**
     * 状态（0正常 1停用）
     */
//    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    private String remark;

    /**
     * 变更编号名称
     */
    @NotBlank(message = "变更编号名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bgbhName;
}
