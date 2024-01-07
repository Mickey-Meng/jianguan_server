package com.ruoyi.ql.domain.export;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.ql.domain.bo.QlContractGoodsRelBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 合同管理业务对象 ql_contract_info_sale
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@ExcelIgnoreUnannotated
public class QlContractInfoSaleExport{

    /**
     * 合同编码
     */
    private String contractCode;
    /**
     * 合同名称
     */
    private String contractName;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 客户名称
     */
    private String customerName;
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
     * 账期
     */
    private Long accountPeriod;
    /**
     * 商品名称
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


}
