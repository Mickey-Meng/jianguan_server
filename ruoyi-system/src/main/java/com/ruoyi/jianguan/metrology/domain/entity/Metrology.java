package com.ruoyi.jianguan.metrology.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 计量表
 *
 * @author G.X.L
 * @date 2023-03-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_metrology")
@ApiModel(value = "Metrology对象", description = "计量表")
public class Metrology extends NewBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

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

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private String attachment;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;

    /**
     * 插入记录的时间
     */
    @ApiModelProperty(value = "插入记录的时间")
    private LocalDateTime sttime;

    /**
     * 状态: -1: 删除, 0: 冻结, 1: 正常
     */
    @ApiModelProperty(value = "状态: -1: 删除, 0: 冻结, 1: 正常")
    private Integer ststate;

    /**
     * 顺序
     */
    @ApiModelProperty(value = "顺序")
    private Integer storder;


}
