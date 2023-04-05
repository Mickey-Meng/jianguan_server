package com.ruoyi.jianguan.common.domain.dto;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/21 5:41 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class CheckCount implements Comparable<CheckCount>{
    // 总数
    private Integer count;
    private Integer finish;
    private Integer todayFinish;
    private Integer dalay;
    private String type;
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Integer getTodayFinish() {
        return todayFinish;
    }

    public void setTodayFinish(Integer todayFinish) {
        this.todayFinish = todayFinish;
    }

    public Integer getDalay() {
        return dalay;
    }

    public void setDalay(Integer dalay) {
        this.dalay = dalay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(CheckCount o) {
        return o.getFinish()-this.finish;
    }
}
