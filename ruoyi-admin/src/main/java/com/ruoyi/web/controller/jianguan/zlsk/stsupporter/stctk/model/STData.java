package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model;

public class STData<T> extends STStatus
{
    private T a;

    public STData() {
    }

    public STData(final T a) {
        this.a = a;
    }

    public STData(final int n, final String s, final T a) {
        super(n, s);
        this.a = a;
    }

    public T getData() {
        return this.a;
    }

    public void setData(final T a) {
        this.a = a;
    }
}
