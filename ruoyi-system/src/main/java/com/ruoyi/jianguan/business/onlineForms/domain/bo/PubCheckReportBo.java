package com.ruoyi.jianguan.business.onlineForms.domain.bo;

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
 * 评定填报关联信息业务对象 pub_check_report
 *
 * @author mickey
 * @date 2024-01-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PubCheckReportBo extends BaseEntity {

    /**
     * 业务主键ID
     */
    @NotNull(message = "业务主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 评定构建ID
     */
    @NotNull(message = "评定构建ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long checkComponentId;

    /**
     * 评定工序ID
     */
    @NotNull(message = "评定工序ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long checkProduceId;

    /**
     * 填报构建ID
     */
    @NotNull(message = "填报构建ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long reportComponentId;

    /**
     * 填报工序ID
     */
    @NotNull(message = "填报工序ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long reportProduceId;

    /**
     * 实测项目是否合格
     */
    @NotNull(message = "实测项目是否合格不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long scxmStatus;

    /**
     * 外观质量
     */
    @NotNull(message = "外观质量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long wgzlStatus;

    /**
     * 资料完整性
     */
    @NotNull(message = "资料完整性不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long zlwzxStatus;

    /**
     * 评定结果
     */
    @NotNull(message = "评定结果不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long checkResult;

    /**
     * 审批状态
     */
    @NotNull(message = "审批状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

    /**
     * 填报时间
     */
    @NotNull(message = "填报时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date reportTime;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
