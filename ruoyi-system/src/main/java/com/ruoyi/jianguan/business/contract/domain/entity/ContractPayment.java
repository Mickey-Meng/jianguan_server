package com.ruoyi.jianguan.business.contract.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 合同付款对象 zz_contract_payment
 *
 * @author mickey
 * @date 2023-06-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_contract_payment")
@ApiModel(value = "合同付款对象", description = "合同付款表")
public class ContractPayment extends NewBaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 款项类型
     */
    private String type;
    /**
     * 款项金额
     */
    private BigDecimal amount;
    /**
     * 填报日期
     */
    private Date recordTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 附件
     */
    private String attachment;
    /**
     * 状态 0 审批中 1 审批完成 2 驳回
     */
    private Integer status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;

}