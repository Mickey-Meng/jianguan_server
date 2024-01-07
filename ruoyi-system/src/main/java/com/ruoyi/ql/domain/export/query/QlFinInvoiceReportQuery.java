package com.ruoyi.ql.domain.export.query;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.ql.domain.bo.QlInvoiceItemBo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 供应商开票业务对象 ql_fin_invoice
 *
 * @author ruoyi
 * @date 2022-12-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QlFinInvoiceReportQuery extends BaseReportQueryEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 合同id
     */
    private String contractId;
    /**
     * 合同编号
     */
    @NotBlank(message = "合同编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contractCode;
    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 供应商id
     */
    @NotBlank(message = "供应商id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierId;

    /**
     * 本次开票金额
     */
    @NotNull(message = "本次开票金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal invoiceAmount;

    /**
     * 欠开票金额
     */
    private BigDecimal uninvoice;

    /**
     * 开票时间
     */
    @NotNull(message = "开票时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date invoiceDate;

    /**
     *
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;
    /**
     * 发票编号
     */
//    @NotBlank(message = "发票编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String invoiceNo;
    /**
     * 附件
     */
    private String fj;
    /**
     * 折扣
     */
    @ExcelProperty(value = "折扣")
    @ApiModelProperty(value = "折扣", required = true)
    private String discount;

    private List<QlInvoiceItemBo> invoiceItems;
}
