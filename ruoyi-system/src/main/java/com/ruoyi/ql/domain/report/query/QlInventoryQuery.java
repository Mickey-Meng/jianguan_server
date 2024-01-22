package com.ruoyi.ql.domain.report.query;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class QlInventoryQuery  {

    /**
     * 起始月
     */
    private String startMonth;

    /**
     * 结束月
     */
    private String endMonth;


    /**
     * 起始日期
     */
    private LocalDate startDate;

    /**
     * 结束日期
     */
    private LocalDate endDate;


    /**
     * 货物id
     */
    private String goodsId;


    private List<String> goodsIds;

}