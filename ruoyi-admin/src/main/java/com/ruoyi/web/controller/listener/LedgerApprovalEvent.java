package com.ruoyi.web.controller.listener;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.project.approval.domain.MeaLedgerApproval;
import com.ruoyi.project.approval.mapper.MeaLedgerApprovalMapper;
import com.ruoyi.project.flowidatenfo.domain.MeaFlowDataInfo;
import com.ruoyi.project.flowidatenfo.mapper.MeaFlowDataInfoMapper;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownDetailMapper;
import liquibase.pro.packaged.Q;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author jing-zhang
 * @version 1.0.0
 * @date 2022/12/2 18:30
 */
@Component
public class LedgerApprovalEvent implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        MeaFlowDataInfoMapper meaFlowDataInfoMapper = SpringContextHolder.getBean(MeaFlowDataInfoMapper.class);
        MeaLedgerBreakdownDetailMapper meaLedgerBreakdownDetailMapper = SpringContextHolder.getBean(MeaLedgerBreakdownDetailMapper.class);
        MeaLedgerApprovalMapper meaLedgerApprovalMapper = SpringContextHolder.getBean(MeaLedgerApprovalMapper.class);
        String taskId = delegateTask.getId();
        QueryWrapper<MeaFlowDataInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("task_id",taskId);
        List<MeaFlowDataInfo> meaFlowDataInfos = meaFlowDataInfoMapper.selectList(queryWrapper);
        if(CollUtil.isNotEmpty(meaFlowDataInfos)){
            MeaFlowDataInfo meaFlowDataInfo = meaFlowDataInfos.get(0);
            MeaLedgerApproval meaLedgerApproval = meaLedgerApprovalMapper.selectById(meaFlowDataInfo.getBussinessId());
            if(meaLedgerApproval!=null){
                meaLedgerApproval.setReviewCode("2");
                meaLedgerApprovalMapper.updateById(meaLedgerApproval);
                if(StrUtil.isEmpty(meaLedgerApproval.getBreakdownId())){
                    StaticLog.info("台账审批id"+meaFlowDataInfo.getBussinessId()+"绑定的分解明细getBreakdownId为空");
                }else {
                    MeaLedgerBreakdownDetail meaLedgerBreakdownDetail = meaLedgerBreakdownDetailMapper.selectById(meaLedgerApproval.getBreakdownId());
                    if(meaLedgerBreakdownDetail!=null){
                        meaLedgerBreakdownDetail.setFhsl(meaLedgerBreakdownDetail.getSjsl());
                        if(meaLedgerBreakdownDetail.getSjsl()!=null){
                            meaLedgerBreakdownDetail.setFhje(meaLedgerBreakdownDetail.getSjsl().multiply(meaLedgerBreakdownDetail.getHtdj()));
                        }else {
                            meaLedgerBreakdownDetail.setFhje(BigDecimal.ZERO);
                        }
                        meaLedgerBreakdownDetailMapper.updateById(meaLedgerBreakdownDetail);
                    }
                    StaticLog.info("分解明细审批完成");
                }

            }

        }
    }

}
