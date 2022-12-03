package com.ruoyi.project.approval.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 期数管理视图对象 mea_ledger_approval_no
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaLedgerApprovalNoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    private String bdbh;

    /**
     * 申请期次
     */
    @ExcelProperty(value = "申请期次")
    private String sqqc;

    /**
     * 申报日期
     */
    @ExcelProperty(value = "申报日期")
    private Date sbsj;

    /**
     * 申报状态
     */
    @ExcelProperty(value = "申报状态")
    private String spzt;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;


}
