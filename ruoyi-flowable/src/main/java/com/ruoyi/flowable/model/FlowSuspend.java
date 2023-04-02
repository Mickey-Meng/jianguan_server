package com.ruoyi.flowable.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.UserFilterColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 暂停任务表对象
 * @Author: lpeng
 * @Date: 2022-04-14 09:54
 * @Description:
 */
@Data
@NoArgsConstructor
@TableName(value = "zz_flow_suspend")
public class FlowSuspend  {
    /**
     * 主键。
     */
    @TableId(value = "suspend_flow_id",type = IdType.ASSIGN_ID)
    public Long suspendFlowId;

    /**
     * 暂停的流程类型
     */
    @TableField(value = "process_definition_key")
    public String processDefinitionKey;

    /**
     * 暂停的流程类型名称
     */
    @TableField(value = "process_definition_name")
    public String  processDefinitionName;

    /**
     * 暂停的实例ID
     */
    @TableField(value = "process_instance_id")
    public String  processInstanceId;

    /**
     * 暂停的任务ID
     */
    @TableField(value = "flow_task_id")
    public String   flowTaskId;

    /**
     * 暂停的实例名称
     */
    @TableField(value = "flow_task_name")
    public String  flowTaskName;
    /**
     * 暂停的业务key
     */
    @TableField(value = "process_instance_business_key")
    public String  processInstanceBusinessKey;

    /**
     * 更新时间。
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 更新者Id。
     */
    @TableField(value = "update_user_id")
    private Long updateUserId;

    /**
     * 创建时间。
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建者Id。
     */
    @UserFilterColumn
    @TableField(value = "create_user_id")
    private Long createUserId;

}
