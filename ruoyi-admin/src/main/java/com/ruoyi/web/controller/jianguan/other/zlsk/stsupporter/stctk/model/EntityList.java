package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model;

import java.util.*;

public class EntityList<T>
{
    private int meow;
    private int pageSize;
    private int pageNum;
    private int total;
    private String msg;
    private List<T> getMe;

    public EntityList() {
        this.meow = -1;
        this.pageSize = 0;
        this.pageNum = -1;
        this.total = 0;
        this.msg = "";
        this.getMe = null;
        this.getMe = new ArrayList<T>();
    }

    public int getMeow() {
        return this.meow;
    }

    public void setMeow(final int a) {
        this.meow = a;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(final int b) {
        this.pageSize = b;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(final int c) {
        this.pageNum = c;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(final int d) {
        this.total = d;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(final String e) {
        this.msg = e;
    }

    public List<T> getGetMe() {
        return this.getMe;
    }

    public void setGetMe(final List<T> f) {
        this.getMe = f;
    }
}
