package com.ruoyi.czjg.zjrw.domain.dto;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/24 10:40 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
//构件的统计数据
public class ConponentCountData {
    private String name ;
    private Integer count;
    private Float fang;

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

    public Float getFang() {
        return fang;
    }

    public void setFang(Float fang) {
        this.fang = fang;
    }
}
