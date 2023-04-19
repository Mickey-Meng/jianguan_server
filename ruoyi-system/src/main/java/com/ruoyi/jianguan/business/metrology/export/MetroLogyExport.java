package com.ruoyi.jianguan.business.metrology.export;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 计量台账-导出实体
 *
 * @author G.X.L
 * @since 1.0
 * @date 2023-04-05
 */
@Data
public class MetroLogyExport {

    /**
     * 计量期数名称
     */
    @ApiModelProperty(value = "计量期数名称")
    private String metrologyName;

    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    private LocalDateTime startDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期")
    private LocalDateTime endDate;

    /**
     * 计量编号
     */
    @ApiModelProperty(value = "计量编号")
    private String metrologyNo;

    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    private LocalDateTime applyDate;

    /**
     * 申请单位
     */
    @ApiModelProperty(value = "申请单位")
    private String applyUnit;

    /**
     * 计量金额
     */
    @ApiModelProperty(value = "计量金额")
    private BigDecimal amount;

    /**
     * id
     */
    @ApiModelProperty(value = "计量内容")
    private String content;

    /**
     * 申请依据
     */
    @ApiModelProperty(value = "申请依据")
    private String applyCertificate;

    /**
     * 审批状态：reject：驳回（数据可编辑状态,其他状态不可编辑）, pending: 待审批, approving: 审批中, approved: 审批完成
     */
    @ApiModelProperty(value = "审批状态：reject：驳回（数据可编辑状态,其他状态不可编辑）, pending: 待审批, approving: 审批中, approved: 审批完成")
    private String auditStatus;

}
