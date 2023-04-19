package com.ruoyi.jianguan.business.quality.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 隐蔽工程验收记录
 *
 * @author qiaoxulin
 * @since 2022-05-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_hidden_project_accept")
@ApiModel(value = "HiddenProjectAccept对象", description = "隐蔽工程验收记录")
public class HiddenProjectAccept extends NewBaseEntity {

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
     * 标段id
     */
    @ApiModelProperty(value = "标段id")
    private Integer buildSection;

    /**
     * 工程编号
     */
    @ApiModelProperty(value = "工程编号")
    private String projectCode;


    /**
     * 分项工程
     */
    @ApiModelProperty(value = "分项工程")
    private String subProject;


    /**
     * 单位、分部工程
     */
    @ApiModelProperty(value = "单位、分部工程")
    private String unit;


    /**
     * 隐蔽工程项目
     */
    @ApiModelProperty(value = "隐蔽工程项目")
    private String hiddenProject;


    /**
     * 施工自检结果
     */
    @ApiModelProperty(value = "施工自检结果")
    private String buildCheckselfResult;


    /**
     * 附件清单
     */
    @ApiModelProperty(value = "附件清单")
    private String attachList;


    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private String attachment;


    /**
     * 项目质检负责人
     */
    @ApiModelProperty(value = "项目质检负责人")
    private Integer qualityCheckUser;


    /**
     * 项目施工负责人
     */
    @ApiModelProperty(value = "项目施工负责人")
    private Integer projectBuildUser;


    /**
     * 现场监理人员
     */
    @ApiModelProperty(value = "现场监理人员")
    private Integer supervisorUser;


    /**
     * 专业监理工程师
     */
    @ApiModelProperty(value = "专业监理工程师")
    private Integer supervisorEngineerUser;


    /**
     * 项目负责人
     */
    @ApiModelProperty(value = "项目负责人")
    private Integer projectChargeUser;


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
     * 状态 0 进行中 1 已完成
     */
    @ApiModelProperty(value = "状态 0 进行中 1 已完成")
    private Integer status;


}
