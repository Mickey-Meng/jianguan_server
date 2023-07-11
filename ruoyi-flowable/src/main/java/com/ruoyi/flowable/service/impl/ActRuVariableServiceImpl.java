package com.ruoyi.flowable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flowable.domain.entity.ActRuVariable;
import com.ruoyi.flowable.mapper.ActRuVariableMapper;
import com.ruoyi.flowable.service.ActRuVariableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运行时变量Service业务层处理
 *
 * @author mickey
 * @date 2023-06-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ActRuVariableServiceImpl extends ServiceImpl<ActRuVariableMapper, ActRuVariable>  implements ActRuVariableService {

    @Autowired
    private ActRuVariableMapper actRuVariableMapper;
    @Override
    public void updateActRuVariable(ActRuVariable actRuVariable) {
        actRuVariableMapper.updateActRuVariable(actRuVariable);
    }
}