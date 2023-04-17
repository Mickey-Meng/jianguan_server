package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums;

public enum CiLandScapeGreeningWorkEnum
{
    PARK_GREENING("\u516c\u56ed\u7eff\u5316"),
    GREEN_BUFFER("\u9632\u62a4\u7eff\u5730"),
    SQUARE("\u5e7f\u573a");

    String name;

    private CiLandScapeGreeningWorkEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
