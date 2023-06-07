package com.ruoyi.jianguan.manage.produce.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序库业务对象 pub_produce_library
 *
 * @author ruoyi
 * @date 2023-06-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PubProduceLibraryBo extends BaseEntity {

    /**
     * 业务主键ID
     */
    @NotNull(message = "业务主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 工序库名称
     */
    @NotBlank(message = "工序库名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 工序库编号
     */
    @NotBlank(message = "工序库编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 父级工序
     */
    @NotNull(message = "父级工序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentId;
    /**
     * 级别
     */
    private Integer groupLevel;
    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 备注
     */
    private String remark;

}
