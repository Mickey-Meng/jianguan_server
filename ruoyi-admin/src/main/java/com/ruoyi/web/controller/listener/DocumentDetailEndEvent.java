package com.ruoyi.web.controller.listener;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.service.ConfigService;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.bill.mapper.MeaContractBillMapper;
import com.ruoyi.project.flowidatenfo.domain.MeaFlowDataInfo;
import com.ruoyi.project.flowidatenfo.mapper.MeaFlowDataInfoMapper;
import com.ruoyi.project.ledger.domain.MeaLedgerBreakdownDetail;
import com.ruoyi.project.ledger.mapper.MeaLedgerBreakdownDetailMapper;
import com.ruoyi.project.measurementDocuments.domain.MeaMeasurementDocuments;
import com.ruoyi.project.measurementDocuments.mapper.MeaMeasurementDocumentsMapper;
import com.ruoyi.project.measurementDocumentsDetail.domain.MeaMeasurementDocumentsDetail;
import com.ruoyi.project.measurementDocumentsDetail.mapper.MeaMeasurementDocumentsDetailMapper;
import lombok.RequiredArgsConstructor;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * @author jing-zhang
 * @version 1.0.0
 * @date 2022/12/2 18:30
 */
@Component
public class DocumentDetailEndEvent implements ServletContextListener,TaskListener {



    @Override
    public void notify(DelegateTask delegateTask) {
        MeaMeasurementDocumentsMapper meaMeasurementDocumentsMapper = SpringContextHolder.getBean(MeaMeasurementDocumentsMapper.class);
        MeaMeasurementDocumentsDetailMapper meaMeasurementDocumentsDetailMapper = SpringContextHolder.getBean(MeaMeasurementDocumentsDetailMapper.class);
        MeaFlowDataInfoMapper meaFlowDataInfoMapper = SpringContextHolder.getBean(MeaFlowDataInfoMapper.class);
        MeaLedgerBreakdownDetailMapper meaLedgerBreakdownDetailMapper = SpringContextHolder.getBean(MeaLedgerBreakdownDetailMapper.class);
        String taskId = delegateTask.getId();
        QueryWrapper<MeaFlowDataInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("task_id",taskId);
        List<MeaFlowDataInfo> meaFlowDataInfos = meaFlowDataInfoMapper.selectList(queryWrapper);
        if(CollUtil.isNotEmpty(meaFlowDataInfos)){
            MeaFlowDataInfo meaFlowDataInfo = meaFlowDataInfos.get(0);
            MeaMeasurementDocuments meaMeasurementDocuments = meaMeasurementDocumentsMapper.selectById(meaFlowDataInfo.getBussinessId());
            QueryWrapper<MeaMeasurementDocumentsDetail> queryWrapperInfo=new QueryWrapper<>();
            queryWrapperInfo.eq("pzbh",meaFlowDataInfo.getBussinessId());
            List<MeaMeasurementDocumentsDetail> meaMeasurementDocumentsDetails = meaMeasurementDocumentsDetailMapper.selectList(queryWrapperInfo);
            if(CollUtil.isNotEmpty(meaMeasurementDocumentsDetails)){
                for(MeaMeasurementDocumentsDetail meaMeasurementDocumentsDetail:meaMeasurementDocumentsDetails){
                    QueryWrapper<MeaLedgerBreakdownDetail> qw=new QueryWrapper<>();
                    qw.eq("tzfjbh",meaMeasurementDocuments.getTzfjbh());
                    qw.eq("zmh",meaMeasurementDocumentsDetail.getZmh());
                    MeaLedgerBreakdownDetail meaLedgerBreakdownDetail = meaLedgerBreakdownDetailMapper.selectOne(qw);
                    if(meaLedgerBreakdownDetail!=null){
                        if(meaLedgerBreakdownDetail.getYjlsl()!=null){
                            meaLedgerBreakdownDetail.setYjlsl(meaLedgerBreakdownDetail.getYjlsl().add(meaMeasurementDocumentsDetail.getBqjlsl()));
                        }else {
                            meaLedgerBreakdownDetail.setYjlsl(meaMeasurementDocumentsDetail.getBqjlsl());
                        }
                        meaLedgerBreakdownDetailMapper.updateById(meaLedgerBreakdownDetail);
                    }
                }
                meaMeasurementDocuments.setReviewCode("2");
                meaMeasurementDocumentsMapper.updateById(meaMeasurementDocuments);
            }
        }
    }

}
