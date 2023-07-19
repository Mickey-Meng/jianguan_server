package com.ruoyi.jianguan.business.quality.domain.dto;

import com.ruoyi.common.core.domain.dto.SaveDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 监理通知保存dto
 *
 * @author qiaoxulin
 * @date : 2022/6/11 16:28
 */
@Data
@ApiModel(value = "SupervisionNoticeSaveDTO", description = "监理通知保存dto")
public class SupervisionNoticeSaveDTO extends SaveDTO {
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
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;


    /**
     * 主送
     */
    @ApiModelProperty(value = "主送")
    private String mainSent;


    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;


    /**
     * 抄送
     */
    @ApiModelProperty(value = "抄送")
    private String copy;


    /**
     * 通知内容
     */
    @ApiModelProperty(value = "通知内容")
    private String content;


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
