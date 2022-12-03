package com.ruoyi.project.approval.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账报审业务对象 mea_ledger_approval
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaLedgerApprovalBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 标段编号
     */
    @NotBlank(message = "标段编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bdbh;

    /**
     * 申请期次
     */
    @NotBlank(message = "申请期次不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sqqc;

    /**
     * 台账分解编号
     */
    @NotBlank(message = "台账分解编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tzfjbh;

    /**
     * 工程部位
     */
    @NotBlank(message = "工程部位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String gcbw;

    /**
     * 数据状态
     */
    @NotBlank(message = "数据状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dataStatus;

    /**
     * 申报状态
     */
    @NotBlank(message = "申报状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String spzt;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
