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
 * 分项开工申请
 *
 * @author qiaoxulin
 * @since 2022-06-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_subitem_open")
@ApiModel(value = "SubitemOpen对象", description = "分项开工申请")
public class SubitemOpen extends NewBaseEntity {

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
     * 工程编号
     */
    @ApiModelProperty(value = "工程编号")
    private String projectCode;


    /**
     * 建议开工日期
     */
    @ApiModelProperty(value = "建议开工日期")
    private LocalDate openDate;


    /**
     * 计划完工日期
     */
    @ApiModelProperty(value = "计划完工日期")
    private LocalDate endDate;


    /**
     * 地点或桩号
     */
    @ApiModelProperty(value = "地点或桩号")
    private String place;


    /**
     * 现场负责人
     */
    @ApiModelProperty(value = "现场负责人")
    private Integer liveUser;


    /**
     * 施工员
     */
    @ApiModelProperty(value = "施工员")
    private Integer buildUser;


    /**
     * 质检员
     */
    @ApiModelProperty(value = "质检员")
    private Integer checkUser;


    /**
     * 施工准备情况说明
     */
    @ApiModelProperty(value = "施工准备情况说明")
    private String buildPrepareExplain;


    /**
     * 标准试验审批表附件
     */
    @ApiModelProperty(value = "标准试验审批表附件")
    private String experimentAttachment;


    /**
     * 标准试验审批表说明
     */
    @ApiModelProperty(value = "标准试验审批表说明")
    private String experimentExplain;


    /**
     * 专项施工方案审批表附件
     */
    @ApiModelProperty(value = "专项施工方案审批表附件")
    private String buildAttachment;


    /**
     * 专项施工方案审批表说明
     */
    @ApiModelProperty(value = "专项施工方案审批表说明")
    private String buildExplain;


    /**
     * 工艺试验审批表附件
     */
    @ApiModelProperty(value = "工艺试验审批表附件")
    private String processAttachment;


    /**
     * 工艺试验审批表说明
     */
    @ApiModelProperty(value = "工艺试验审批表说明")
    private String processExplain;


    /**
     * 到场材料审批表附件
     */
    @ApiModelProperty(value = "到场材料审批表附件")
    private String materialAttachment;


    /**
     * 到场材料审批表说明
     */
    @ApiModelProperty(value = "到场材料审批表说明")
    private String materialExplain;


    /**
     * 到场设备审批表附件
     */
    @ApiModelProperty(value = "到场设备审批表附件")
    private String equipmentAttachment;


    /**
     * 到场设备审批表说明
     */
    @ApiModelProperty(value = "到场设备审批表说明")
    private String equipmentExplain;


    /**
     * 到场技术附件
     */
    @ApiModelProperty(value = "到场技术附件")
    private String techAttachment;


    /**
     * 到场技术说明
     */
    @ApiModelProperty(value = "到场技术说明")
    private String techExplain;


    /**
     * 施工方案附件
     */
    @ApiModelProperty(value = "施工方案附件")
    private String buildPlanAttachment;


    /**
     * 施工方案说明
     */
    @ApiModelProperty(value = "施工方案说明")
    private String buildPlanExplain;


    /**
     * 安全技术措施附件
     */
    @ApiModelProperty(value = "安全技术措施附件")
    private String securityAttachment;


    /**
     * 安全技术措施说明
     */
    @ApiModelProperty(value = "安全技术措施说明")
    private String securityExplain;


    /**
     * 危险性较大项目附件
     */
    @ApiModelProperty(value = "危险性较大项目附件")
    private String riskAttachment;


    /**
     * 危险性较大项目说明
     */
    @ApiModelProperty(value = "危险性较大项目说明")
    private String riskExplain;


    /**
     * 环境保护措施附件
     */
    @ApiModelProperty(value = "环境保护措施附件")
    private String environmentAttachment;


    /**
     * 环境保护措施说明
     */
    @ApiModelProperty(value = "环境保护措施说明")
    private String environmentExplain;


    /**
     * 环保、安全、质量、技术交底材料附件
     */
    @ApiModelProperty(value = "环保、安全、质量、技术交底材料附件")
    private String bottomAttachment;


    /**
     * 环保、安全、质量、技术交底材料说明
     */
    @ApiModelProperty(value = "环保、安全、质量、技术交底材料说明")
    private String bottomExplain;


    /**
     * 状态 0 审批中 1 已审批
     */
    @ApiModelProperty(value = "状态 0 审批中 1 已审批")
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
