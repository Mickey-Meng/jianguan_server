package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model;

import java.util.*;

public class EntityList<T>
{
    private int a;
    private int b;
    private int c;
    private int d;
    private String e;
    private List<T> f;

    public EntityList() {
        this.a = -1;
        this.b = 0;
        this.c = -1;
        this.d = 0;
        this.e = "";
        this.f = null;
        this.f = new ArrayList<T>();
    }

    public int getMeow() {
        return this.a;
    }

    public void setMeow(final int a) {
        this.a = a;
    }

    public int getPageSize() {
        return this.b;
    }

    public void setPageSize(final int b) {
        this.b = b;
    }

    public int getPageNum() {
        return this.c;
    }

    public void setPageNum(final int c) {
        this.c = c;
    }

    public int getTotal() {
        return this.d;
    }

    public void setTotal(final int d) {
        this.d = d;
    }

    public String getMsg() {
        return this.e;
    }

    public void setMsg(final String e) {
        this.e = e;
    }

    public List<T> getGetMe() {
        return this.f;
    }

    public void setGetMe(final List<T> f) {
        this.f = f;
    }
}
