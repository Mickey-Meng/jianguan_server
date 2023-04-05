package com.ruoyi.jianguan.common.domain.dto;

import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/23 9:52 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class SafeCount {
    private String gongqu;
    private List<PerDayDetail>  dayDatas;

    public String getGongqu() {
        return gongqu;
    }

    public void setGongqu(String gongqu) {
        this.gongqu = gongqu;
    }

    public List<PerDayDetail> getDayDatas() {
        return dayDatas;
    }

    public void setDayDatas(List<PerDayDetail> dayDatas) {
        this.dayDatas = dayDatas;
    }
}
