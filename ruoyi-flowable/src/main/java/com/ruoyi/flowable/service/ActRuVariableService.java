package com.ruoyi.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.flowable.domain.entity.ActRuVariable;
import com.ruoyi.flowable.domain.entity.ActRuVariableQuery;

import java.util.List;

/**
 * 运行时变量Service接口
 *
 * @author mickey
 * @date 2023-06-07
 */
public interface ActRuVariableService extends IService<ActRuVariable> {

    void updateActRuVariable(ActRuVariable actRuVariable);

    List<ActRuVariable> findActRuVariable(ActRuVariableQuery actRuVariableQuery);
}