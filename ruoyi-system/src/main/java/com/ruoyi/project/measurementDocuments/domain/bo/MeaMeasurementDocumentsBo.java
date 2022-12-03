package com.ruoyi.project.measurementDocuments.domain.bo;

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
 * 计量凭证，设计计量、变更计量共用一张凭证，明细分开。业务对象 mea_measurement_documents
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaMeasurementDocumentsBo extends BaseEntity {

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
     * 台账分解编号
     */
    @NotBlank(message = "台账分解编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tzfjbh;

    /**
     * 凭证编号
     */
    @NotBlank(message = "凭证编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pzbh;

    /**
     * 计量类型
     */
    @NotBlank(message = "计量类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jllx;

    /**
     * 计量日期
     */
    @NotNull(message = "计量日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date jlrq;

    /**
     * 交工证书/变更令编号
     */
    @NotBlank(message = "交工证书/变更令编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jgzs;

    /**
     * 工程部位
     */
    @NotBlank(message = "工程部位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String gcbw;

    /**
     * 计算式
     */
    @NotBlank(message = "计算式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jss;

    /**
     * 计量比例
     */
    @NotBlank(message = "计量比例不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jlbl;

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
