package com.ruoyi.czjg.zjrw.domain.entity;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/11/11 上午10:23
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class SafeCheckData {

    private int checkFirstId;
    private String checkFirstName;
    private int nodo;
    private int nodelay;
    private int nocheck;
    private int finish;
    private int countall;

    public int getCheckFirstId() {
        return checkFirstId;
    }

    public void setCheckFirstId(int checkFirstId) {
        this.checkFirstId = checkFirstId;
    }

    public String getCheckFirstName() {
        return checkFirstName;
    }

    public void setCheckFirstName(String checkFirstName) {
        this.checkFirstName = checkFirstName;
    }

    public int getNodo() {
        return nodo;
    }

    public void setNodo(int nodo) {
        this.nodo = nodo;
    }

    public int getNodelay() {
        return nodelay;
    }

    public void setNodelay(int nodelay) {
        this.nodelay = nodelay;
    }

    public int getNocheck() {
        return nocheck;
    }

    public void setNocheck(int nocheck) {
        this.nocheck = nocheck;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getCountall() {
        return countall;
    }

    public void setCountall(int countall) {
        this.countall = countall;
    }
}
