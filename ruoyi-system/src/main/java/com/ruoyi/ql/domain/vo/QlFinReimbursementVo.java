package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


/**
 * 费用报销视图对象 ql_fin_reimbursement
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlFinReimbursementVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 报销订单ID，唯一标识某一次具体报销事件
     */
    @ExcelProperty(value = "报销订单ID，唯一标识某一次具体报销事件")
    private String reimbursementOrderId;

    /**
     * 报销日期
     */
    @ExcelProperty(value = "报销日期")
    private Date finReimbursementDate;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    private BigDecimal finAmount;

    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工姓名")
    private String empName;
    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工ID")
    private String empId;
    @NotBlank(message = "员工姓名不可为空", groups = { AddGroup.class, EditGroup.class })
    private List<QlFinReimbursementItemVo> qlFinReimbursementItemVoList;
}
