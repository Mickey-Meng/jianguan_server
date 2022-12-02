package com.ruoyi.system.domain.vo;

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
 * 合同条款视图对象 contract_info
 *
 * @author ruoyi
 * @date 2022-12-02
 */
@Data
@ExcelIgnoreUnannotated
public class ContractInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    private String BDBH;

    /**
     * 开工日期,YYYY-MM-DD
     */
    @ExcelProperty(value = "开工日期,YYYY-MM-DD")
    private Date KGRQ;

    /**
     * 竣工日期,YYYY-MM-DD
     */
    @ExcelProperty(value = "竣工日期,YYYY-MM-DD")
    private Date JGRQ;

    /**
     * 项目名称
     */
    @ExcelProperty(value = "项目名称")
    private String XMMC;

    /**
     * 合同编号
     */
    @ExcelProperty(value = "合同编号")
    private String HTBH;

    /**
     * 合同总金额
     */
    @ExcelProperty(value = "合同总金额")
    private BigDecimal HTZJE;

    /**
     * 合同段
     */
    @ExcelProperty(value = "合同段")
    private String HTD;

    /**
     * 工程量清单金额
     */
    @ExcelProperty(value = "工程量清单金额")
    private BigDecimal GCLQDJE;

    /**
     * 开工预付款金额
     */
    @ExcelProperty(value = "开工预付款金额")
    private BigDecimal KGYFKJE;

    /**
     * 暂列金金额（元）
     */
    @ExcelProperty(value = "暂列金金额", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "元=")
    private BigDecimal ZLJJE;

    /**
     * 合同工期 (月/天  24/730)
     */
    @ExcelProperty(value = "合同工期 (月/天  24/730)")
    private String HTGQ;

    /**
     * 开工预付款起扣比例（%）
     */
    @ExcelProperty(value = "开工预付款起扣比例", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "%=")
    private BigDecimal KGYFKQKBL;

    /**
     * 开工预付款截止比例（%）
     */
    @ExcelProperty(value = "开工预付款截止比例", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "%=")
    private BigDecimal KGYFKJZBL;

    /**
     * 质保金扣款比例（%）
     */
    @ExcelProperty(value = "质保金扣款比例", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "%=")
    private BigDecimal ZBJKKBL;

    /**
     * 农民工工资保证金扣款比例（（%）
     */
    @ExcelProperty(value = "农民工工资保证金扣款比例", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "（=%")
    private BigDecimal NMGGZBZJKKBL;

    /**
     * 质保金总额（元）
     */
    @ExcelProperty(value = "质保金总额", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "元=")
    private BigDecimal ZBJZE;

    /**
     * 计日工金额（元）
     */
    @ExcelProperty(value = "计日工金额", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "元=")
    private BigDecimal JRGJE;

    /**
     * 标段长度（KM）
     */
    @ExcelProperty(value = "标段长度", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "K=M")
    private BigDecimal BDCD;

    /**
     * 起讫桩号
     */
    @ExcelProperty(value = "起讫桩号")
    private String QQZH;

    /**
     * 开工预付款支付情况
     */
    @ExcelProperty(value = "开工预付款支付情况")
    private String KGYJKZFQK;

    /**
     * 开工预付款扣回规定
     */
    @ExcelProperty(value = "开工预付款扣回规定")
    private String KGYFKKHGD;

    /**
     * 材料预付款支付情况
     */
    @ExcelProperty(value = "材料预付款支付情况")
    private String CLYKFZFQK;

    /**
     * 材料预付款扣回规定
     */
    @ExcelProperty(value = "材料预付款扣回规定")
    private String CLYFKKHGD;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
