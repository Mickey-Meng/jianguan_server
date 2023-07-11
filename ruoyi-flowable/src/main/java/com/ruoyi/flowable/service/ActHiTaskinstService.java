package com.ruoyi.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flowable.domain.entity.ActHiTaskinst;
import com.ruoyi.flowable.domain.entity.ActRuTask;

/**
 * 历史的任务实例Service接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ActHiTaskinstService extends IService<ActHiTaskinst> {

    void updateActHiTaskinst(ActHiTaskinst actHiTaskinst);

}