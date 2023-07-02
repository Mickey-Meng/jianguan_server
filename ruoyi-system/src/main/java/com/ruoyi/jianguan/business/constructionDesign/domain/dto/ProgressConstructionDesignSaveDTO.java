package com.ruoyi.jianguan.business.constructionDesign.domain.dto;

import com.ruoyi.common.core.domain.entity.FileModel;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 计划管理-施工图设计业务dto
 *
 * @author M.Z.B
 * @date : 2023/06/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProgressConstructionDesignSaveDTO extends PlanConstructionDesignSaveDTO {

    /**
     * 附件
     */
    @NotBlank(message = "附件不能为空", groups = { AddGroup.class, EditGroup.class })
    private List<FileModel> attachment;

}
