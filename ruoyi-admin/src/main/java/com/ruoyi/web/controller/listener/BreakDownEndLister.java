package com.ruoyi.web.controller.listener;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.project.flowidatenfo.domain.MeaFlowDataInfo;
import com.ruoyi.project.flowidatenfo.mapper.MeaFlowDataInfoMapper;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownDetailMapper;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jing-zhang
 * @version 1.0.0
 * @date 2022/12/2 18:30
 */
@Component
public class BreakDownEndLister implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        MeaFlowDataInfoMapper meaFlowDataInfoMapper = SpringContextHolder.getBean(MeaFlowDataInfoMapper.class);
        MeaLedgerBreakdownDetailMapper meaLedgerBreakdownMapper = SpringContextHolder.getBean(MeaLedgerBreakdownDetailMapper.class);
        String taskId = execution.getId();
        QueryWrapper<MeaFlowDataInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("task_id",taskId);
        List<MeaFlowDataInfo> meaFlowDataInfos = meaFlowDataInfoMapper.selectList(queryWrapper);
        if(CollUtil.isNotEmpty(meaFlowDataInfos)){
            MeaFlowDataInfo meaFlowDataInfo = meaFlowDataInfos.get(0);
            MeaLedgerBreakdownDetail meaLedgerBreakdown = meaLedgerBreakdownMapper.selectById(meaFlowDataInfo.getBussinessId());
            if(meaLedgerBreakdown!=null){
                meaLedgerBreakdown.setReviewCode("2");
                meaLedgerBreakdownMapper.updateById(meaLedgerBreakdown);
                StaticLog.info("分解明细审批完成");
            }
        }
    }
}
