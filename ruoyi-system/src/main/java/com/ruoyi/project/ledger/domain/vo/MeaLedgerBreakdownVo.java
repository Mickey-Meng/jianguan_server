package com.ruoyi.project.ledger.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 台账分解视图对象 mea_ledger_breakdown
 *
 * @author ruoyi
 * @date 2022-12-07
 */
@Data
@ExcelIgnoreUnannotated
public class MeaLedgerBreakdownVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 标段编号
     */
    @ExcelProperty(value = "标段编号")
    private String bdbh;

    /**
     * 台账分解编号
     */
    @ExcelProperty(value = "台账分解编号")
    private String tzfjbh;

    /**
     * 台账分解编号父节点
     */
    @ExcelProperty(value = "台账分解编号父节点")
    private String tzfjbhParent;

/*    *//**
     * 台账分解编号祖级列表
     */
    @ExcelProperty(value = "台账分解编号祖级列表")
    private String tzfjbhAncestors;

    /**
     * 台账分解名称
     */
    @ExcelProperty(value = "台账分解名称")
    private String tzfjmc;

    /**
     * 分解类型（0正常 1变更）
     */
    @ExcelProperty(value = "分解类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "change_status")
    private String fjlx;

    /**
     * 状态（0正常 1停用）
     */
 /*   @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;
*/
    /**
     * 备注
     */
 /*   @ExcelProperty(value = "备注")
    private String remark;*/

    /**
     * 树id
     */
    @ExcelProperty(value = "树id")
    private Long parentId;
    /**
     *  '数据是否是变更获取（1 变更数据 0正常合同）',
     */
    @ExcelProperty(value = " '数据是否是变更获取", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=,变=更数据,0=正常合同")
    @ApiModelProperty(value = " '数据是否是变更获取", required = true)
    private String isChange;

}
