package com.ruoyi.jianguan.manage.produce.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 构建类型业务对象 pub_conponent_type
 *
 * @author ruoyi
 * @date 2023-06-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PubComponentTypeBo extends BaseEntity {

    /**
     * 业务主键ID
     */
    private Integer id;

    /**
     * 工序库id
     */
    @NotBlank(message = "工序库id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String libraryId;

    /**
     * 构建类型名称
     */
    @NotBlank(message = "构建类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 构建类型编号
     */
    @NotBlank(message = "构建类型编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 备注
     */
    private String remark;


}
