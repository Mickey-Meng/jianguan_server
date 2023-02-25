package com.ruoyi.ql.domain;

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
 * 出库管理对象 ql_outbound
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_outbound")
public class QlOutbound extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 出库单号
     */
    private String outboundCode;
    /**
     * 出库对接人
     */
    private String outboundUsername;
    /**
     * 产品id
     */
    private String proudctId;
    /**
     * 出库数量
     */
    private BigDecimal outboundNumber;
    /**
     * 出库时间
     */
    private Date outboundDate;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     *
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 产品名称
     */
    private String proudctName;

}
