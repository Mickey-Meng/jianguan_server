package com.ruoyi.flowable.domain.dto;

import com.ruoyi.common.core.validate.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: lpeng
 * @Date: 2022-04-14 10:02
 * @Description:
 */
@ApiModel("暂停的流程的Dto对象")
@Data
public class FlowSuspendDto {

    /**
     * 主键Id。
     */
    @ApiModelProperty(value = "主键Id")
    @NotNull(message = "数据验证失败，主键Id不能为空！", groups = {UpdateGroup.class})
    public Long suspendFlowId;

    /**
     * 暂停的流程类型
     */
    @ApiModelProperty(value = "暂停的流程类型")
    @NotNull(message = "暂停的流程类型不能为空！")
    public String processDefinitionKey;

    /**
     * 暂停的实例ID
     */
    @ApiModelProperty(value = "暂停的实例ID")
    @NotNull(message = "暂停的实例ID不能为空！")
    public Long  processInstanceId;

    /**
     * 暂停的任务ID
     */
    @ApiModelProperty(value = "暂停的任务ID")
    @NotNull(message = "暂停的任务ID不能为空！")
    public Long   flowTaskId;

    /**
     * 暂停的业务key
     */
    @ApiModelProperty(value = "暂停的业务key")
    @NotNull(message = "暂停的业务key不能为空！")
    public String  processInstanceBusinessKey;




}
