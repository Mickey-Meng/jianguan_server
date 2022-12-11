package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库区设置对象 ql_wh_reservoir
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_wh_reservoir")
public class QlWhReservoir extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 库区设置id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 库区编码
     */
    private String reservoirCode;
    /**
     * 库区名称
     */
    private String reservoirName;
    /**
     * 所属仓库
     */
    private Long warehouseId;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;

}
