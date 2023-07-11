package com.ruoyi.flowable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flowable.domain.entity.ActRuTask;
import com.ruoyi.flowable.mapper.ActRuTaskMapper;
import com.ruoyi.flowable.service.ActRuTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运行时任务Service业务层处理
 *
 * @author mickey
 * @date 2023-06-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ActRuTaskServiceImpl extends ServiceImpl<ActRuTaskMapper, ActRuTask>  implements ActRuTaskService {

    @Autowired
    private ActRuTaskMapper actRuTaskMapper;
    @Override
    public void updateActRuTask(ActRuTask actRuTask) {
        actRuTaskMapper.updateActRuTask(actRuTask);
    }
}