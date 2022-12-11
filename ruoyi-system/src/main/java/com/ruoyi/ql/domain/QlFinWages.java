package com.ruoyi.ql.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工资管理对象 ql_fin_wages
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_fin_wages")
public class QlFinWages extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 月份
     */
    private Date payDate;
    /**
     * 基础工资
     */
    private BigDecimal basePay;
    /**
     * 基础天数
     */
    private Long baseDays;
    /**
     * 出勤天数
     */
    private BigDecimal attendanceDays;
    /**
     * 法定假日公休天数
     */
    private Long legalHolidayDays;
    /**
     * 加班天数
     */
    private Long workOvertimeDays;
    /**
     * 出差天数
     */
    private Long evectionDays;
    /**
     * 出差补贴
     */
    private BigDecimal evectionMoney;
    /**
     * 全勤奖
     */
    private BigDecimal fullAttendanceBonus;
    /**
     * 饭补
     */
    private BigDecimal mealAllowance;
    /**
     * 预付工资
     */
    private BigDecimal payInAdvance;
    /**
     * 社保
     */
    private BigDecimal socialSecurity;
    /**
     * 个人所得税
     */
    private BigDecimal tax;
    /**
     * 税前工资
     */
    private BigDecimal preTaxPay;
    /**
     * 税后工资
     */
    private BigDecimal afterTaxPay;
    /**
     * 实际到手工资
     */
    private BigDecimal netPay;
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
     * 员工姓名
     */
    private String empName;
    /**
     * 员工id
     */
    private String empId;

}
