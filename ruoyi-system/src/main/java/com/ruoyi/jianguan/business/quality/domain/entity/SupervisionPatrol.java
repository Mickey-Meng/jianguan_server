package com.ruoyi.jianguan.business.quality.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 监理巡视
 *
 * @author qiaoxulin
 * @since 2022-06-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_supervision_patrol")
@ApiModel(value = "SupervisionPatrol对象", description = "监理巡视")
public class SupervisionPatrol extends NewBaseEntity {

    private static final long serialVersionUID = 1L;


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
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;


    /**
     * 发起时间
     */
    @ApiModelProperty(value = "发起时间")
    private LocalDate startDate;


    /**
     * 巡视地点
     */
    @ApiModelProperty(value = "巡视地点")
    private String patrolPlace;


    /**
     * 主要施工情况
     */
    @ApiModelProperty(value = "主要施工情况")
    private String buildCondition;


    /**
     * 质量、安全、环保情况
     */
    @ApiModelProperty(value = "质量、安全、环保情况")
    private String qualityCondition;


    /**
     * 发现的问题及处理意见
     */
    @ApiModelProperty(value = "发现的问题及处理意见")
    private String problemDealCondition;


    /**
     * 巡视现场照片
     */
    @ApiModelProperty(value = "巡视现场照片")
    private String patrolPhotoAttachment;


    /**
     * 视频
     */
    @ApiModelProperty(value = "视频")
    private String video;


    /**
     * 其他附件
     */
    @ApiModelProperty(value = "其他附件")
    private String otherAttachmentInfo;


    /**
     * 状态 0 审批中 1已审批
     */
    @ApiModelProperty(value = "状态 0 审批中 1已审批")
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
