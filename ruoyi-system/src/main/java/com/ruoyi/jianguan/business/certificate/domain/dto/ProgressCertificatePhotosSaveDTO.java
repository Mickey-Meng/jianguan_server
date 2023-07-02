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
public class ProgressCertificatePhotosSaveDTO extends PlanCertificatePhotosSaveDTO {

    /**
     * 附件
     */
    @NotBlank(message = "附件不能为空", groups = { AddGroup.class, EditGroup.class })
    private List<FileModel> attachment;

}
