package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库设置对象 ql_wh_warehouse
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_wh_warehouse")
public class QlWhWarehouse extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 仓库设置id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 仓库编码
     */
    private String warehouseCode;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 地址
     */
    private String address;
    /**
     * 负责人
     */
    private String principal;
    /**
     * 联系方式
     */
    private String telephone;
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
