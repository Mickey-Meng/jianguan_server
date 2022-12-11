package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工信息管理业务对象 ql_fin_emp
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlFinEmpBo extends BaseEntity {

    /**
     * ID ,主键
     */
    @NotNull(message = "ID ,主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empName;

    /**
     * 年龄
     */
    private Long empAge;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empGender;

    /**
     * 入职日期
     */
    private Date empRzDate;

    /**
     * 基础工资
     */
    @NotNull(message = "基础工资不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long empBasepay;

    /**
     * 身份证号码
     */
    @NotBlank(message = "身份证号码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empId;

    /**
     * 家庭住址
     */
    private String empAddr;

    /**
     * 备注
     */
    private String remark;


}
