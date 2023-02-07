package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工信息管理对象 ql_fin_emp
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_fin_emp")
public class QlFinEmp extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID ,主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 姓名
     */
    private String empName;
    /**
     * 年龄
     */
    private Long empAge;
    /**
     * 性别
     */
    private String empGender;
    /**
     * 入职日期
     */
    private Date empRzDate;
    /**
     * 基础工资
     */
    private Long empBasepay;
    /**
     * 身份证号码
     */
    private String empId;
    /**
     * 家庭住址
     */
    private String empAddr;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 紧急联系人
     */
    private String emergencyContact;
    /**
     * 紧急联系人电话
     */
    private String emergencyPhone;
    /**
     * 部门
     */
    private Long deptId;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     * 备注
     */
    private String remark;

}
