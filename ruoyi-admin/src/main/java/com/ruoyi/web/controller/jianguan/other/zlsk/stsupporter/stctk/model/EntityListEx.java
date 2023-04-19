package com.ruoyi.web.controller.jianguan.other.zlsk.stsupporter.stctk.model;

public class EntityListEx<T, E> extends EntityList<T>
{
    private E info;

    public EntityListEx() {
        this.info = null;
    }

    public E getInfo() {
        return this.info;
    }

    public void setInfo(final E a) {
        this.info = a;
    }
}
