package com.ruoyi.jianguan.common.domain.dto;

public class SafeGongQugroupDTO {

   private String gongquname;

   private int gongquid;

   private int count;

   private int finish;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }
}
