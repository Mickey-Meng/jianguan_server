package com.ruoyi.web.controller.ql;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.ql.domain.report.*;
import com.ruoyi.ql.domain.report.query.QlInventoryQuery;
import com.ruoyi.ql.domain.report.vo.InventoryDetail;
import com.ruoyi.ql.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工资管理
 *
 * @author ruoyi
 * @date 2022-12-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/jmreport")
public class QlSupplyChainReportController extends BaseController {

    private final IInventoryService inventoryService;

    @GetMapping("/inventory")
    public ReportResponse<List<InventoryDetail>> inventory(@RequestParam(value = "startMonth", required = false) String startMonth,
                                                           @RequestParam(value = "endMonth", required = false) String endMonth) {
        System.out.println("QlSupplyChainReportController.monthlySummary.startMonth--"+startMonth+"--"+endMonth);

        QlInventoryQuery inventoryQuery = new QlInventoryQuery();
        inventoryQuery.setStartMonth(startMonth);
        inventoryQuery.setEndMonth(endMonth);
        List<InventoryDetail> inventoryDetails = inventoryService.detail(inventoryQuery);

        return ReportResponse.ok(inventoryDetails);
    }

    @GetMapping("/monthlySummary")
    public ReportResponse<List<InventoryDetail>> monthlySummary(@RequestParam(value = "startMonth", required = false) String startMonth,
                                                           @RequestParam(value = "endMonth", required = false) String endMonth) {
        System.out.println("QlSupplyChainReportController.monthlySummary.startMonth--"+startMonth+"--"+endMonth);

        QlInventoryQuery inventoryQuery = new QlInventoryQuery();
        inventoryQuery.setStartMonth(startMonth);
        inventoryQuery.setEndMonth(endMonth);
        List<InventoryDetail> inventoryDetails = inventoryService.monthlySummary(inventoryQuery);

        return ReportResponse.ok(inventoryDetails);
    }

    @GetMapping("/annualSummary")
    public ReportResponse<List<InventoryDetail>> annualSummary(@RequestParam(value = "startMonth", required = false) String startMonth,
                                                                @RequestParam(value = "endMonth", required = false) String endMonth) {
        System.out.println("QlSupplyChainReportController.annualSummary.startMonth--"+startMonth+"--"+endMonth);

        QlInventoryQuery inventoryQuery = new QlInventoryQuery();
        inventoryQuery.setStartMonth(startMonth);
        inventoryQuery.setEndMonth(endMonth);
        List<InventoryDetail> inventoryDetails = inventoryService.annualSummary(inventoryQuery);

        return ReportResponse.ok(inventoryDetails);
    }

}
