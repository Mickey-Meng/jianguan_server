package com.ruoyi.project.startup.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 开工预付款业务对象 mea_startup_prepayment
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaStartupPrepaymentBo extends BaseEntity {

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
     * 开工预付款申请编号
     */
    @NotBlank(message = "开工预付款申请编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sqbh;

    /**
     * 申请日期
     */
    @NotNull(message = "申请日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sqsj;

    /**
     * 申请类型
     */
    @NotBlank(message = "申请类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sqlx;

    /**
     * 申请次数
     */
    @NotNull(message = "申请次数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sqcs;

    /**
     * 预付款金额
     */
    @NotNull(message = "预付款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal yukje;

    /**
     * 申请依据
     */
    @NotBlank(message = "申请依据不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sqyj;

    /**
     * 附件地址
     */
    @NotBlank(message = "附件地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fj;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
