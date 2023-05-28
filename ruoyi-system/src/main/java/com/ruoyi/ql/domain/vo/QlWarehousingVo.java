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
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


/**
 * 入库管理视图对象 ql_warehousing
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
@ExcelIgnoreUnannotated
public class QlWarehousingVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 入库单号
     */
    @ExcelProperty(value = "入库单号")
    private String warehousingCode;

    /**
     * 入库对接人
     */
    @ExcelProperty(value = "入库对接人")
    private String warehousingUsername;

    /**
     * 入库时间
     */
    @ExcelProperty(value = "入库时间")
    private Date warehousingDate;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String proudctName;

    /**
     * 入库状态（1：已入库 0 未入库）
     */
    @ExcelProperty(value = "入库状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=：已入库,0=,未=入库")
    @ApiModelProperty(value = "入库状态", required = true)
    private String warehousingStatus;
    /**
     * 单价
     */
    @ExcelProperty(value = "单价")
    @ApiModelProperty(value = "单价", required = true)
    private BigDecimal price;

    /**
     * 采购数量
     */
    @ExcelProperty(value = "采购数量")
    @ApiModelProperty(value = "采购数量", required = true)
    private BigDecimal orderNumber;

    /**
     * 采购金额
     */
    @ExcelProperty(value = "采购金额")
    @ApiModelProperty(value = "采购金额", required = true)
    private BigDecimal amount;
    /**
     * 采购合同id
     */
    @ExcelProperty(value = "采购合同id")
    @ApiModelProperty(value = "采购合同id", required = true)
    private String purchaseOrderId;
    /**
     * 入库数量
     */
    @ApiModelProperty(value = "入库数量")
    private BigDecimal warehousingNumber;

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id " )
    private String proudctId;


    /**
     * 到货日期
     */
    @ExcelProperty(value = "到货日期")
    @ApiModelProperty(value = "到货日期", required = true)
    private Date arrivalDate;

    /**
     * 采购员
     */
    @ExcelProperty(value = "采购员")
    @ApiModelProperty(value = "采购员", required = true)
    private String purchaser;

    /**
     * 采购合同id
     */
    @ExcelProperty(value = "采购合同id")
    @ApiModelProperty(value = "采购合同id", required = true)
    private String contractId;

    /**
     * 采购合同编码
     */
    @ExcelProperty(value = "采购合同编码")
    @ApiModelProperty(value = "采购合同编码", required = true)
    private String contractCode;

    /**
     * 供应商Id
     */
    @ExcelProperty(value = "供应商Id")
    @ApiModelProperty(value = "供应商Id", required = true)
    private Long supplierId;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    @ApiModelProperty(value = "供应商名称", required = true)
    private String supplierName;

    /**
     * 供应商电话
     */
    @ExcelProperty(value = "供应商电话")
    @ApiModelProperty(value = "供应商电话", required = true)
    private String mobilePhone;

    /**
     * 供应商地址
     */
    @ExcelProperty(value = "供应商地址")
    @ApiModelProperty(value = "供应商地址", required = true)
    private String address;
    /**
     * 入库复核人
     */
    @ExcelProperty(value = "入库复核人")
    @ApiModelProperty(value = "入库复核人", required = true)
    private String warehousingReleaseuser;

    /**
     * 基准价
     */
    @ExcelProperty(value = "基准价")
    @ApiModelProperty(value = "基准价", required = true)
    private BigDecimal basePrice;

    /**
     * 进货价
     */
    @ExcelProperty(value = "进货价")
    @ApiModelProperty(value = "进货价", required = true)
    private BigDecimal incomePrice;

    /**
     * 附加价格
     */
    @ExcelProperty(value = "附加价格")
    @ApiModelProperty(value = "附加价格", required = true)
    private BigDecimal extraPrice;

    /**
     * 附件--进货基准价截图
     */
    @ExcelProperty(value = "附件--进货基准价截图")
    @ApiModelProperty(value = "附件--进货基准价截图", required = true)
    private String fj;

    /**
     * 进货日期，默认系统当天日期
     */
    @ExcelProperty(value = "进货日期，默认系统当天日期")
    @ApiModelProperty(value = "进货日期，默认系统当天日期", required = true)
    private Date incomeDate;
    /**
     * 最后付款日期
     */
    @ExcelProperty(value = "最后付款日期")
    @ApiModelProperty(value = "最后付款日期", required = true)
    private Date lastPaymentDate;
    /**
     * 规格
     */
    @ExcelProperty(value = "规格")
    @ApiModelProperty(value = "规格", required = true)
    private String goodsSearchstandard;

    /**
     * 单位
     */
    @ExcelProperty(value = "单位")
    @ApiModelProperty(value = "单位", required = true)
    private String goodsUnit;

    /**
     * 入库单明细表
     */
    private List<QlWarehousingDetailVo> warehousingDetails;


    /**
     * 备注
     */
    private String remark;
}
