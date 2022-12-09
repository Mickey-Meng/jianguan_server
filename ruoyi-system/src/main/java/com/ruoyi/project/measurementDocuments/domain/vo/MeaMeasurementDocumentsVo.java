package com.ruoyi.project.measurementDocuments.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.project.measurementDocumentsDetail.domain.vo.MeaMeasurementDocumentsDetailVo;
import lombok.Data;
import java.util.Date;
import java.util.List;


/**
 * 计量凭证，设计计量、变更计量共用一张凭证，明细分开。视图对象 mea_measurement_documents
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@ExcelIgnoreUnannotated
public class MeaMeasurementDocumentsVo {

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
     * 计量期次编号
     */
    @ExcelProperty(value = "计量期次编号")
    private String jlqsbh;

    /**
     * 台账分解编号
     */
    @ExcelProperty(value = "台账分解编号")
    private String tzfjbh;

    /**
     * 凭证编号
     */
    @ExcelProperty(value = "凭证编号")
    private String pzbh;

    /**
     * 计量类型
     */
    @ExcelProperty(value = "计量类型")
    private String jllx;

    /**
     * 计量日期
     */
    @ExcelProperty(value = "计量日期")
    private Date jlrq;

    /**
     * 交工证书/变更令编号
     */
    @ExcelProperty(value = "交工证书/变更令编号")
    private String jgzs;

    /**
     * 工程部位
     */
    @ExcelProperty(value = "工程部位")
    private String gcbw;

    /**
     * 计算式
     */
    @ExcelProperty(value = "计算式")
    private String jss;

    /**
     * 计量比例
     */
    @ExcelProperty(value = "计量比例")
    private String jlbl;

    /**
     * 附件地址
     */
    @ExcelProperty(value = "附件地址")
    private String fj;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "data_status")
    private String status;


    private List<MeaMeasurementDocumentsDetailVo> detailBos;


}
