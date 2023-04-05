package com.ruoyi.jianguan.contract.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import com.ruoyi.common.core.domain.entity.FileModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 往来款保存dto
 *
 * @author qiaoxulin
 * @date : 2022/5/27 17:46
 */
@Data
@ApiModel(value = "ComeGoMoneySaveDTO", description = "往来款保存dto")
public class ComeGoMoneySaveDTO extends SaveDTO {

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
     * 付款单位
     */
    @ApiModelProperty(value = "付款单位")
    private String payUnit;


    /**
     * 收款单位
     */
    @ApiModelProperty(value = "收款单位")
    private String gatherUnit;

    /**
     * 账单编号
     */
    @ApiModelProperty(value = "账单编号")
    private String billCode;

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
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private List<FileModel> attachment;


    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String explaination;


    /**
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private Integer status;


    /**
     * 是否为草稿 0是 1不是
     */
    @ApiModelProperty(value = "是否为草稿 0是 1不是")
    private Integer draftFlag;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;
}