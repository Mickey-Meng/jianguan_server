package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model;

import io.swagger.annotations.*;

public class DpOtherCost extends BaseEntity
{
    @ApiModelProperty("\u8d39\u7528\u91d1\u989d")
    private Double cost;
    @ApiModelProperty("\u8d39\u7528\u7c7b\u578b(1\u5de5\u7a0b\u5efa\u8bbe\u5176\u4ed6\u8d39,2\u9884\u5907\u8d39,3\u7ba1\u7406\u8d39\u7528,4\u8d22\u52a1\u8d39\u7528)")
    private Integer costType;

    public Double getCost() {
        return this.cost;
    }

    public Integer getCostType() {
        return this.costType;
    }

    public DpOtherCost setCost(final Double cost) {
        this.cost = cost;
        return this;
    }

    public DpOtherCost setCostType(final Integer costType) {
        this.costType = costType;
        return this;
    }

    @Override
    public String toString() {
        return "DpOtherCost(cost=" + this.getCost() + ", costType=" + this.getCostType() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DpOtherCost)) {
            return false;
        }
        final DpOtherCost other = (DpOtherCost)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$cost = this.getCost();
        final Object other$cost = other.getCost();
        Label_0065: {
            if (this$cost == null) {
                if (other$cost == null) {
                    break Label_0065;
                }
            }
            else if (this$cost.equals(other$cost)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$costType = this.getCostType();
        final Object other$costType = other.getCostType();
        if (this$costType == null) {
            if (other$costType == null) {
                return true;
            }
        }
        else if (this$costType.equals(other$costType)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof DpOtherCost;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $cost = this.getCost();
        result = result * 59 + (($cost == null) ? 43 : $cost.hashCode());
        final Object $costType = this.getCostType();
        result = result * 59 + (($costType == null) ? 43 : $costType.hashCode());
        return result;
    }
}
