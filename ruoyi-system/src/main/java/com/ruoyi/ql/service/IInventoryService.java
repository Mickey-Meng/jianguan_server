package com.ruoyi.ql.service;

import com.ruoyi.ql.domain.report.query.QlInventoryQuery;
import com.ruoyi.ql.domain.report.vo.InventoryDetail;

import java.util.List;

public interface IInventoryService {

    /**
     * 库存明细
     * @param inventoryQuery
     * @return
     */
    List<InventoryDetail> detail(QlInventoryQuery inventoryQuery);

    /**
     * 月度汇总
     * @param inventoryQuery
     * @return
     */
    List<InventoryDetail> monthlySummary(QlInventoryQuery inventoryQuery);

    /**
     * 年度汇总
     * @param inventoryQuery
     * @return
     */
    List<InventoryDetail> annualSummary(QlInventoryQuery inventoryQuery);

}
