package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.enums;

public enum RateEnum
{
    OTHER_FEE_RATE(Double.valueOf(0.12)),
    BUDGET_RESERVE_RATE(Double.valueOf(0.1)),
    LAD_UNFORESEEN_RATE(Double.valueOf(0.1));

    Double rate;

    private RateEnum(final Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return this.rate;
    }
}
