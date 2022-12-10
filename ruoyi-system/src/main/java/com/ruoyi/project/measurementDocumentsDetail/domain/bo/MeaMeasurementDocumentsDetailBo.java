package com.ruoyi.project.measurementDocumentsDetail.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账变更/工程变更 明细业务对象 mea_measurement_documents_detail
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaMeasurementDocumentsDetailBo extends BaseEntity {

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
     * 凭证编号
     */
//    @NotBlank(message = "凭证编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pzbh;

    /**
     * 子目号
     */
    @NotBlank(message = "子目号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String zmh;


    private String zmmc;

    /**
     * 本期计量数量
     */
    @NotNull(message = "本期计量数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal bqjlsl;

    /**
     * 计量类型
     */
//    @NotBlank(message = "计量类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jllx;

    /**
     * 状态（0正常 1停用）
     */
//    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
