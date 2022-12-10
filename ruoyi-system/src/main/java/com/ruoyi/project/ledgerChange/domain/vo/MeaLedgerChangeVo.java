package com.ruoyi.project.ledgerChange.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.project.ledgerChangeDetail.domain.MeaLedgerChangeDetail;
import lombok.Data;
import java.util.Date;
import java.util.List;


/**
 * 台账变更/工程变更视图对象 mea_ledger_change
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaLedgerChangeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 变更编号
     */
    @ExcelProperty(value = "变更编号")
    private String bgbh;

    /**
     * 变更事项
     */
    @ExcelProperty(value = "变更事项")
    private String bgsx;

    /**
     * 变更等级
     */
    @ExcelProperty(value = "变更等级")
    private String bgdj;

    /**
     * 变更类型
     */
    @ExcelProperty(value = "变更类型")
    private String bglx;

    /**
     * 桩号
     */
    @ExcelProperty(value = "桩号")
    private String zh;

    /**
     * 子目号
     */
    @ExcelProperty(value = "子目号")
    private String zmh;

    /**
     * 工程部位
     */
    @ExcelProperty(value = "工程部位")
    private String gcbw;

    /**
     * 图号
     */
    @ExcelProperty(value = "图号")
    private String th;

    /**
     * 申请日期
     */
    @ExcelProperty(value = "申请日期")
    private Date sqrq;

    /**
     * 变更金额
     */
    @ExcelProperty(value = "变更金额")
    private BigDecimal bgje;

    /**
     * 变更原因
     */
    @ExcelProperty(value = "变更原因")
    private String bgyy;

    /**
     * 计算式
     */
    @ExcelProperty(value = "计算式")
    private String jss;

    /**
     * 数据状态
     */
    @ExcelProperty(value = "数据状态")
    private String dataStatus;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;
    //add by yangaogao 20221207 添加变更明细




}
