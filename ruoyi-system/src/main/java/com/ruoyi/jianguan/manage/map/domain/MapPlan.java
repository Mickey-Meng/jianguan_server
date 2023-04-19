package com.ruoyi.jianguan.manage.map.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.experimental.Accessors;

/**
 * 地图方案管理对象 map_plan
 *
 * @author ruoyi
 * @date 2023-04-08
 */
@Data
@Accessors(chain = true)
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
     * 关键字加`防止脚本执行报错
     */
    @TableField("`level`")
    private Long level;
    /**
     * 分组
     */
    @TableField("`group`")
    private String group;
    /**
     * 状态（0=正常,1=删除）
     */
    @TableField("`status`")
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
