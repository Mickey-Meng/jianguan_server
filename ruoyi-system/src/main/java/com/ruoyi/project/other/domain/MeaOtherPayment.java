package com.ruoyi.project.other.domain;

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
 * 其他款项对象 mea_other_payment
 *
 * @author ruoyi
 * @date 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mea_other_payment")
public class MeaOtherPayment extends BaseEntity {

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
     * 计量期次
     */
    private String jlqsbh;
    /**
     * 申请编号
     */
    private String sqbh;
    /**
     * 申请日期
     */
    private Date sqsj;
    /**
     * 所属单位
     */
    private String ssdw;
    /**
     * 款项类型
     */
    private String kxlx;
    /**
     * 款项金额
     */
    private BigDecimal kxje;
    /**
     * 附件
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
