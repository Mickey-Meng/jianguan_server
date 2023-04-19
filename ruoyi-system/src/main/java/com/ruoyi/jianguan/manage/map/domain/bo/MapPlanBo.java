package com.ruoyi.jianguan.manage.map.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地图方案管理业务对象 map_plan
 *
 * @author ruoyi
 * @date 2023-04-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MapPlanBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 方案名称
     */
    @NotBlank(message = "方案名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String planName;

    /**
     * 父级ID
     */
    @NotNull(message = "父级ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentId;

    /**
     * 层级
     */
    @NotNull(message = "层级不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long level;

    /**
     * 分组
     */
    @NotBlank(message = "分组不能为空", groups = { AddGroup.class, EditGroup.class })
    private String group;

    /**
     * 状态（0=正常,1=删除）
     */
    @NotBlank(message = "状态（0=正常,1=删除）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 顺序编号
     */
    @NotNull(message = "顺序编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderNumber;

    /**
     * 分组类型
     */
    @NotBlank(message = "分组类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String groupType;


}
