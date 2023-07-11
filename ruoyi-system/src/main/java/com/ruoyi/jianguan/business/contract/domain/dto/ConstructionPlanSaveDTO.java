package com.ruoyi.jianguan.business.contract.domain.dto;


import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class ConstructionPlanSaveDTO extends SaveDTO {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 上报人
     */
    private String reportPeople;

    /**
     * 上报时间
     */
    private Date reportTime;
    private String responsiblePerson;
    private String responsiblePersonId;
    private Date plainStartTime;
    private Date plainEndTime;
    private String name;

    /**
     * 附件
     */
    @NotBlank(message = "附件不能为空", groups = {AddGroup.class, EditGroup.class})
    private List<FileModel> attachment;

    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    @NotNull(message = "状态 0 审批中 1 审批完成 2 驳回不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer status;
    private Integer reportStatus;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;


}