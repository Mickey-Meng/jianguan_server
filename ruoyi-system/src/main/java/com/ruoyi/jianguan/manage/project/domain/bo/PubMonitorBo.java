package com.ruoyi.jianguan.manage.project.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备监控业务对象 pub_monitor
 *
 * @author ruoyi
 * @date 2023-05-28
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PubMonitorBo extends BaseEntity {

    /**
     * 项目编号
     */
    @NotNull(message = "项目编号不能为空", groups = { EditGroup.class })
    private Integer id;

    /**
     * 项目id
     */
    @NotBlank(message = "项目id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String projectId;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 设备编号
     */
    @NotBlank(message = "设备编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceNo;

    /**
     * 通道编号
     */
    @NotBlank(message = "通道编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String channelNo;

    /**
     * 监控url
     */
    @NotBlank(message = "监控url不能为空", groups = { AddGroup.class, EditGroup.class })
    private String url;

    /**
     * 坐标
     */
    @NotBlank(message = "坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String geom;

    private Integer orderNum;

}
