package com.ruoyi.flowable.domain.vo;

import com.ruoyi.common.annotation.UserFilterColumn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lpeng
 * @Date: 2022-04-14 10:41
 * @Description:
 */
@ApiModel("暂停的流程Vo对象")
@Data
public class FlowSuspendVo {

    /**
     * 主键。
     */
    @ApiModelProperty(value = "主键")
    public Long suspendFlowId;

    /**
     * 暂停的流程类型
     */
    @ApiModelProperty(value = "暂停的流程类型")
    public String processDefinitionKey;

    /**
     * 暂停的实例ID
     */
    @ApiModelProperty(value = "暂停的实例ID")
    public Long  processInstanceId;

    /**
     * 暂停的任务ID
     */
    @ApiModelProperty(value = "暂停的任务ID")
    public Long   flowTaskId;

    /**
     * 暂停的业务key
     */
    @ApiModelProperty(value = "暂停的业务key")
    public String  processInstanceBusinessKey;

    /**
     * 更新时间。
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 更新者Id。
     */
    @ApiModelProperty(value = "更新者Id")
    private Long updateUserId;

    /**
     * 创建时间。
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建者Id。
     */
    @UserFilterColumn
    @ApiModelProperty(value = "创建者Id")
    private Long createUserId;
}
