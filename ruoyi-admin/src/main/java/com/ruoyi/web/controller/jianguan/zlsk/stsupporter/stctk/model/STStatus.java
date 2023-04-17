package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model;

public class STStatus
{
    private int meow;
    private String msg;
    private String token;
    private String time;

    public STStatus() {
        this.meow = 0;
        this.msg = null;
    }

    public STStatus(final int a) {
        this.meow = a;
        this.msg = null;
    }

    public STStatus(final int a, final String b) {
        this.meow = a;
        this.msg = b;
    }

    public int getMeow() {
        return this.meow;
    }

    public void setMeow(final int a) {
        this.meow = a;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(final String b) {
        this.msg = b;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(final String c) {
        this.token = c;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(final String d) {
        this.time = d;
    }

    public void setStatus(final STStatus stStatus) {
        this.setMeow(stStatus.getMeow());
        this.setMsg(stStatus.getMsg());
        this.setToken(stStatus.getToken());
        this.setTime(stStatus.getTime());
    }
}
