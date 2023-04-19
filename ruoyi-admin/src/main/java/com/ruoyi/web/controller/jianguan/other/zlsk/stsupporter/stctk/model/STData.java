package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model;

public class STData<T> extends STStatus
{
    private T data;

    public STData() {
    }

    public STData(final T a) {
        this.data = data;
    }

    public STData(final int n, final String s, final T a) {
        super(n, s);
        this.data = a;
    }

    public T getData() {
        return this.data;
    }

    public void setData(final T a) {
        this.data = a;
    }
}
