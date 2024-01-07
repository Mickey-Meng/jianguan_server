package com.ruoyi.web.controller.scheduled;

import com.ruoyi.ql.domain.QlContractInfoSale;
import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.mapper.QlContractInfoSaleMapper;
import com.ruoyi.ql.mapper.QlOutboundMapper;
import com.ruoyi.ql.mapper.QlWarehousingMapper;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.mapper.SysNoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * yangaogao
 * 20231106
 *
 * 根据入库单明细/出库单明细的付款/收款截止日期，在平台中给相应业务员设置提醒功能，
 * 入库单明细提醒入库对接人，出库单提醒出库审核人，
 * 提醒内容如：“xxxxxx入库单付款截止日期为xxxxx,需及时补充付款记录”，待付款/收款记录（开票/收票）完成后，系统提示才取消
 */
@Component
@RequiredArgsConstructor
public class QlFinPaymentReceiverNoticeScheduled {

    private final QlWarehousingMapper qlWarehousingMapper;

    private final QlOutboundMapper qlOutboundMapper;

    private final SysNoticeMapper sysNoticeMapper;



    @Scheduled(cron = "0 35 23 * * ?")
    public void WarehousingExpireScheduled() {

        List<QlWarehousing> qlWarehousings = qlWarehousingMapper.getWarehousingExpireScheduled();
        List<SysNotice> sysNotices = new ArrayList<>();
        for (QlWarehousing qlWarehousing : qlWarehousings) {
            Date lastPaymentDate = qlWarehousing.getLastPaymentDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(lastPaymentDate);
            SysNotice sysNotice = new SysNotice();
            sysNotice.setNoticeContent("入库单编号:["+qlWarehousing.getWarehousingCode()  +"]入库单付款截止日期为["+ format+"]到期，需及时补充付款记录”。");
            sysNotice.setNoticeTitle("付款到期提醒");
            sysNotice.setNoticeType("3");//预警
            sysNotice.setReadStatus("0");//
            sysNotice.setReceiveId(qlWarehousing.getWarehousingUserId());
            sysNotice.setReceiveName(qlWarehousing.getWarehousingUsername());
            sysNotice.setExpirationDate(qlWarehousing.getLastPaymentDate());
            sysNotices.add(sysNotice);
        }
        sysNoticeMapper.insertBatch(sysNotices);
        System.out.println(qlWarehousings);
    }



    @Scheduled(cron = "0 25 23 * * ?")
    public void OutboundExpireScheduled() {

        List<QlOutbound> qlOutbounds = qlOutboundMapper.getQlOutboundExpireScheduled();
        List<SysNotice> sysNotices = new ArrayList<>();
        for (QlOutbound qlOutbound : qlOutbounds) {
            Date lastReceivableDate = qlOutbound.getLastReceivableDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(lastReceivableDate);
            SysNotice sysNotice = new SysNotice();
            sysNotice.setNoticeContent("出库单编号:["+qlOutbound.getOutboundCode()  +"]出库收款截止日期为["+ format+"]到期，需及时补充收款记录”。");
            sysNotice.setNoticeTitle("收款到期提醒");
            sysNotice.setNoticeType("3");//预警
            sysNotice.setReadStatus("0");//
            sysNotice.setReceiveId(qlOutbound.getOutboundReleaseuserId());
            sysNotice.setReceiveName(qlOutbound.getOutboundUsername());
            sysNotice.setExpirationDate(qlOutbound.getLastReceivableDate());
            sysNotices.add(sysNotice);
        }
        sysNoticeMapper.insertBatch(sysNotices);
    }

}
