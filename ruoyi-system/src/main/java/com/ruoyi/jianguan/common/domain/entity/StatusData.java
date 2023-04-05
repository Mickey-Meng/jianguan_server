package com.ruoyi.jianguan.common.domain.entity;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/11/10 下午7:54
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class StatusData {
    public String projecttype;
    public String conponenttype;
    public String conponenttypename;
    public int finish;
    public int priod;
    public int countall;
    public int delay;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getConponenttype() {
        return conponenttype;
    }

    public void setConponenttype(String conponenttype) {
        this.conponenttype = conponenttype;
    }

    public String getConponenttypename() {
        return conponenttypename;
    }

    public void setConponenttypename(String conponenttypename) {
        this.conponenttypename = conponenttypename;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getPriod() {
        return priod;
    }

    public void setPriod(int priod) {
        this.priod = priod;
    }

    public int getCountall() {
        return countall;
    }

    public void setCountall(int countall) {
        this.countall = countall;
    }
}
