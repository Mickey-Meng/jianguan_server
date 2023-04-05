package com.ruoyi.jianguan.common.domain.entity;

/**
 * @version : 1.0
 * @Author : chenzhiwei
 * @date : Create file in 2022/3/1 10:40
 * @description :
 **/
public class SafeGongQugroupOverdue {


    private String gongquname;
    private int gongquid;
    //超期
    private int overdue;

    public String getGongquname() {
        return gongquname;
    }

    public void setGongquname(String gongquname) {
        this.gongquname = gongquname;
    }

    public int getGongquid() {
        return gongquid;
    }

    public void setGongquid(int gongquid) {
        this.gongquid = gongquid;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }
}
