package com.ruoyi.ql.domain.export;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.ql.domain.vo.QlWarehousingDetailVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 入库管理视图对象 ql_warehousing
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Data
public class WarehousingExportVo {

    private static final long serialVersionUID = 1L;


    /**
     * 入库单号
     */
    @ExcelProperty(value = "入库单号")
    private String warehousingCode;

    /**
     * 采购员
     */
    @ExcelProperty(value = "采购员")
    private String purchaser;

    /**
     * 采购合同编码
     */
    @ExcelProperty(value = "采购合同编码")
    private String contractCode;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;

    /**
     * 供应商地址
     */
    @ExcelProperty(value = "供应商地址")
    private String address;

    /**
     * 供应商电话
     */
    @ExcelProperty(value = "供应商电话")
    private String mobilePhone;

    /**
     * 到货日期
     */
    @ExcelProperty(value = "到货日期")
    private Date arrivalDate;

    /**
     * 最后付款日期
     */
    @ExcelProperty(value = "最后付款日期")
    private Date lastPaymentDate;

    /**
     * 入库对接人
     */
    @ExcelProperty(value = "入库对接人")
    private String warehousingUsername;

    /**
     * 入库复核人
     */
    @ExcelProperty(value = "入库复核人")
    private String warehousingReleaseuser;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String goodsName;

    /**
     * 规格
     */
    @ExcelProperty(value = "规格")
    private String goodsSearchstandard;

    /**
     * 单位
     */
    @ExcelProperty(value = "单位")
    private String goodsUnit;

    /**
     * 库存数量
     */
    @ExcelProperty(value = "库存数量")
    private BigDecimal  inventoryNumber;

    /**
     * 基准价
     */
    @ExcelProperty(value = "基准价")
    private BigDecimal basePrice;

    /**
     * 进货价
     */
    @ExcelProperty(value = "进货价")
    private BigDecimal incomePrice;

    /**
     * 附加价格
     */
    @ExcelProperty(value = "附加价格")
    private BigDecimal extraPrice;

    /**
     * 采购金额
     */
    @ExcelProperty(value = "采购金额")
    private BigDecimal amount;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

}

