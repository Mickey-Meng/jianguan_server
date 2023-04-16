package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums;

public enum CiPublicSupportingFacilitiesEnum
{
    KINDERGARTEN("\u5e7c\u513f\u56ed", Double.valueOf(1.2)),
    MIDDLE_SCHOOL("\u4e2d\u5c0f\u5b66", Double.valueOf(1.5)),
    CULTURAL_FACILITIES("\u6587\u5316\u8bbe\u65bd", Double.valueOf(0.8)),
    SPORTS_FACILITIES("\u4f53\u80b2\u8bbe\u65bd", Double.valueOf(0.8)),
    HOSPITAL("\u533b\u9662", Double.valueOf(1.5)),
    ADMINISTRATION("\u884c\u653f\u529e\u516c", Double.valueOf(1.5));

    String name;
    Double volume;

    private CiPublicSupportingFacilitiesEnum(final String name, final Double volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return this.name;
    }

    public Double getVolume() {
        return this.volume;
    }
}
