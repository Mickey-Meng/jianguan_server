package com.ruoyi.czjg.zjrw.domain.dto;

import java.util.Date;
import java.util.List;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/23 9:54 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class PerDayDetail {

    private Date uploadTime;
    private List<Integer> reconds;

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<Integer> getReconds() {
        return reconds;
    }

    public void setReconds(List<Integer> reconds) {
        this.reconds = reconds;
    }
}
