package com.ruoyi.flowable.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.NewBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 流程类型
 *
 * @author qiaoxulin
 * @since 2022-06-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zz_flow_type")
@ApiModel(value = "FlowType对象", description = "流程类型")
public class FlowType extends NewBaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 流程定义key
     */
    @ApiModelProperty(value = "流程定义key")
    private String flowKey;


    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称")
    private String flowName;


    /**
     * 删除标记(1: 正常 -1: 已删除)
     */
    @ApiModelProperty(value = "删除标记(1: 正常 -1: 已删除)")
    private Integer deletedFlag;


}
