package com.ruoyi.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flowable.domain.entity.ActHiVarinst;
import com.ruoyi.flowable.domain.entity.ActRuTask;

/**
 * 历史的流程运行中的变量信息Service接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ActHiVarinstService extends IService<ActHiVarinst> {

    void updateActHiVarinst(ActHiVarinst actHiVarinst);

}