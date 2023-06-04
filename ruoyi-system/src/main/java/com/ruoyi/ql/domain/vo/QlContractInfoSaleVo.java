package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.ql.domain.bo.QlContractGoodsRelBo;
import io.swagger.annotations.ApiModelProperty;
import liquibase.pro.packaged.S;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


/**
 * 合同管理视图对象 ql_contract_info_sale
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlContractInfoSaleVo {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
    @ExcelProperty(value = "合同id")
    private Long id;

    /**
     * 合同编码
     */
    @ExcelProperty(value = "合同编码")
    private String contractCode;

    /**
     * 合同名称
     */
    @ExcelProperty(value = "合同名称")
    private String contractName;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

    private String customerId;
    /**
     * 总金额
     */
    @ExcelProperty(value = "总金额")
    private BigDecimal amount;
    private String fj;

    @ExcelProperty(value = "质保金")
    private BigDecimal retentionAmount;


    @ExcelProperty(value = "质保金到期时间")
    private Date retentionDate;

    @ExcelProperty(value = "税率")
    private BigDecimal rate;

    @ExcelProperty(value = "发货地不能为空")
    private String area;
    /**
     * 合同签订时间
     */
    @ExcelProperty(value = "合同签订时间")
    private Date contactDate;

    /**
     * 1已签订 0未签订
     */
    @ExcelProperty(value = "1已签订 0未签订")
    private String contractStatus;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;

    /**
     * 供应商联系人
     */
    @ExcelProperty(value = "供应商联系人")
    @ApiModelProperty(value = "供应商联系人", required = true)
    private String contactPerson;

    /**
     * 供应商联系方式
     */
    @ExcelProperty(value = "供应商联系方式")
    @ApiModelProperty(value = "供应商联系方式", required = true)
    private String mobilePhone;

    /**
     * 采购人员
     */
    @ExcelProperty(value = "采购人员")
    @ApiModelProperty(value = "采购人员", required = true)
    private String purchaser;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    @ApiModelProperty(value = "开始时间", required = true)
    private Date startDate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    @ApiModelProperty(value = "结束时间", required = true)
    private Date endDate;

    /**
     * 销售合同与商品关系
     */
    @ApiModelProperty(value = "销售合同与商品关系", required = true)
    private List<QlContractGoodsRelVo> contractGoodsRels;

    /**
     * 账期
     */
    private Long accountPeriod;

}
