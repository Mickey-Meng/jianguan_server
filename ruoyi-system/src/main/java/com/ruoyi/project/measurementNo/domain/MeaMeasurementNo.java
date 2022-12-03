package com.ruoyi.project.measurementNo.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 中间计量期数管理对象 mea_measurement_no
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_measurement_no")
public class MeaMeasurementNo extends BaseEntity {

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
     * 计量期数编号
     */
    private String jlqsbh;
    /**
     * 计量期数文字表达
     */
    private String jlqs;
    /**
     * 开始日期
     */
    private Date ksrq;
    /**
     * 结束日期
     */
    private Date jsrq;
    /**
     * 默认日期
     */
    private Date mrrq;
    /**
     * 报表编号
     */
    private String bbbh;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
