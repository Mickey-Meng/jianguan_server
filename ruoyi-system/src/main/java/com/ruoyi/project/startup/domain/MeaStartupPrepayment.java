package com.ruoyi.project.startup.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 开工预付款对象 mea_startup_prepayment
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_startup_prepayment")
public class MeaStartupPrepayment extends BaseEntity {

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
     * 开工预付款申请编号
     */
    private String sqbh;
    /**
     * 申请日期
     */
    private Date sqsj;
    /**
     * 申请类型
     */
    private String sqlx;
    /**
     * 申请次数
     */
    private Long sqcs;
    /**
     * 预付款金额
     */
    private BigDecimal yukje;
    /**
     * 申请依据
     */
    private String sqyj;
    /**
     * 附件地址
     */
    private String fj;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

}
