package com.ruoyi.ql.domain.importvo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购合同 对象 ql_contract_info_purchase
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ql_contract_info_purchase")
public class QlContractInfoPurchaseImport extends BaseEntity {

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
     * 供应商名称
     */
    private String supplierName;
    /**
     * 供应商id
     */
    private String supplierId;/**
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
     * 总金额
     */
    private BigDecimal amount;
    /**
     * 合同签订时间
     */
    private Date contactDate;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 税率
     */
    private BigDecimal rate;
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
     * 账期
     */
    private Long accountPeriod;

    /**
     * 产品名称
     */
    private String goodsName;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private String goodsNum;

    /**
     * 美元总额
     */
    private BigDecimal totalAmountDollar;

}
