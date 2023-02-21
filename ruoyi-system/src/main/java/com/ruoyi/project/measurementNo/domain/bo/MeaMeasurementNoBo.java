package com.ruoyi.project.measurementNo.domain.bo;

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
 * 中间计量期数管理业务对象 mea_measurement_no
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaMeasurementNoBo extends BaseEntity {

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
     * 计量期数编号
     */
    @NotBlank(message = "计量期数编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jlqsbh;

    /**
     * 计量期数文字表达
     */
    @NotBlank(message = "计量期数文字表达不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jlqs;

    /**
     * 开始日期
     */
    @NotNull(message = "开始日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date ksrq;

    /**
     * 结束日期
     */
    @NotNull(message = "结束日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date jsrq;

    /**
     * 默认日期
     */
    @NotNull(message = "默认日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date mrrq;

    /**
     * 报表编号
     */
    @NotBlank(message = "报表编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bbbh;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = " 锁定状态（0未锁定 1 已锁定）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
