package com.ruoyi.web.controller.jianguan.zlsk.stsupporter.stctk.model;

public class EntityListEx<T, E> extends EntityList<T>
{
    private E a;

    public EntityListEx() {
        this.a = null;
    }

    public E getInfo() {
        return this.a;
    }

    public void setInfo(final E a) {
        this.a = a;
    }
}
