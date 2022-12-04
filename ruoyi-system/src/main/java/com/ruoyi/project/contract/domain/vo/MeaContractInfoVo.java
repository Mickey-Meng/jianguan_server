package com.ruoyi.project.contract.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;



/**
 * 合同条款视图对象 mea_contract_info
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaContractInfoVo {

    private static final long serialVersionUID = 1L;


    @ExcelProperty(value = "id")
    private String id;


    @ExcelProperty(value = "项目名称")
    private String xmmc;


    /**
     * 开工日期
     */
    @ExcelProperty(value = "开工日期")
    private Date kgrq;

    /**
     *
     */
    @ExcelProperty(value = "竣工日期")
    private Date jgrq;

    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    private String bdbh;


    /**
     * 合同编号
     */
    @ExcelProperty(value = "合同编号")
    private String htbh;

    /**
     * 合同总金额
     */
    @ExcelProperty(value = "合同总金额")
    private BigDecimal htzje;

    /**
     * 合同段
     */
    @ExcelProperty(value = "合同段")
    private String htd;

    /**
     * 工程量清单金额
     */
    @ExcelProperty(value = "工程量清单金额")
    private BigDecimal gclqdje;

    /**
     * 开工预付款金额
     */
    @ExcelProperty(value = "开工预付款金额")
    private BigDecimal kgyfkje;

    /**
     * 暂列金金额
     */
    @ExcelProperty(value = "暂列金金额")
    private BigDecimal zljje;

    /**
     * 合同工期)
     */
    @ExcelProperty(value = "合同工期)")
    private String htgq;

    /**
     * 开工预付款起扣比例
     */
    @ExcelProperty(value = "开工预付款起扣比例")
    private BigDecimal kgyfkqkbl;

    /**
     * 开工预付款截止比例
     */
    @ExcelProperty(value = "开工预付款截止比例")
    private BigDecimal kgyfkjzbl;

    /**
     * 质保金扣款比例
     */
    @ExcelProperty(value = "质保金扣款比例")
    private BigDecimal zbjkkbl;

    /**
     * 农民工工资保证金扣款比例
     */
    @ExcelProperty(value = "农民工工资保证金扣款比例")
    private BigDecimal nmggzbzjkkbl;

    /**
     * 质保金总额
     */
    @ExcelProperty(value = "质保金总额")
    private BigDecimal zbjze;

    /**
     * 计日工金额
     */
    @ExcelProperty(value = "计日工金额")
    private BigDecimal jrgje;

    /**
     * 标段长度(km)
     */
    @ExcelProperty(value = "标段长度(km)")
    private BigDecimal bdcd;

    /**
     * 起讫桩号
     */
    @ExcelProperty(value = "起讫桩号")
    private String qqzh;

    /**
     * 开工预付款支付情况
     */
    @ExcelProperty(value = "开工预付款支付情况")
    private String kgyjkzfqk;

    /**
     * 开工预付款扣回规定
     */
    @ExcelProperty(value = "开工预付款扣回规定")
    private String kgyfkkhgd;

    /**
     * 材料预付款支付情况
     */
    @ExcelProperty(value = "材料预付款支付情况")
    private String clykfzfqk;

    /**
     * 材料预付款扣回规定
     */
    @ExcelProperty(value = "材料预付款扣回规定")
    private String clyfkkhgd;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;


}
