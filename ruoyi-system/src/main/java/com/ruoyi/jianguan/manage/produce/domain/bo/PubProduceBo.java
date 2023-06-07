package com.ruoyi.jianguan.manage.produce.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序信息业务对象 pub_produce
 *
 * @author ruoyi
 * @date 2023-06-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PubProduceBo extends BaseEntity {

    /**
     * 业务主键ID
     */
    @NotNull(message = "业务主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 构建类型编码
     */
    @NotBlank(message = "构建类型编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String componentTypeCode;

    /**
     * 工序名称
     */
    @NotBlank(message = "工序名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rangee;

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long isvaild;

    /**
     * 备注
     */
    private String remark;


}
