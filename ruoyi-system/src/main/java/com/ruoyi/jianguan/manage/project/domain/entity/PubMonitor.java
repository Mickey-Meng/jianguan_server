package com.ruoyi.jianguan.manage.project.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备监控对象 pub_monitor
 *
 * @author ruoyi
 * @date 2023-05-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pub_monitor")
public class PubMonitor extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 项目编号
     */
    @TableId(value = "id")
    private Integer id;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 设备编号
     */
    private String deviceNo;
    /**
     * 通道编号
     */
    private String channelNo;
    /**
     * 监控url
     */
    private String url;
    /**
     * 坐标
     */
    private String geom;

}
