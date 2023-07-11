package com.ruoyi.flowable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flowable.domain.entity.ActHiVarinst;
import com.ruoyi.flowable.domain.entity.ActRuTask;
import com.ruoyi.flowable.mapper.ActHiVarinstMapper;
import com.ruoyi.flowable.mapper.ActRuTaskMapper;
import com.ruoyi.flowable.service.ActHiVarinstService;
import com.ruoyi.flowable.service.ActRuTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 历史的流程运行中的变量信息Service业务层处理
 *
 * @author mickey
 * @date 2023-06-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ActHiVarinstServiceImpl extends ServiceImpl<ActHiVarinstMapper, ActHiVarinst>  implements ActHiVarinstService {

    @Autowired
    private ActHiVarinstMapper actHiVarinstMapper;
    @Override
    public void updateActHiVarinst(ActHiVarinst actHiVarinst) {
        actHiVarinstMapper.updateActHiVarinst(actHiVarinst);
    }
}