package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums;

public enum EarthAndStoneEnum
{
    EARTH("\u573a\u5730\u7efc\u5408\u571f\u65b9"),
    STONE("\u573a\u5730\u7efc\u5408\u77f3\u65b9");

    String name;

    private EarthAndStoneEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
