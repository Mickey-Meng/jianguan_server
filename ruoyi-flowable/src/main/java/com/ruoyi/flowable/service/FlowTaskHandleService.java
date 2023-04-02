package com.ruoyi.flowable.service;

import com.ruoyi.common.core.domain.object.MyPageData;
import com.ruoyi.common.core.domain.object.MyPageParam;
import com.ruoyi.common.core.service.IBaseService;
import com.ruoyi.flowable.model.FlowTaskHandle;
import org.flowable.task.api.Task;

/**
 * 处理工单
 * @Author: lpeng
 * @Date: 2022-05-05 14:34
 * @Description:
 */
public interface FlowTaskHandleService extends IBaseService<FlowTaskHandle, Long> {

    /**
     * 新增一条待处理数据
     * @return
     */
    FlowTaskHandle saveUnHandleByTask(String processInstanceId,String taskId);

    /**
     * 新增一条待处理数据
     * @param flowTaskHandle
     * @return
     */
    FlowTaskHandle saveUnHandleNew(FlowTaskHandle flowTaskHandle);

    /**
     * 设置状态
     * handleStatus 见 FlowTaskHandleStatus
     * @param id
     * @param handleStatus
     * @return
     */
    Boolean setTaskHandleStatus(Long id,Integer handleStatus);

    /**
     * 获取用户的任务列表。这其中包括当前用户作为指派人和候选人。
     *
     * @param username       指派人。
     * @param definitionKey  流程定义的标识。
     * @param definitionName 流程定义名。
     * @param taskName       任务名称。
     * @param pageParam      分页对象。
     * @return 用户的任务列表。
     */
    MyPageData<Task> getTaskListByUserName(
            String username, String definitionKey, String definitionName, String taskName,Integer handleStatus, MyPageParam pageParam);
}
