package com.ruoyi.jianguan.manage.map.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 地图方案服务关联对象 map_plan_server
 *
 * @author ruoyi
 * @date 2023-04-10
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("map_plan_server")
public class MapPlanServer extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 地图方案ID
     */
    @NonNull
    private Long planId;
    /**
     * 地图服务信息ID
     */
    @NonNull
    private Long serverId;

}
