package com.ruoyi.ql.domain.vo;


import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * 付款与入库关系视图对象 ql_receivable_outbound_rel
 *
 * @author ruoyi
 * @date 2023-05-11
 */
@Data
@ExcelIgnoreUnannotated
@ApiModel(value = "付款与入库关系", description = "付款与入库关系VO")
public class QlReceivableOutboundRelVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 收款单ID
     */
    @ExcelProperty(value = "收款单ID")
    @ApiModelProperty(value = "收款单ID", required = true)
    private Long receivableId;

    /**
     * 出库单编号
     */
    @ExcelProperty(value = "出库单编号")
    @ApiModelProperty(value = "出库单编号", required = true)
    private String outboundCode;

    /**
     * 本次收款金额
     */
    @ExcelProperty(value = "本次收款金额")
    @ApiModelProperty(value = "本次收款金额", required = true)
    private BigDecimal amount;

    /**
     * 客户姓名
     */
    @ExcelProperty(value = "客户姓名")
    @ApiModelProperty(value = "客户姓名", required = true)
    private String customerName;

    /**
     * 销售合同编码
     */
    @ExcelProperty(value = "销售合同编码")
    @ApiModelProperty(value = "销售合同编码", required = true)
    private String saleContractCode;


}