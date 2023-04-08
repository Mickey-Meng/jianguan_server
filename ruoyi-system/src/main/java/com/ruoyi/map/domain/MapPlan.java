package com.ruoyi.map.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地图方案管理对象 map_plan
 *
 * @author ruoyi
 * @date 2023-04-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("map_plan")
public class MapPlan extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 方案名称
     */
    private String planName;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 层级
     */
    private Long level;
    /**
     * 分组
     */
    private String group;
    /**
     * 状态（0=正常,1=删除）
     */
    private String status;
    /**
     * 顺序编号
     */
    private Long orderNumber;
    /**
     * 分组类型
     */
    private String groupType;

}
