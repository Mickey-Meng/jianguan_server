package com.ruoyi.jianguan.business.onlineForms.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序附件信息业务对象 pub_produce_document
 *
 * @author mickey
 * @date 2024-01-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PubProduceDocumentBo extends BaseEntity {

    /**
     * 业务主键ID
     */
    @NotNull(message = "业务主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 构建ID
     */
    @NotNull(message = "构建ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long componentId;

    /**
     * 工序ID
     */
    @NotNull(message = "工序ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long produceId;

    /**
     * 文档名称
     */
    @NotBlank(message = "文档名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String documentName;

    /**
     * 文档路径
     */
    @NotBlank(message = "文档路径不能为空", groups = { AddGroup.class, EditGroup.class })
    private String documentUrl;

    /**
     * 文档状态
     */
    @NotNull(message = "文档状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long documentStatus;

    /**
     * 文档类型(1-填报;2-评定)
     */
    @NotNull(message = "文档类型(1-填报;2-评定)", groups = { AddGroup.class, EditGroup.class })
    private Long documentType;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
