package com.ruoyi.web.controller.scheduled;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.ql.domain.QlContractInfoSale;
import com.ruoyi.ql.domain.vo.QlContractInfoSaleVo;
import com.ruoyi.ql.mapper.QlContractInfoSaleMapper;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.mapper.SysNoticeMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.list.PredicatedList;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ContractExpireScheduled
 * @Description TODO
 * @Author Karl
 * @Date 2023/1/10 14:43
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
public class ContractExpireScheduled {

    private final QlContractInfoSaleMapper baseMapper;
    private final SysNoticeMapper sysNoticeMapper;



    @Scheduled(cron = "0 45 23 * * ?")
    public void ContractExpireScheduled() {

        System.out.println("qweq");

        List<QlContractInfoSale> qlContractInfoSaleVos = baseMapper.ContractExpireScheduled();
        List<SysNotice> sysNotices = new ArrayList<>();
        for (QlContractInfoSale qlContractInfoSaleVo : qlContractInfoSaleVos) {

            Date retentionDate = qlContractInfoSaleVo.getRetentionDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(retentionDate);

            SysNotice sysNotice = new SysNotice();
            sysNotice.setNoticeContent("客户:["+qlContractInfoSaleVo.getCustomerName()+"]签署的合同["+qlContractInfoSaleVo.getContractName()+"]质保金于["+format+"]到期，请尽快处理。");
            sysNotice.setNoticeTitle("质保金到期提醒");
            sysNotice.setNoticeType("2");
            sysNotice.setBusinessId(qlContractInfoSaleVo.getId());
            sysNotice.setBusinessType("contractInfoSale");
            sysNotices.add(sysNotice);
        }

        sysNoticeMapper.insertBatch(sysNotices);

        System.out.println(qlContractInfoSaleVos);

    }


}
