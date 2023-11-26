package com.ruoyi.ql.domain;

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
 * 合同管理对象 ql_contract_info_sale
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_contract_info_sale")
public class QlContractInfoSale extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 合同id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 合同编码
     */
    private String contractCode;
    /**
     * 合同名称
     */
    private String contractName;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 总金额
     */
    private BigDecimal amount;
    /**
     * 质保金
     */
    private BigDecimal retentionAmount;
    /**
     * 合同签订时间
     */
    private Date contactDate;
    /**
     * 质保金到期时间
     */
    private Date retentionDate;
    /**
     * 税率
     */
    private BigDecimal rate;
    /**
     * 发货地
     */
    private String area;
    /**
     * 1已签订 0未签订
     */
    private String contractStatus;
    /**
     * 附件
     */
    private String fj;
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
     * 供应商联系人
     */
    private String contactPerson;
    /**
     * 供应商联系方式
     */
    private String mobilePhone;
    /**
     * 采购人员
     */
    private String purchaser;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 账期
     */
    private Long accountPeriod;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目id
     */
    private String projectId;
}
