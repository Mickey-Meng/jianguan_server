package com.ruoyi.project.measurementDocumentsDetail.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 台账变更/工程变更 明细视图对象 mea_measurement_documents_detail
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaMeasurementDocumentsDetailVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private String id;

    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    private String bdbh;

    /**
     * 凭证编号
     */
    @ExcelProperty(value = "凭证编号")
    private String pzbh;

    /**
     * 子目号
     */
    @ExcelProperty(value = "子目号")
    private String zmh;

    /**
     * 本期计量数量
     */
    @ExcelProperty(value = "本期计量数量")
    private BigDecimal bqjlsl;

    /**
     * 计量类型
     */
    @ExcelProperty(value = "计量类型")
    private String jllx;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;


    //modify yangaogao  20230216  中间计量是针对台账分解进行的，不是针对清单进行的。
//    private MeaContractBill meaContractBill;

    private MeaLedgerBreakdownDetail meaLedgerBreakdownDetail;
    /**
     * 台账分解明细ID
     */
    @ExcelProperty(value = "台账分解明细ID")
    @ApiModelProperty(value = "台账分解明细ID", required = true)
    private String meaLedgerBreakdownDetailId;

    /**
     * 可设计数量
     */
    private BigDecimal ksjsl;

    /**
     * 累计计量比例
     */
    private String ljjlbl;

    /**
     * 本期计量金额 = 本期计量数量 * 合同单价
     */
    private BigDecimal bqjlje;

    /**
     * 累计计量金额 = 累计数量 * 合同单价
     */
    private BigDecimal ljjlje;



}
