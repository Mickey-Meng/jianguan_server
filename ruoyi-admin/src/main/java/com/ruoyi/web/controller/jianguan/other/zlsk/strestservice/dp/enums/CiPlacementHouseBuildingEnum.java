package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.enums;

public enum CiPlacementHouseBuildingEnum
{
    volume(Double.valueOf(2.0), "m?"),
    CompensatedAreaPerCapita(Double.valueOf(60.0), "m?");

    Double value;
    String unit;

    private CiPlacementHouseBuildingEnum(final Double value, final String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Double getValue() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }
}
