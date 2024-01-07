package com.ruoyi.ql.domain.export;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出库单excel导出
 *
 * @author bx 2023/5/10
 */
@Data
@ExcelIgnoreUnannotated
public class OutboundExport {

    /**
     * 出库单号
     */
    @ExcelProperty(value = "单据编号")
    private String outboundCode;

    /**
     * 项目名称
     */
    @ExcelProperty(value = "项目名称")
    private String projectName;
    /**
     *
     */
    @ExcelProperty(value = "出货日期")
    private Date outboundDate;
    /**
     *
     */
    @ExcelProperty(value = "销售员")
    private String salesperson;
    /**
     *
     */
    @ExcelProperty(value = "销售合同编号")
    private String saleContractCode;
    /**
     *
     */
    @ExcelProperty(value = "采购合同编号")
    private String purchaseContractCode;
    /**
     *
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;
    /**
     *
     */
    @ExcelProperty(value = "客户电话")
    private String telephone;
    /**
     *
     */
    @ExcelProperty(value = "客户地址")
    private String address;
    /**
     *
     */
    @ExcelProperty(value = "销售日期")
    private Date saleDate;
    /**
     *
     */
    @ExcelProperty(value = "销售金额")
    private BigDecimal saleAmount;
    /**
     *
     */
    @ExcelProperty(value = "出库对接人")
    private String outboundUsername;
    /**
     *
     */
    @ExcelProperty(value = "出库审核人")
    private String outboundReleaseuser;
    /**
     *
     */
    @ExcelProperty(value = "产品名称")
    private String goodsName;
    /**
     *
     */
    @ExcelProperty(value = "产品规格")
    private String goodsSearchstandard;
    /**
     *
     */
    @ExcelProperty(value = "产品单位")
    private String goodsUnit;
    /**
     *
     */
    @ExcelProperty(value = "销售数量")
    private String basePrice;
    /**
     *
     */
    @ExcelProperty(value = "基准价")
    private BigDecimal inventoryNumber;
    /**
     *
     */
    @ExcelProperty(value = "销售价")
    private BigDecimal salePrice;
    /**
     *
     */
    @ExcelProperty(value = "附加价格")
    private BigDecimal extraPrice;
    /**
     *
     */
    @ExcelProperty(value = "总价")
    private BigDecimal amount;
    /**
     *
     */
    @ExcelProperty(value = "备注")
    private String remark;

}
