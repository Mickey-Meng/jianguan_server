package com.ruoyi.web.controller.listener;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.project.approval.domain.MeaLedgerApproval;
import com.ruoyi.project.approval.domain.MeaLedgerApprovalNo;
import com.ruoyi.project.approval.mapper.MeaLedgerApprovalMapper;
import com.ruoyi.project.approval.mapper.MeaLedgerApprovalNoMapper;
import com.ruoyi.project.flowidatenfo.domain.MeaFlowDataInfo;
import com.ruoyi.project.flowidatenfo.mapper.MeaFlowDataInfoMapper;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownDetailMapper;
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
public class LedgerApprovalEndEvent implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        MeaFlowDataInfoMapper meaFlowDataInfoMapper = SpringContextHolder.getBean(MeaFlowDataInfoMapper.class);
        MeaLedgerBreakdownDetailMapper meaLedgerBreakdownDetailMapper = SpringContextHolder.getBean(MeaLedgerBreakdownDetailMapper.class);
        MeaLedgerApprovalMapper meaLedgerApprovalMapper = SpringContextHolder.getBean(MeaLedgerApprovalMapper.class);
        MeaLedgerApprovalNoMapper meaLedgerApprovalNoMapper = SpringContextHolder.getBean(MeaLedgerApprovalNoMapper.class);
        String taskId = delegateTask.getId();
        QueryWrapper<MeaFlowDataInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id", taskId);
        List<MeaFlowDataInfo> meaFlowDataInfos = meaFlowDataInfoMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(meaFlowDataInfos)) {
            MeaFlowDataInfo meaFlowDataInfo = meaFlowDataInfos.get(0);

            //1 update  MeaLedgerApprovalNo.reviewCode = 2
            QueryWrapper<MeaLedgerApprovalNo> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("sqqc", meaFlowDataInfo.getBussinessId());
            List<MeaLedgerApprovalNo> meaLedgerApprovalNoList = meaLedgerApprovalNoMapper.selectList(queryWrapper2);
            MeaLedgerApprovalNo meaLedgerApprovalNo = meaLedgerApprovalNoList.get(0);

            meaLedgerApprovalNo.setReviewCode("2");
            meaLedgerApprovalNoMapper.updateById(meaLedgerApprovalNo);


            //2  更新台账报审明细表中MeaLedgerApproval.    reviewCode

            QueryWrapper<MeaLedgerApproval> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("sqqc", meaFlowDataInfo.getBussinessId());
            List<MeaLedgerApproval> meaLedgerApprovals = meaLedgerApprovalMapper.selectList(queryWrapper1);
            meaLedgerApprovals.forEach((e) -> {
                e.setReviewCode("2");
            });
            meaLedgerApprovalMapper.updateBatchById(meaLedgerApprovals);
            //3   更新台账分解明细表

            if (meaLedgerApprovals != null) {
                meaLedgerApprovals.forEach((e) -> {
                    if (StrUtil.isEmpty(e.getBreakdownId())) {
                        StaticLog.info("台账审批id" + meaFlowDataInfo.getBussinessId() + "绑定的分解明细getBreakdownId为空");
                    } else {
                        MeaLedgerBreakdownDetail meaLedgerBreakdownDetail = meaLedgerBreakdownDetailMapper.selectById(e.getBreakdownId());
                        if (meaLedgerBreakdownDetail != null) {
                            meaLedgerBreakdownDetail.setFhsl(meaLedgerBreakdownDetail.getSjsl());
                            if (meaLedgerBreakdownDetail.getSjsl() != null) {
                                meaLedgerBreakdownDetail.setFhje(meaLedgerBreakdownDetail.getSjsl().multiply(meaLedgerBreakdownDetail.getHtdj()));
                            } else {
                                meaLedgerBreakdownDetail.setFhje(BigDecimal.ZERO);
                            }
                            // 台账审批通过，台账分解明细的审批状态也通过修改为审批通过
                            meaLedgerBreakdownDetail.setReviewCode("2");
                            meaLedgerBreakdownDetailMapper.updateById(meaLedgerBreakdownDetail);
                        }
                        StaticLog.info("分解明细审批完成");
                    }
                });
            }
        }
    }
}
