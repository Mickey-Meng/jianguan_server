package com.ruoyi.web.controller.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ruoyi.flowable.model.FlowCategory;
import com.ruoyi.project.bill.domain.MeaContractBill;
import com.ruoyi.project.bill.domain.bo.MeaContractBillBo;
import com.ruoyi.project.bill.mapper.MeaContractBillMapper;
import com.ruoyi.project.bill.service.IMeaContractBillService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        IMeaContractBillService iMeaContractBillService = SpringContextHolder.getBean(IMeaContractBillService.class);
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
                        meaLedgerBreakdownDetail.setBgsl(meaLedgerChangeDetail.getBgsl());
                        meaLedgerBreakdownDetailMapper.updateById(meaLedgerBreakdownDetail);*/
                        QueryWrapper<MeaContractBill> contractBillQueryWrapper=new QueryWrapper<>();
                        contractBillQueryWrapper.eq("zmh",meaLedgerChangeDetail.getZmh());
//                        MeaContractBill meaContractBill = contractBillMapper.selectOne(contractBillQueryWrapper);
                        List<MeaContractBill> meaContractBills = contractBillMapper.selectList(contractBillQueryWrapper);
                        // #510   yangaogao  20230731  1.之前是否有变更记录 ，如果有则再原数据记录上累加 数量和金额，否则插入一条新数据,标识为 变更清单数据
                        List<MeaContractBill> meaContractBillList = meaContractBills.stream().filter(item -> "1".equals(item.getIsChange())).collect(Collectors.toList());
                        if(CollUtil.isNotEmpty(meaContractBillList)){
                            MeaContractBill meaContractBill = meaContractBillList.get(0);
                            meaContractBill.setBgje(meaLedgerChangeDetail.getBgje().add(meaContractBill.getBgje()));// 变更金额
                            meaContractBill.setBgsl(meaLedgerChangeDetail.getBgsl().add(meaContractBill.getBgsl()));
                            MeaContractBillBo meaContractBillBo = BeanUtil.copyProperties(meaContractBill, MeaContractBillBo.class);
                            iMeaContractBillService.updateByBo(meaContractBillBo);
                        }else{
                            Optional<MeaContractBill> meaContractBillNoChange =  meaContractBills.stream().filter(item -> item.getIsChange().equals("0")).findFirst();
                            MeaContractBill meaContractBill1 = meaContractBillNoChange.get();
                            meaContractBill1.setId(null);
                            meaContractBill1.setHtsl(new BigDecimal("0"));
                            meaContractBill1.setHtje(new BigDecimal("0"));
                            meaContractBill1.setBgje(meaLedgerChangeDetail.getBgje());// 变更金额
                            meaContractBill1.setBgsl(meaLedgerChangeDetail.getBgsl());
                            meaContractBill1.setIsChange("1");//标识为变更清单
                            contractBillMapper.insert(meaContractBill1);
                        }
                    }
                }
                meaLedgerChange.setReviewCode("2");
                meaLedgerChange.setSprq(new Date());
                meaLedgerChangeMapper.updateById(meaLedgerChange);
            }
//        }
    }

}
