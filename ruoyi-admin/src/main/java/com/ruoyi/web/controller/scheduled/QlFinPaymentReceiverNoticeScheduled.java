package com.ruoyi.web.controller.scheduled;

import com.ruoyi.ql.domain.QlContractInfoSale;
import com.ruoyi.ql.domain.QlOutbound;
import com.ruoyi.ql.domain.QlWarehousing;
import com.ruoyi.ql.mapper.QlContractInfoSaleMapper;
import com.ruoyi.ql.mapper.QlOutboundMapper;
import com.ruoyi.ql.mapper.QlWarehousingMapper;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.bo.SysNoticeBo;
import com.ruoyi.system.mapper.SysNoticeMapper;
import com.ruoyi.system.service.impl.SysNoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final SysNoticeServiceImpl sysNoticeService;


    @Scheduled(cron = "0 35 23 * * ?")
    public void WarehousingExpireScheduled() {
        SysNoticeBo sysNoticeBo = new SysNoticeBo();
        sysNoticeBo.setBusinessType("warehousing");
        List<SysNotice> warehousingSysNotice = sysNoticeService.selectNoticeList(sysNoticeBo);
        Map<String, SysNotice> warehousingMap = warehousingSysNotice
                .stream()
                .collect(Collectors.toMap(sysNotice -> sysNotice.getBusinessId() + "##" + sysNoticeBo.getBusinessType(),
                        sysNotice -> sysNotice, (oldObj, newObj) -> oldObj));
        // 过滤已经有付款记录的入库单

        List<QlWarehousing> qlWarehousings = qlWarehousingMapper.getWarehousingExpireScheduled();
        List<SysNotice> sysNotices = new ArrayList<>();
        for (QlWarehousing qlWarehousing : qlWarehousings) {
            if(warehousingMap.containsKey(qlWarehousing.getId() + "##" + "warehousing")) {
                continue;
            }
            Date lastPaymentDate = qlWarehousing.getLastPaymentDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(lastPaymentDate);
            SysNotice sysNotice = new SysNotice();
            sysNotice.setNoticeContent("入库单编号:[" + qlWarehousing.getWarehousingCode() + "]入库单付款截止日期为[" + format + "]到期，需及时补充付款记录”。");
            sysNotice.setNoticeTitle("付款到期提醒");
            sysNotice.setNoticeType("3");//预警
            sysNotice.setReadStatus("0");//
            sysNotice.setReceiveId(qlWarehousing.getWarehousingUserId());
            sysNotice.setReceiveName(qlWarehousing.getWarehousingUsername());
            sysNotice.setExpirationDate(qlWarehousing.getLastPaymentDate());
            sysNotice.setBusinessId(qlWarehousing.getId());
            sysNotice.setBusinessType("warehousing");
            sysNotices.add(sysNotice);
        }
        sysNoticeMapper.insertBatch(sysNotices);
        System.out.println(qlWarehousings);
    }


    @Scheduled(cron = "0 25 23 * * ?")
    public void OutboundExpireScheduled() {

        SysNoticeBo sysNoticeBo = new SysNoticeBo();
        sysNoticeBo.setBusinessType("outbound");
        List<SysNotice> warehousingSysNotice = sysNoticeService.selectNoticeList(sysNoticeBo);
        Map<String, SysNotice> warehousingMap = warehousingSysNotice
                .stream()
                .collect(Collectors.toMap(sysNotice -> sysNotice.getBusinessId() + "##" + sysNoticeBo.getBusinessType(),
                        sysNotice -> sysNotice, (oldObj, newObj) -> oldObj));
        // 过滤已经有收款数据的出库单
        List<QlOutbound> qlOutbounds = qlOutboundMapper.getQlOutboundExpireScheduled();
        List<SysNotice> sysNotices = new ArrayList<>();
        for (QlOutbound qlOutbound : qlOutbounds) {
            if(warehousingMap.containsKey(qlOutbound.getId() + "##" + "outbound")) {
                continue;
            }
            Date lastReceivableDate = qlOutbound.getLastReceivableDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(lastReceivableDate);
            SysNotice sysNotice = new SysNotice();
            sysNotice.setNoticeContent("出库单编号:[" + qlOutbound.getOutboundCode() + "]出库收款截止日期为[" + format + "]到期，需及时补充收款记录”。");
            sysNotice.setNoticeTitle("收款到期提醒");
            sysNotice.setNoticeType("3");//预警
            sysNotice.setReadStatus("0");//
            sysNotice.setReceiveId(qlOutbound.getOutboundReleaseuserId());
            sysNotice.setReceiveName(qlOutbound.getOutboundUsername());
            sysNotice.setExpirationDate(qlOutbound.getLastReceivableDate());
            sysNotice.setBusinessId(qlOutbound.getId());
            sysNotice.setBusinessType("outbound");
            sysNotices.add(sysNotice);
        }
        sysNoticeMapper.insertBatch(sysNotices);
    }

}
