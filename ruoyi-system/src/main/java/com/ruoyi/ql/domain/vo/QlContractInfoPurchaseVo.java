package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.ql.domain.bo.QlWarehousingBo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;


/**
 * 采购合同 视图对象 ql_contract_info_purchase
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlContractInfoPurchaseVo {

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
    private String fj;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;
    /**
     * 供应商id
     */
    @ExcelProperty(value = "供应商id")
    @ApiModelProperty(value = "供应商id", required = true)
    private String supplierId;

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
    @ApiModelProperty(value = "供应商联系方式" )
    private String mobilePhone;

    /**
     * 采购人员
     */
    @ExcelProperty(value = "采购人员")
    @ApiModelProperty(value = "采购人员" )
    private String purchaser;

    /**
     * 总金额
     */
    @ExcelProperty(value = "总金额")
    @ApiModelProperty(value = "总金额", required = true)
    private BigDecimal amount;

    /**
     * 合同签订时间
     */
    @ExcelProperty(value = "合同签订时间")
    @ApiModelProperty(value = "合同签订时间" )
    private Date contactDate;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    @ApiModelProperty(value = "开始时间" )
    private Date startDate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    @ApiModelProperty(value = "结束时间" )
    private Date endDate;

    @ExcelProperty(value = "税率")
    private BigDecimal rate;

    @ExcelProperty(value = "合同状态")
    private String contractStatus;

    /**
     * 销售合同与商品关系
     */
    @ExcelProperty(value = "销售合同与商品关系")
    @ApiModelProperty(value = "销售合同与商品关系", required = true)
    private List<QlContractGoodsRelVo> contractGoodsRels;

    /**
     * 供应商信息
     */
    private QlBasisSupplierVo qlBasisSupplierVo;

    /**
     * 账期
     */
    @ExcelProperty(value = "账期")
    @ApiModelProperty(value = "账期", required = true)
    private Long accountPeriod;


    /**
     * 备注
     */
    private String remark;

}
