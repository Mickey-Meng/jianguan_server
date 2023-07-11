package com.ruoyi.flowable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.flowable.domain.entity.ActHiTaskinst;
import com.ruoyi.flowable.domain.entity.ActRuTask;
import com.ruoyi.flowable.mapper.ActHiTaskinstMapper;
import com.ruoyi.flowable.mapper.ActRuTaskMapper;
import com.ruoyi.flowable.service.ActHiTaskinstService;
import com.ruoyi.flowable.service.ActRuTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 历史的任务实例Service业务层处理
 *
 * @author mickey
 * @date 2023-06-07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ActHiTaskinstServiceImpl extends ServiceImpl<ActHiTaskinstMapper, ActHiTaskinst>  implements ActHiTaskinstService {

    @Autowired
    private ActHiTaskinstMapper actHiTaskinstMapper;
    @Override
    public void updateActHiTaskinst(ActHiTaskinst actHiTaskinst) {
        actHiTaskinstMapper.updateActHiTaskinst(actHiTaskinst);
    }
}