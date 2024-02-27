package com.ruoyi.web.controller.ql;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.web.controller.scheduled.QlFinPaymentReceiverNoticeScheduled;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 省市区
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/supply/chain/test")
public class SupplyChainTestController extends BaseController {

    private final QlFinPaymentReceiverNoticeScheduled qlFinPaymentReceiverNoticeScheduled;

    @GetMapping("/warehousingExpireScheduled")
    public void warehousingExpireScheduled() {
        qlFinPaymentReceiverNoticeScheduled.WarehousingExpireScheduled();
    }


    @GetMapping("/outboundExpireScheduled")
    public void outboundExpireScheduled() {
        qlFinPaymentReceiverNoticeScheduled.OutboundExpireScheduled();
    }

}
