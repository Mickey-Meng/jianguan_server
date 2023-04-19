package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.enums;

public enum CostTypeEnum
{
    OTHER_FEE(Integer.valueOf(1), "\u5de5\u7a0b\u5efa\u8bbe\u5176\u4ed6\u8d39"),
    BUDGET_RESERVE(Integer.valueOf(2), "\u9884\u5907\u8d39"),
    ADMINISTRATIVE_EXPENSE(Integer.valueOf(3), "\u7ba1\u7406\u8d39\u7528"),
    FINANCIAL_EXPENSE(Integer.valueOf(4), "\u8d22\u52a1\u8d39\u7528");

    Integer typeId;
    String name;

    private CostTypeEnum(final Integer typeId, final String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public String getName() {
        return this.name;
    }
}
