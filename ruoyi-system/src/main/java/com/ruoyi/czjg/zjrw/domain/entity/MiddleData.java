package com.ruoyi.czjg.zjrw.domain.entity;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/11/10 下午6:01
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class MiddleData {
    private String projecttype;
    private int finish;
    private int delay;
    private int today;
    private int countall;

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getToday() {
        return today;
    }

    public void setToday(int today) {
        this.today = today;
    }

    public int getCountall() {
        return countall;
    }

    public void setCountall(int countall) {
        this.countall = countall;
    }
}
