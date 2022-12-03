package com.ruoyi.project.file.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 附件记录业务对象 mea_file
 *
 * @author ruoyi
 * @date 2022-12-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MeaFileBo extends BaseEntity {

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空", groups = { EditGroup.class })
    private String fileId;

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileName;

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String flowId;

    /**
     * ID
     */
    @NotBlank(message = "ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String url;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String path;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
    private String remark;


}
