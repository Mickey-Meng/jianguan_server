package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 费用报销明细视图对象 ql_fin_reimbursement_item
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlFinReimbursementItemVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 报销订单ID
     */
    @ExcelProperty(value = "报销订单ID")
    private String reimbursementOrderId;

    /**
     * 报销类型
     */
    @ExcelProperty(value = "报销类型")
    private String finReimbursementType;

    /**
     * 消费日期
     */
    @ExcelProperty(value = "消费日期")
    private Date reimbursementDate;

    /**
     * 消费金额
     */
    @ExcelProperty(value = "消费金额")
    private BigDecimal finAmount;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
