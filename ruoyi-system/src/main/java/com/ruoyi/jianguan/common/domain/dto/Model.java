package com.ruoyi.jianguan.common.domain.dto;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/9 2:55 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class Model {
    private Boolean show;
    private String value;

    public Model() {

    }

    public Model(Boolean show, String value) {
        this.show = show;
        this.value = value;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
