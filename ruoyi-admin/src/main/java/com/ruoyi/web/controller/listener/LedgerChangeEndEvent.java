package com.ruoyi.web.controller.listener;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.bill.mapper.MeaContractBillMapper;
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

    /***
     *
     * add by yangaogao  20230221
     *
     * 变更审批完成后，姜变更信息作为一条新清单数据，插入到工程量清单表中，
     * 台账分解功能，可以针对以上数据进行分解，需要标记为变更，多次变更，多条变更记录插入数据库即可
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        MeaFlowDataInfoMapper meaFlowDataInfoMapper = SpringContextHolder.getBean(MeaFlowDataInfoMapper.class);
        MeaContractBillMapper contractBillMapper = SpringContextHolder.getBean(MeaContractBillMapper.class);
        MeaLedgerChangeMapper meaLedgerChangeMapper = SpringContextHolder.getBean(MeaLedgerChangeMapper.class);
        MeaLedgerChangeDetailMapper meaLedgerChangeDetailMapper = SpringContextHolder.getBean(MeaLedgerChangeDetailMapper.class);
        MeaLedgerBreakdownDetailMapper meaLedgerBreakdownDetailMapper = SpringContextHolder.getBean(MeaLedgerBreakdownDetailMapper.class);
        String taskId = delegateTask.getId();
        QueryWrapper<MeaFlowDataInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("task_id",taskId);
        List<MeaFlowDataInfo> meaFlowDataInfos = meaFlowDataInfoMapper.selectList(queryWrapper);
        if(CollUtil.isNotEmpty(meaFlowDataInfos)){
            MeaFlowDataInfo meaFlowDataInfo = meaFlowDataInfos.get(0);

            QueryWrapper<MeaLedgerChange> queryWrapperInfoMeaLedgerChange=new QueryWrapper<>();
            queryWrapperInfoMeaLedgerChange.eq("bgbh",meaFlowDataInfo.getBussinessId());
            MeaLedgerChange meaLedgerChange = meaLedgerChangeMapper.selectOne(queryWrapperInfoMeaLedgerChange);

            QueryWrapper<MeaLedgerChangeDetail> queryWrapperInfo=new QueryWrapper<>();
            queryWrapperInfo.eq("bgbh",meaFlowDataInfo.getBussinessId());
            List<MeaLedgerChangeDetail> meaLedgerChangeDetails = meaLedgerChangeDetailMapper.selectList(queryWrapperInfo);
            if(CollUtil.isNotEmpty(meaLedgerChangeDetails)){
                for(MeaLedgerChangeDetail meaLedgerChangeDetail:meaLedgerChangeDetails){
                   /* QueryWrapper<MeaLedgerBreakdownDetail> qw=new QueryWrapper<>();
                    qw.eq("zmh",meaLedgerChangeDetail.getZmh());
                    MeaLedgerBreakdownDetail meaLedgerBreakdownDetail = meaLedgerBreakdownDetailMapper.selectOne(qw);*/
                   /* if(meaLedgerBreakdownDetail!=null){
                        meaLedgerBreakdownDetail.setBgsl(meaLedgerChangeDetail.getXzsl());
                        meaLedgerBreakdownDetailMapper.updateById(meaLedgerBreakdownDetail);*/
                        QueryWrapper<MeaContractBill> contractBillQueryWrapper=new QueryWrapper<>();
                        contractBillQueryWrapper.eq("zmh",meaLedgerChangeDetail.getZmh());
                        contractBillQueryWrapper.eq("status","1");
                        MeaContractBill meaContractBill = contractBillMapper.selectOne(contractBillQueryWrapper);
                        if(meaContractBill!=null){
                            /*if(meaContractBill.getXzje()==null){
                                meaContractBill.setXzje(meaLedgerChangeDetail.getXzje());// 修正金额
                            }else {
                                meaContractBill.setXzje(meaContractBill.getXzje().add(meaLedgerChangeDetail.getXzje()));
                            }
                            if(meaContractBill.getXzsl()==null){
                                meaContractBill.setXzsl(meaLedgerChangeDetail.getXzsl());
                            }else {
                                meaContractBill.setXzsl(meaContractBill.getXzsl().add(meaLedgerChangeDetail.getXzsl()));
                            }
                            if(meaContractBill.getZsl()==null){
                                meaContractBill.setZsl(meaLedgerChangeDetail.getShsl());
                            }else {
                                meaContractBill.setZsl(meaContractBill.getZsl().add(meaLedgerChangeDetail.getShsl()));
                            }
                            if(meaContractBill.getZje()==null){
                                meaContractBill.setZje(meaLedgerChangeDetail.getShje());
                            }else {
                                meaContractBill.setZje(meaContractBill.getZje().add(meaLedgerChangeDetail.getShje()));
                            }
                            contractBillMapper.updateById(meaContractBill);*/
                            meaContractBill.setId(null);
                            meaContractBill.setXzje(meaLedgerChangeDetail.getXzje());// 修正金额
                            meaContractBill.setXzsl(meaLedgerChangeDetail.getXzsl());
                         /*   meaContractBill.setZsl(meaContractBill.getZsl().add(meaLedgerChangeDetail.getXzsl()));
                            meaContractBill.setZje(meaContractBill.getZje().add(meaLedgerChangeDetail.getXzje()));*/
                            meaContractBill.setStatus("0");//标识为变更清单
                            contractBillMapper.insert(meaContractBill);
                        }
                    }
                }
                meaLedgerChange.setReviewCode("2");
                meaLedgerChangeMapper.updateById(meaLedgerChange);
            }
//        }
    }

}
