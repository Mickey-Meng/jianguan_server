package com.ruoyi.jianguan.common.domain.entity;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/11/9 上午10:01
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class OperationData {
    private int startact;
    private int endact;
    private int countall;

    public int getStartcat() {
        return startact;
    }

    public void setStartcat(int startcat) {
        this.startact = startcat;
    }

    public int getEndact() {
        return endact;
    }

    public void setEndact(int endact) {
        this.endact = endact;
    }

    public int getCountall() {
        return countall;
    }

    public void setCountall(int countall) {
        this.countall = countall;
    }
}
