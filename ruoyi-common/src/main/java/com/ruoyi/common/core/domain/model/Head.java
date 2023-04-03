package com.ruoyi.common.core.domain.model;

public class Head implements Comparable<Head>{
    private String name;
    private String code;
    private int sort;

    public Head(String name, String code, int sort) {
        this.name = name;
        this.code = code;
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public int compareTo(Head o) {
        return this.sort-o.sort;
    }
}
