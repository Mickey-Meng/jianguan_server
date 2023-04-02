package com.ruoyi.flowable.domain.dto;

import com.ruoyi.common.core.validate.ConstDictRef;
import com.ruoyi.common.core.validate.UpdateGroup;
import com.ruoyi.flowable.common.constant.FlowVariableType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 流程变量Dto对象。
 *
 * @author Jerry
 * @date 2021-06-06
 */
@ApiModel("流程变量Dto对象")
@Data
public class FlowEntryVariableDto {

    /**
     * 主键Id。
     */
    @ApiModelProperty(value = "主键Id")
    @NotNull(message = "数据验证失败，主键Id不能为空！", groups = {UpdateGroup.class})
    private Long variableId;

    /**
     * 流程Id。
     */
    @ApiModelProperty(value = "流程Id")
    @NotNull(message = "数据验证失败，流程Id不能为空！")
    private Long entryId;

    /**
     * 变量名。
     */
    @ApiModelProperty(value = "变量名")
    @NotBlank(message = "数据验证失败，变量名不能为空！")
    private String variableName;

    /**
     * 显示名。
     */
    @ApiModelProperty(value = "显示名")
    @NotBlank(message = "数据验证失败，显示名不能为空！")
    private String showName;

    /**
     * 流程变量类型。
     */
    @ApiModelProperty(value = "流程变量类型")
    @ConstDictRef(constDictClass = FlowVariableType.class, message = "数据验证失败，流程变量类型为无效值！")
    @NotNull(message = "数据验证失败，流程变量类型不能为空！")
    private Integer variableType;

    /**
     * 绑定数据源Id。
     */
    @ApiModelProperty(value = "绑定数据源Id")
    private Long bindDatasourceId;

    /**
     * 绑定数据源关联Id。
     */
    @ApiModelProperty(value = "绑定数据源关联Id")
    private Long bindRelationId;

    /**
     * 绑定字段Id。
     */
    @ApiModelProperty(value = "绑定字段Id")
    private Long bindColumnId;

    /**
     * 是否内置。
     */
    @ApiModelProperty(value = "是否内置")
    @NotNull(message = "数据验证失败，是否内置不能为空！")
    private Boolean builtin;
}
