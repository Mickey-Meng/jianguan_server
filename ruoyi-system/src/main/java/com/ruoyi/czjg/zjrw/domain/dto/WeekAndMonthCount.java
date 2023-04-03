package com.ruoyi.czjg.zjrw.domain.dto;

import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/23 3:30 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class WeekAndMonthCount {
    private List<WeekData> weeks;
    private WeekData month;

    public List<WeekData> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<WeekData> weeks) {
        this.weeks = weeks;
    }

    public WeekData getMonth() {
        return month;
    }

    public void setMonth(WeekData month) {
        this.month = month;
    }
}
