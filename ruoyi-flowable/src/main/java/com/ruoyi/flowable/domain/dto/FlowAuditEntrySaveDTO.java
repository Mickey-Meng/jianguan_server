package com.ruoyi.flowable.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 流程审批人员设置保存dto
 *
 * @author qiaoxulin
 * @date : 2022/5/18 11:27
 */
@Data
@ApiModel(value = "FlowAuditEntrySaveDTO", description = "流程审批人员设置保存dto")
public class FlowAuditEntrySaveDTO {

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
     * 流程定义key
     */
    @ApiModelProperty(value = "流程定义key")
    private String flowKey;

    /**
     * 流程类型id
     */
    @ApiModelProperty(value = "流程类型id")
    private Long typeId;

    /**
     * 流程节点key
     */
    @ApiModelProperty(value = "流程节点key")
    private String entryKey;

    /**
     * 是否是会签 0不是 1是
     */
    @ApiModelProperty(value = "是否是会签 0不是 1是")
    private Integer isSign;

    /**
     * 流程节点名称
     */
    @ApiModelProperty(value = "流程节点名称")
    private String entryName;


    /**
     * 流程节点审核人定义变量
     */
    @ApiModelProperty(value = "流程节点审核人定义变量")
    private String entryUserVariable;

    /**
     * 抄送人员id
     */
    @ApiModelProperty(value = "抄送人员id")
    private List<Integer> copyUser;


    /**
     * 节点排序
     */
    @ApiModelProperty(value = "节点排序")
    private Integer sort;


    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private List<Integer> userId;


    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private List<String> userName;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;
}
