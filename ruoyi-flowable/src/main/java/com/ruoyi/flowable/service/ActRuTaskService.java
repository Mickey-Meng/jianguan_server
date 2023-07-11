package com.ruoyi.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flowable.domain.entity.ActRuTask;

/**
 * 运行时任务Service接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ActRuTaskService extends IService<ActRuTask> {

    void updateActRuTask(ActRuTask actRuTask);

}