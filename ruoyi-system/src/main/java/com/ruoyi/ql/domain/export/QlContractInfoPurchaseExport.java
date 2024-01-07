package com.ruoyi.ql.domain.export;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购合同 对象 ql_contract_info_purchase
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlContractInfoPurchaseExport{

    private static final long serialVersionUID=1L;

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
     * 账期
     */
    private Long accountPeriod;
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
     * 1已签订 0未签订
     */
    private String contractStatus;
    /**
     * 合同签订时间
     */
    private Date contactDate;
    /**
     * 税率
     */
    private BigDecimal rate;
    /**
     *
     */
    private String remark;
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
