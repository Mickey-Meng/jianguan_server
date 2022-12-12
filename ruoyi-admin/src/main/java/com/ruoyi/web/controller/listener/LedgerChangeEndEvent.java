package com.ruoyi.web.controller.listener;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.project.flowidatenfo.domain.MeaFlowDataInfo;
import com.ruoyi.project.flowidatenfo.mapper.MeaFlowDataInfoMapper;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownDetailMapper;
import com.ruoyi.project.ledgerChange.domain.MeaLedgerChange;
import com.ruoyi.project.ledgerChange.mapper.MeaLedgerChangeMapper;
import com.ruoyi.project.ledgerChangeDetail.domain.MeaLedgerChangeDetail;
import com.ruoyi.project.ledgerChangeDetail.mapper.MeaLedgerChangeDetailMapper;
import com.ruoyi.project.measurementDocuments.domain.MeaMeasurementDocuments;
import com.ruoyi.project.measurementDocumentsDetail.domain.MeaMeasurementDocumentsDetail;
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
public class LedgerChangeEndEvent implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        MeaFlowDataInfoMapper meaFlowDataInfoMapper = SpringContextHolder.getBean(MeaFlowDataInfoMapper.class);
        MeaLedgerChangeMapper meaLedgerChangeMapper = SpringContextHolder.getBean(MeaLedgerChangeMapper.class);
        MeaLedgerChangeDetailMapper meaLedgerChangeDetailMapper = SpringContextHolder.getBean(MeaLedgerChangeDetailMapper.class);
        MeaLedgerBreakdownDetailMapper meaLedgerBreakdownDetailMapper = SpringContextHolder.getBean(MeaLedgerBreakdownDetailMapper.class);
        String taskId = delegateTask.getId();
        QueryWrapper<MeaFlowDataInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("task_id",taskId);
        List<MeaFlowDataInfo> meaFlowDataInfos = meaFlowDataInfoMapper.selectList(queryWrapper);
        if(CollUtil.isNotEmpty(meaFlowDataInfos)){
            MeaFlowDataInfo meaFlowDataInfo = meaFlowDataInfos.get(0);
            MeaLedgerChange meaLedgerChange = meaLedgerChangeMapper.selectById(meaFlowDataInfo.getBussinessId());
            QueryWrapper<MeaLedgerChangeDetail> queryWrapperInfo=new QueryWrapper<>();
            queryWrapperInfo.eq("bgbh",meaFlowDataInfo.getBussinessId());
            List<MeaLedgerChangeDetail> meaLedgerChangeDetails = meaLedgerChangeDetailMapper.selectList(queryWrapperInfo);
            if(CollUtil.isNotEmpty(meaLedgerChangeDetails)){
                for(MeaLedgerChangeDetail meaLedgerChangeDetail:meaLedgerChangeDetails){
                    QueryWrapper<MeaLedgerBreakdownDetail> qw=new QueryWrapper<>();
                    qw.eq("zmh",meaLedgerChangeDetail.getZmh());
                    MeaLedgerBreakdownDetail meaLedgerBreakdownDetail = meaLedgerBreakdownDetailMapper.selectOne(qw);
                    if(meaLedgerBreakdownDetail!=null){
                        meaLedgerBreakdownDetail.setBgsl(meaLedgerChangeDetail.getXzsl());
                        meaLedgerBreakdownDetailMapper.updateById(meaLedgerBreakdownDetail);
                    }
                }
                meaLedgerChange.setReviewCode("2");
                meaLedgerChangeMapper.updateById(meaLedgerChange);
            }
        }
        System.out.println("delegateTask"+delegateTask);
    }

}
