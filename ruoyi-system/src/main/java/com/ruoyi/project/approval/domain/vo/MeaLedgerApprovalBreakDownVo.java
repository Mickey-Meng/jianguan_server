package com.ruoyi.project.approval.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.project.ledger.domain.vo.MeaLedgerBreakdownDetailVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 台账报审视图对象 mea_ledger_approval
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaLedgerApprovalBreakDownVo {

    private static final long serialVersionUID = 1L;


    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    @ApiModelProperty(value = "标段编号", required = true)
    private String bdbh;

    /**
     * 申请期次
     */
    @ExcelProperty(value = "申请期次")
    @ApiModelProperty(value = "申请期次", required = true)
    private String sqqc;

    /**
     * 台账分解编号
     */
    @ExcelProperty(value = "台账分解编号")
    @ApiModelProperty(value = "台账分解编号", required = true)
    private String tzfjbh;

    /**
     * 工程部位
     */
    @ExcelProperty(value = "工程部位")
    @ApiModelProperty(value = "工程部位", required = true)
    private String gcbw;

    /**
     * 数据状态
     */
    @ExcelProperty(value = "数据状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    @ApiModelProperty(value = "数据状态", required = true)
    private String dataStatus;

    /**
     * 申报状态
     */
    @ExcelProperty(value = "申报状态")
    @ApiModelProperty(value = "申报状态", required = true)
    private String spzt;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;
    /**
     * 关联台账分解ID
     */
    @ExcelProperty(value = "关联台账分解ID")
    @ApiModelProperty(value = "关联台账分解", required = true)
    private MeaLedgerBreakdownDetailVo meaLedgerBreakdownDetailVo;

}
