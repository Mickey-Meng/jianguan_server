package com.ruoyi.project.bill.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 工程量清单视图对象 mea_contract_bill
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaContractBillVo {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private String id;


    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    private String bdbh;

    /**
     * 子目号
     */
    @ExcelProperty(value = "子目号")
    private String zmh;

    /**
     * 子目名称
     */
    @ExcelProperty(value = "子目名称")
    private String zmmc;

    /**
     * 子目号父节点
     */
    @ExcelProperty(value = "子目号父节点")
    private String zmhParent;

    /**
     * 子目号祖级列表
     */
    @ExcelProperty(value = "子目号祖级列表")
    private String zmhAncestors;

    /**
     * 单位
     */
    @ExcelProperty(value = "单位")
    private String dw;

    /**
     * 合同单价
     */
    @ExcelProperty(value = "合同单价")
    private BigDecimal htdj;

    /**
     * 新增单价
     */
    @ExcelProperty(value = "新增单价")
    private BigDecimal xzdj;

    /**
     * 合同数量
     */
    @ExcelProperty(value = "合同数量")
    private BigDecimal htsl;

    /**
     * 合同金额
     */
    @ExcelProperty(value = "合同金额")
    private BigDecimal htje;

    /**
     * 审核数量
     */
    @ExcelProperty(value = "审核数量")
    private BigDecimal shsl;

    /**
     * 审核金额
     */
    @ExcelProperty(value = "审核金额")
    private BigDecimal shje;

    /**
     * 修正数量
     */
    @ExcelProperty(value = "修正数量")
    private BigDecimal xzsl;

    /**
     * 修正金额
     */
    @ExcelProperty(value = "修正金额")
    private BigDecimal xzje;

    /**
     * 总数量
     */
    @ExcelProperty(value = "总数量")
    private BigDecimal zsl;

    /**
     * 总金额
     */
    @ExcelProperty(value = "总金额")
    private BigDecimal zje;

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

    /**
     * 树id
     */
    @ExcelProperty(value = "树id")
    private String parentId;


}
