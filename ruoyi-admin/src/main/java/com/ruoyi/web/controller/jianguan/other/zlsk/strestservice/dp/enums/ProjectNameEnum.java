package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.enums;

public enum ProjectNameEnum
{
    LAD("\u5f81\u5730\u62c6\u8fc1\u8d39\u7528"),
    EARTH_STONE("\u573a\u5730\u5e73\u6574\u53ca\u571f\u77f3\u65b9"),
    ROAD("\u9053\u8def\u5de5\u7a0b"),
    BRIDGE("\u6865\u6881\u5de5\u7a0b"),
    LANDSCAPE("\u666f\u89c2\u7eff\u5316\u5de5\u7a0b"),
    FACILITIES("\u516c\u5efa\u914d\u5957\u5de5\u7a0b"),
    RIVER("\u6cb3\u9053\u6cbb\u7406"),
    PLACEMENT_HOUSE("\u5b89\u7f6e\u623f\u5efa\u8bbe");

    String projectName;

    private ProjectNameEnum(final String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return this.projectName;
    }
}
