package com.ruoyi.ql.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 出库管理视图对象 ql_outbound
 *
 * @author ruoyi
 * @date 2023-05-05
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "出库管理", description = "出库管理VO")
public class QlOutboundVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 出库单号
     */
    @ExcelProperty(value = "出库单号")
    @ApiModelProperty(value = "出库单号", required = true)
    private String outboundCode;

    /**
     * 出库日期
     */
    @ExcelProperty(value = "出库日期")
    @ApiModelProperty(value = "出库日期", required = true)
    private Date outboundDate;

    /**
     * 销售员
     */
    @ExcelProperty(value = "销售员")
    @ApiModelProperty(value = "销售员", required = true)
    private String salesperson;

    /**
     * 销售合同编号
     */
    @ExcelProperty(value = "销售合同编号")
    @ApiModelProperty(value = "销售合同编号", required = true)
    private String saleContractCode;

    /**
     * 采购合同编号
     */
    @ExcelProperty(value = "采购合同编号")
    @ApiModelProperty(value = "采购合同编号", required = true)
    private String purchaseContractCode;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    @ApiModelProperty(value = "客户名称", required = true)
    private String customerName;

    /**
     * 电话
     */
    @ExcelProperty(value = "电话")
    @ApiModelProperty(value = "电话", required = true)
    private String telephone;

    /**
     * 地址描述
     */
    @ExcelProperty(value = "地址描述")
    @ApiModelProperty(value = "地址描述", required = true)
    private String address;

    /**
     * 产品id
     */
    @ExcelProperty(value = "产品id")
    @ApiModelProperty(value = "产品id", required = true)
    private String proudctId;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    @ApiModelProperty(value = "产品名称", required = true)
    private String proudctName;

    /**
     * 商品规格
     */
    @ExcelProperty(value = "商品规格")
    @ApiModelProperty(value = "商品规格", required = true)
    private String goodsSearchstandard;

    /**
     * 商品单位【关联字典管理】
     */
    @ExcelProperty(value = "商品单位【关联字典管理】")
    @ApiModelProperty(value = "商品单位【关联字典管理】", required = true)
    private String goodsUnit;

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
     * 附件--销售基准价截图
     */
    @ExcelProperty(value = "附件--销售基准价截图")
    @ApiModelProperty(value = "附件--销售基准价截图", required = true)
    private String fj;

    /**
     * 销售日期
     */
    @ExcelProperty(value = "销售日期")
    @ApiModelProperty(value = "销售日期", required = true)
    private Date saleDate;

    /**
     * 销售数量
     */
    @ExcelProperty(value = "销售数量")
    @ApiModelProperty(value = "销售数量", required = true)
    private BigDecimal saleNumber;

    /**
     * 销售金额
     */
    @ExcelProperty(value = "销售金额")
    @ApiModelProperty(value = "销售金额", required = true)
    private BigDecimal saleAmount;

    /**
     * 出库对接人
     */
    @ExcelProperty(value = "出库对接人")
    @ApiModelProperty(value = "出库对接人", required = true)
    private String outboundUsername;

    /**
     * 出库审核人
     */
    @ExcelProperty(value = "出库审核人")
    @ApiModelProperty(value = "出库审核人", required = true)
    private String outboundReleaseuser;

    /**
     * 出库数量
     */
    @ExcelProperty(value = "出库数量")
    @ApiModelProperty(value = "出库数量", required = true)
    private BigDecimal outboundNumber;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 部门ID
     */
    @ExcelProperty(value = "部门ID")
    @ApiModelProperty(value = "部门ID", required = true)
    private Long deptId;


}
