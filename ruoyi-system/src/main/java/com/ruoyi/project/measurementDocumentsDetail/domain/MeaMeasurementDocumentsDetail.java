package com.ruoyi.project.measurementDocumentsDetail.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 台账变更/工程变更 明细对象 mea_measurement_documents_detail
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_measurement_documents_detail")
public class MeaMeasurementDocumentsDetail extends BaseEntity {

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
     * 凭证编号
     */
    private String pzbh;
    /**
     * 子目号
     */
    private String zmh;
    /**
     * 本期计量数量
     */
    private BigDecimal bqjlsl;
    /**
     * 计量类型
     */
    private String jllx;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 台账分解明细ID
     */
    private String meaLedgerBreakdownDetailId;

}
