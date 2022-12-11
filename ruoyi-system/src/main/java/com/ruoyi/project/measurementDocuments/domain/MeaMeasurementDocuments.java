package com.ruoyi.project.measurementDocuments.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.project.measurementDocumentsDetail.domain.MeaMeasurementDocumentsDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 计量凭证，设计计量、变更计量共用一张凭证，明细分开。对象 mea_measurement_documents
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_measurement_documents")
public class MeaMeasurementDocuments extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private String id;
    /**
     * 标段编号
     */
    private String bdbh;
    /**
     * 计量期次编号
     */
    private String jlqsbh;
    /**
     * 台账分解编号
     */
    private String tzfjbh;
    /**
     * 凭证编号
     */
    private String pzbh;
    /**
     * 计量类型
     */
    private String jllx;
    /**
     * 计量日期
     */
    private Date jlrq;
    /**
     * 交工证书/变更令编号
     */
    private String jgzs;
    /**
     * 工程部位
     */
    private String gcbw;
    /**
     * 计算式
     */
    private String jss;
    /**
     * 计量比例
     */
    private String jlbl;
    /**
     * 附件地址
     */
    private String fj;
    /**
     * 状态（0正常 1停用）
     */
    private String status;


    private String reviewCode;
    /**
     * 备注
     */
    private String remark;

    // add by yangaogao 20221207  添加计量明细
//    List<MeaMeasurementDocumentsDetail> meaMeasurementDocumentsDetailList;

}
