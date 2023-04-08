package com.ruoyi.map.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地图服务注册业务对象 sys_map_server_config
 *
 * @author ruoyi
 * @date 2023-04-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MapServerConfigBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 服务名称
     */
    @NotBlank(message = "服务名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String serverName;

    /**
     * 自定义服务id
     */
    @NotBlank(message = "自定义服务id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String serverCode;

    /**
     * 地图服务地址
     */
    @NotBlank(message = "地图服务地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String serverUrl;

    /**
     * 服务类型
     */
    @NotBlank(message = "服务类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String serverType;

    /**
     * 是否可见
     */
    @NotNull(message = "是否可见不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long visiable;

    /**
     * 状态（0=正常,1=删除）
     */
    @NotBlank(message = "状态（0=正常,1=删除）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 属性信息
     */
    @NotBlank(message = "属性信息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrbuites;

    /**
     * 服务缩率图
     */
    @NotBlank(message = "服务缩率图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String thumbnail;

    /**
     *
     */
    @NotBlank(message = "不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
