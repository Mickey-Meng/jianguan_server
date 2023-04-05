package com.ruoyi.jianguan.common.domain.dto;

/**
 * @author : Chen_ZhiWei
 * @Date : Create file in 2022/9/1 17:13
 * @Version : 1.0
 * @Description :
 **/
public class TodayClockInCountDTO {

    private Integer all;

    private Integer clockInCount;

    private Integer notClockInCount;

    private Integer leaveCount;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Integer getClockInCount() {
        return clockInCount;
    }

    public void setClockInCount(Integer clockInCount) {
        this.clockInCount = clockInCount;
    }

    public Integer getNotClockInCount() {
        return notClockInCount;
    }

    public void setNotClockInCount(Integer notClockInCount) {
        this.notClockInCount = notClockInCount;
    }

    public Integer getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(Integer leaveCount) {
        this.leaveCount = leaveCount;
    }
}
