package com.ruoyi.jianguan.quality.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 监理指令
 *
 * @author qiaoxulin
 * @since 2022-06-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_supervision_order")
@ApiModel(value = "SupervisionOrder对象", description = "监理指令")
public class SupervisionOrder extends NewBaseEntity {

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
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;


    /**
     * 施工标段id
     */
    @ApiModelProperty(value = "施工标段id")
    private Integer buildSection;


    /**
     * 抄送
     */
    @ApiModelProperty(value = "抄送")
    private String copy;


    /**
     * 指令编号
     */
    @ApiModelProperty(value = "指令编号")
    private String orderCode;


    /**
     * 指令标题
     */
    @ApiModelProperty(value = "指令标题")
    private String orderTitle;


    /**
     * 回复期限
     */
    @ApiModelProperty(value = "回复期限")
    private LocalDate orderDate;


    /**
     * 工程部位
     */
    @ApiModelProperty(value = "工程部位")
    private String projectPart;


    /**
     * 严重程度 0 一般  1严重
     */
    @ApiModelProperty(value = "严重程度 0 一般  1严重")
    private Integer seriousLevel;


    /**
     * 指令内容
     */
    @ApiModelProperty(value = "指令内容")
    private String orderContent;


    /**
     * 问题照片
     */
    @ApiModelProperty(value = "问题照片")
    private String problemPhotoAttachment;


    /**
     * 其他附件
     */
    @ApiModelProperty(value = "其他附件")
    private String otherAttachment;


    /**
     * 指令回复编号
     */
    @ApiModelProperty(value = "指令回复编号")
    private String replyCode;


    /**
     * 整改完成时间
     */
    @ApiModelProperty(value = "整改完成时间")
    private LocalDate replyDate;


    /**
     * 回复内容
     */
    @ApiModelProperty(value = "回复内容")
    private String replyContent;


    /**
     * 整改照片
     */
    @ApiModelProperty(value = "整改照片")
    private String replyPhotoAttachment;


    /**
     * 其他附件
     */
    @ApiModelProperty(value = "其他附件")
    private String replyOtherAttachment;


    /**
     * 专监复查意见
     */
    @ApiModelProperty(value = "专监复查意见")
    private String reviewSupervision;


    /**
     * 总监复查意见
     */
    @ApiModelProperty(value = "总监复查意见")
    private String reviewDirector;


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
