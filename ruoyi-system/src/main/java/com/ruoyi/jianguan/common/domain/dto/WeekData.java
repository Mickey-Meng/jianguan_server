package com.ruoyi.jianguan.common.domain.dto;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/23 3:14 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class WeekData {
    private String peroid;
    private Integer count;
    private Integer performance;

    public String getPeroid() {
        return peroid;
    }

    public void setPeroid(String peroid) {
        this.peroid = peroid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }
}
