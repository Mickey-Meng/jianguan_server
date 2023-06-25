package com.ruoyi.flowable.domain.vo;

import com.ruoyi.common.core.domain.NewBaseEntity;
import com.ruoyi.common.core.domain.dto.SsFUsersDTO;
import com.ruoyi.common.core.domain.entity.SsFUsers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 流程审核人详情返回VO
 *
 * @author qiaoxulin
 * @since 2022-05-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "FlowAuditEntryDetailVo", description = "流程审核人详情返回VO")
public class FlowAuditEntryDetailVo extends NewBaseEntity {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 流程类型id
     */
    @ApiModelProperty(value = "流程类型id")
    private Long typeId;

    /**
     * 流程类型
     */
    @ApiModelProperty(value = "流程类型")
    private String typeName;

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
     * 是否是会签 0不是 1是
     */
    @ApiModelProperty(value = "是否是会签 0不是 1是")
    private Integer isSign;

    /**
     * 流程定义key
     */
    @ApiModelProperty(value = "流程定义key")
    private String flowKey;


    /**
     * 流程节点key
     */
    @ApiModelProperty(value = "流程节点key")
    private String entryKey;

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
     * 节点排序
     */
    @ApiModelProperty(value = "节点排序")
    private Integer sort;

    /**
     * 抄送人员id
     */
    @ApiModelProperty(value = "抄送人员id")
    private List<Integer> copyUserId;

    /**
     * 抄送人员id
     */
    @ApiModelProperty(value = "抄送人员id")
    private String copyUser;

    /**
     * 抄送人员信息
     */
    @ApiModelProperty(value = "抄送人员信息")
    private List<SsFUsersDTO> copyUserInfo;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private List<Integer> userIds;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;

    /**
     * 审核人员信息
     */
    @ApiModelProperty(value = "审核人员信息")
    private List<SsFUsersDTO> userInfo;


    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private List<String> userNames;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;

    /**
     * 节点配置数量：0表示没有配置节点
     */
    @ApiModelProperty(value = "节点配置数量：0表示没有配置节点")
    private Integer count;
}
