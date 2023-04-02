package com.ruoyi.flowable.model;

/**
 * @Author: lpeng
 * @Date: 2022-05-05 13:58
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.mapper.BaseModelMapper;
import com.ruoyi.flowable.domain.vo.FlowTaskHandleVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flowable.task.api.TaskInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName(value = "zz_flow_task_handle")
public class FlowTaskHandle {
    /**
     * 主键Id。
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 流程实例Id。
     */
    @TableField(value = "process_instance_id")
    private String processInstanceId;

    /**
     * 流程实例Id。
     */
    @TableField(value = "process_definition_key")
    private String processDefinitionKey;

    /**
     * 流程实例Id。
     */
    @TableField(value = "process_definition_name")
    private String processDefinitionName;
    /**
     * 流程定义Id。
     */
    @TableField(value = "process_definition_id")
    private String processDefinitionId;

    /**
     * 任务Id。
     */
    @TableField(value = "task_id")
    private String taskId;

    /**
     * 任务标识。
     */
    @TableField(value = "task_key")
    private String taskKey;

    /**
     * 任务名称。
     */
    @TableField(value = "task_name")
    private String taskName;
    /**
     * 处理状态。
     */
    @ApiModelProperty(value = "处理状态")
    private Integer taskHandleStatus;
    /**
     * 处理状态描述。
     */
    @ApiModelProperty(value = "处理状态描述")
    private String taskHandleStatusStr;
    /**
     * 处理人id。
     */
    @ApiModelProperty(value = "处理人id")
    private Long taskHandleUserId;
    /**
     * 处理人登录名。
     */
    @ApiModelProperty(value = "处理人登录名")
    private String taskHandleUserLoginName;
    /**
     * 处理人用户名。
     */
    @ApiModelProperty(value = "处理人用户名")
    private String taskHandleUserName;

    /**
     * 创建者Id。
     */
    @TableField(value = "create_user_id")
    private Long createUserId;

    /**
     * 创建者登录名。
     */
    @TableField(value = "create_login_name")
    private String createLoginName;

    /**
     * 创建者显示名。
     */
    @TableField(value = "create_username")
    private String createUsername;

    /**
     * 创建时间。
     */
    @TableField(value = "create_time")
    private Date createTime;

    public FlowTaskHandle(TaskInfo task) {
        this.fillWith(task);
    }

    public void fillWith(TaskInfo task) {
        this.taskId = task.getId();
        this.taskKey = task.getTaskDefinitionKey();
        this.taskName = task.getName();
        this.processInstanceId = task.getProcessInstanceId();
    }

    @Mapper
    public interface FlowTaskHandleModelMapper extends BaseModelMapper<FlowTaskHandleVo, FlowTaskHandle> {
    }
    public static final FlowTaskHandleModelMapper INSTANCE = Mappers.getMapper(FlowTaskHandleModelMapper.class);

}
