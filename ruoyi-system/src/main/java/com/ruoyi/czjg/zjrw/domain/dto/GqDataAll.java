package com.ruoyi.czjg.zjrw.domain.dto;

public class GqDataAll implements Comparable<GqDataAll> {
    private String name;
    private int sort;
    private Object object;

    public GqDataAll(){

    }

    public GqDataAll(String name, int sort, Object object) {
        this.name = name;
        this.sort = sort;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public int compareTo(GqDataAll o) {
        return this.sort-o.sort;
    }
}
