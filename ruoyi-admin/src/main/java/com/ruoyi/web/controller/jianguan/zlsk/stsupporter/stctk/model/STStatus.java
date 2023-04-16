package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model;

public class STStatus
{
    private int a;
    private String b;
    private String c;
    private String d;

    public STStatus() {
        this.a = 0;
        this.b = null;
    }

    public STStatus(final int a) {
        this.a = a;
        this.b = null;
    }

    public STStatus(final int a, final String b) {
        this.a = a;
        this.b = b;
    }

    public int getMeow() {
        return this.a;
    }

    public void setMeow(final int a) {
        this.a = a;
    }

    public String getMsg() {
        return this.b;
    }

    public void setMsg(final String b) {
        this.b = b;
    }

    public String getToken() {
        return this.c;
    }

    public void setToken(final String c) {
        this.c = c;
    }

    public String getTime() {
        return this.d;
    }

    public void setTime(final String d) {
        this.d = d;
    }

    public void setStatus(final STStatus stStatus) {
        this.setMeow(stStatus.getMeow());
        this.setMsg(stStatus.getMsg());
        this.setToken(stStatus.getToken());
        this.setTime(stStatus.getTime());
    }
}
