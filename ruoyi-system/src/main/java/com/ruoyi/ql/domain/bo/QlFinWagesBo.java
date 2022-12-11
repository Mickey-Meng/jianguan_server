package com.ruoyi.ql.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工资管理业务对象 ql_fin_wages
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlFinWagesBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 月份
     */
    @NotNull(message = "月份不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date payDate;

    /**
     * 基础工资
     */
    @NotNull(message = "基础工资不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal basePay;

    /**
     * 基础天数
     */
    @NotNull(message = "基础天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long baseDays;

    /**
     * 出勤天数
     */
    @NotNull(message = "出勤天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal attendanceDays;

    /**
     * 法定假日公休天数
     */
    @NotNull(message = "法定假日公休天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long legalHolidayDays;

    /**
     * 加班天数
     */
    @NotNull(message = "加班天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long workOvertimeDays;

    /**
     * 出差天数
     */
    @NotNull(message = "出差天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long evectionDays;

    /**
     * 出差补贴
     */
    @NotNull(message = "出差补贴不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal evectionMoney;

    /**
     * 全勤奖
     */
    @NotNull(message = "全勤奖不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal fullAttendanceBonus;

    /**
     * 饭补
     */
    @NotNull(message = "饭补不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal mealAllowance;

    /**
     * 预付工资
     */
    @NotNull(message = "预付工资不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal payInAdvance;

    /**
     * 社保
     */
    @NotNull(message = "社保不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal socialSecurity;

    /**
     * 个人所得税
     */
    @NotNull(message = "个人所得税不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal tax;

    /**
     * 税前工资
     */
    @NotNull(message = "税前工资不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal preTaxPay;

    /**
     * 税后工资
     */
    @NotNull(message = "税后工资不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal afterTaxPay;

    /**
     * 实际到手工资
     */
    @NotNull(message = "实际到手工资不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal netPay;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 员工姓名
     */
    @NotBlank(message = "员工姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empName;

    /**
     * 员工id
     */
    @NotBlank(message = "员工id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String empId;


}
