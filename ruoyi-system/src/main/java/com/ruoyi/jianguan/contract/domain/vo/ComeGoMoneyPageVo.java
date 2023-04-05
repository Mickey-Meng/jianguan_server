package com.ruoyi.jianguan.contract.domain.vo;

import com.ruoyi.common.core.domain.vo.NewBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 往来款列表返回VO
 *
 * @author qiaoxulin
 * @date : 2022/5/27 18:00
 */
@Data
@ApiModel(value = "BuildTechBottomPageVo", description = "往来款列表返回VO")
public class ComeGoMoneyPageVo extends NewBaseVo {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;


    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;

    /**
     * 施工标段名称
     */
    @ApiModelProperty(value = "施工标段名称")
    private String buildSectionName;


    /**
     * 付款单位
     */
    @ApiModelProperty(value = "付款单位")
    private String payUnit;

    /**
     * 账单编号
     */
    @ApiModelProperty(value = "账单编号")
    private String billCode;

    /**
     * 收款单位
     */
    @ApiModelProperty(value = "收款单位")
    private String gatherUnit;


    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;


    /**
     * 支付日期
     */
    @ApiModelProperty(value = "支付日期")
    private LocalDate payDate;

    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private Integer status;

    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private String statusStr;
}
