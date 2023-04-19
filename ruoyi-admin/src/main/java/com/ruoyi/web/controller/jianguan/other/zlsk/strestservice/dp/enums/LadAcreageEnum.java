package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.enums;

public enum LadAcreageEnum
{
    CAPITE_COMPENSATED_AREA(Double.valueOf(60.0)),
    PLOT_RATIO(Double.valueOf(2.0));

    private Double value;

    private LadAcreageEnum(final Double value) {
        this.value = value;
    }

    public Double getValue() {
        return this.value;
    }
}
