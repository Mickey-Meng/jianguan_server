package com.ruoyi.project.other.domain.bo;

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
 * 其他款项业务对象 mea_other_payment
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaOtherPaymentBo extends BaseEntity {

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
     * 计量期次
     */
    @NotBlank(message = "计量期次不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jlqsbh;

    /**
     * 申请编号
     */
    @NotBlank(message = "申请编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sqbh;

    /**
     * 申请日期
     */
    @NotNull(message = "申请日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sqsj;

    /**
     * 所属单位
     */
    @NotBlank(message = "所属单位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ssdw;

    /**
     * 款项类型
     */
    @NotBlank(message = "款项类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String kxlx;

    /**
     * 款项金额
     */
    @NotNull(message = "款项金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal kxje;

    /**
     * 附件
     */
    @NotBlank(message = "附件不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fj;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
