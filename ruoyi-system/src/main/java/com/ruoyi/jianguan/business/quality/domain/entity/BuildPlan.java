package com.ruoyi.jianguan.business.quality.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 施工方案
* @author qiaoxulin
* @since 2022-05-25
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_build_plan")
@ApiModel(value = "BuildPlan对象", description = "施工方案")
public class BuildPlan extends NewBaseEntity {

    private static final long serialVersionUID = 1L;


    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 标段id
     */
    @ApiModelProperty(value = "标段id")
    private Integer buildSection;

    /**
    * 项目id
    */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;


    /**
    * 工程编号
    */
    @ApiModelProperty(value = "工程编号")
    private String projectCode;


    /**
    * 专项施工方案名称
    */
    @ApiModelProperty(value = "专项施工方案名称")
    private String buildPlanName;


    /**
    * 附件清单
    */
    @ApiModelProperty(value = "附件清单")
    private String attachmentList;


    /**
    * 专项施工方案
    */
    @ApiModelProperty(value = "专项施工方案")
    private String buildPlanAttachment;


    /**
    * 专家论证会议纪要
    */
    @ApiModelProperty(value = "专家论证会议纪要")
    private String expertMeetingAttachment;


    /**
    * 是否需要专家论证,0 不需要 1需要
    */
    @ApiModelProperty(value = "是否需要专家论证,0 不需要 1需要")
    private Integer expertArgument;


    /**
    * 专家论证意见
    */
    @ApiModelProperty(value = "专家论证意见")
    private String expertOpinion;


    /**
    * 专家论证意见的落实情况
    */
    @ApiModelProperty(value = "专家论证意见的落实情况")
    private String expertOpinionImplement;


    /**
    * 整改回复上传
    */
    @ApiModelProperty(value = "整改回复上传")
    private String replyAttachment;


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

    /**
     * 状态 0 审批中 1 生效
     */
    @ApiModelProperty(value = "状态 0 审批中 1 生效")
    private Integer status;
}
