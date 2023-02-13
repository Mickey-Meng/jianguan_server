package com.ruoyi.project.approval.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 期数管理业务对象 mea_ledger_approval_no
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaLedgerApprovalNoBo extends BaseEntity {

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
     * 申请期次
     */
    @NotBlank(message = "申请期次不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sqqc;

    /**
     * 申报日期
     */
    @NotNull(message = "申报日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sbsj;

    /**
     * 申报状态
     */
    private String spzt;
    /**
     * 申报状态（1申报中 2完成申报 3申报打回）
     */
    private String reviewCode;
    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
