package com.ruoyi.jianguan.business.certificate.domain.dto;

import com.ruoyi.common.core.domain.dto.PageDTO;
import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 计划管理-证照管理业务dto
 *
 * @author M.Z.B
 * @date : 2023/06/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProgressCertificatePhotosSaveDTO extends SaveDTO {
    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 证照名称
     */
    @NotBlank(message = "证照名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 证照内容
     */
    @NotBlank(message = "证照内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contents;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endTime;

    /**
     * 上报时间
     */
    @NotNull(message = "上报时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date reportTime;

    /**
     * 上报人
     */
    @NotBlank(message = "上报人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String reportUser;

    /**
     * 责任人
     */
    @NotBlank(message = "责任人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String owner;

    /**
     * 附件
     */
    @NotBlank(message = "附件不能为空", groups = { AddGroup.class, EditGroup.class })
    private List<FileModel> attachment;

    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    @NotNull(message = "状态 0 审批中 1 审批完成 2 驳回不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long projectId;

    /**
     * 备注
     */
    private String remark;

}
