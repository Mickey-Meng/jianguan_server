package com.ruoyi.project.approval.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 台账报审视图对象 mea_ledger_approval
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaLedgerApprovalVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long id;

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
     * 台账分解编号
     */
    @ExcelProperty(value = "台账分解编号")
    private String tzfjbh;

    /**
     * 工程部位
     */
    @ExcelProperty(value = "工程部位")
    private String gcbw;

    /**
     * 数据状态
     */
    @ExcelProperty(value = "数据状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String dataStatus;

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
