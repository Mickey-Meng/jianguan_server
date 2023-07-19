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
public class MaterialBrandReportSaveDTO extends SaveDTO {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = {EditGroup.class})
    private Long id;
    private String materialBrand;
    private String materialCategory;
    private String materialCategoryCode;
    private String samplePhoto;
    private String sampleContent;
    private String materialApproachPhoto;
    private String materialApproachContent;
    private String materialApproachQuantity;
    /**
     * 附件
     */
    private String attachment;
    private String attachment1;
    private String attachment2;
    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
    private Integer status1;
    private Integer status2;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;


}