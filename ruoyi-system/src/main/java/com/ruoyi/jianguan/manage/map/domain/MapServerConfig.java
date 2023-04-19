package com.ruoyi.jianguan.manage.map.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地图服务注册对象 sys_map_server_config
 *
 * @author ruoyi
 * @date 2023-04-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("map_server_config")
public class MapServerConfig extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 服务名称
     */
    private String serverName;
    /**
     * 自定义服务id
     */
    private String serverCode;
    /**
     * 地图服务地址
     */
    private String serverUrl;
    /**
     * 服务类型
     */
    private String serverType;
    /**
     * 是否可见
     */
    private String visiable;
    /**
     * 状态（0=正常,1=删除）
     */
    private String status;
    /**
     * 属性信息
     */
    private String attrbuites;
    /**
     * 服务缩率图
     */
    private String thumbnail;
    /**
     *
     */
    private String description;
    /**
     * 备注
     */
    private String remark;

}
