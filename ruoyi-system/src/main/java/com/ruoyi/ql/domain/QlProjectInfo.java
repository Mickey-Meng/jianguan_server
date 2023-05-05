package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目信息对象 ql_project_info
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_project_info")
public class QlProjectInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目金额
     */
    private BigDecimal projectAmount;
    /**
     * 项目简述
     */
    private String projectResume;
    /**
     * 项目描述
     */
    private String projectDistribute;
    /**
     * 宣传图
     */
    private String photo;
    /**
     * 附件
     */
    private String fj;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     *
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 项目所属地区
     */
    private String area;
    /**
     * 项目类型
     */
    private String projectType;
    /**
     * 项目开工日期
     */
    private Date projectStartDate;
    /**
     * 项目工期(天)
     */
    private Integer projectDays;
}
