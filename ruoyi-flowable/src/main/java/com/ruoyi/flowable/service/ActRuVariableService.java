package com.ruoyi.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flowable.domain.entity.ActRuVariable;

/**
 * 运行时变量Service接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ActRuVariableService extends IService<ActRuVariable> {

    void updateActRuVariable(ActRuVariable actRuVariable);

}