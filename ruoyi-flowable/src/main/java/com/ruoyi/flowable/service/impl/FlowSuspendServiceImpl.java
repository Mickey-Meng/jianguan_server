package com.ruoyi.flowable.service.impl;

import com.ruoyi.common.core.mapper.BaseDaoMapper;
import com.ruoyi.common.core.service.BaseService;
import com.ruoyi.flowable.mapper.FlowSuspendMapper;
import com.ruoyi.flowable.model.FlowSuspend;
import com.ruoyi.flowable.service.FlowSuspendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lpeng
 * @Date: 2022-04-14 10:09
 * @Description:
 */
@Slf4j
@Service("flowSuspendService")
public class FlowSuspendServiceImpl extends BaseService<FlowSuspend, Long> implements FlowSuspendService {

    @Autowired
    private FlowSuspendMapper flowSuspendMapper;

    @Override
    protected BaseDaoMapper<FlowSuspend> mapper() {
        return flowSuspendMapper;
    }
}
