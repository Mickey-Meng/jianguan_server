package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class CiLandscapeGreeningWork extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u5de5\u7a0b\u91cf(m2)")
    private Double workAmount;
    @ApiModelProperty("\u5355\u4ef7\u6307\u6807(\u5143/m2)")
    private Double unitPrice;
    @ApiModelProperty("\u5efa\u5b89\u5de5\u7a0b\u8d39(\u4e07\u5143)")
    private Double projectCost;

    public Double getWorkAmount() {
        return this.workAmount;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public Double getProjectCost() {
        return this.projectCost;
    }

    public CiLandscapeGreeningWork setWorkAmount(final Double workAmount) {
        this.workAmount = workAmount;
        return this;
    }

    public CiLandscapeGreeningWork setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public CiLandscapeGreeningWork setProjectCost(final Double projectCost) {
        this.projectCost = projectCost;
        return this;
    }

    @Override
    public String toString() {
        return "CiLandscapeGreeningWork(workAmount=" + this.getWorkAmount() + ", unitPrice=" + this.getUnitPrice() + ", projectCost=" + this.getProjectCost() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CiLandscapeGreeningWork)) {
            return false;
        }
        final CiLandscapeGreeningWork other = (CiLandscapeGreeningWork)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$workAmount = this.getWorkAmount();
        final Object other$workAmount = other.getWorkAmount();
        Label_0065: {
            if (this$workAmount == null) {
                if (other$workAmount == null) {
                    break Label_0065;
                }
            }
            else if (this$workAmount.equals(other$workAmount)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$unitPrice = this.getUnitPrice();
        final Object other$unitPrice = other.getUnitPrice();
        Label_0102: {
            if (this$unitPrice == null) {
                if (other$unitPrice == null) {
                    break Label_0102;
                }
            }
            else if (this$unitPrice.equals(other$unitPrice)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$projectCost = this.getProjectCost();
        final Object other$projectCost = other.getProjectCost();
        if (this$projectCost == null) {
            if (other$projectCost == null) {
                return true;
            }
        }
        else if (this$projectCost.equals(other$projectCost)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof CiLandscapeGreeningWork;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $workAmount = this.getWorkAmount();
        result = result * 59 + (($workAmount == null) ? 43 : $workAmount.hashCode());
        final Object $unitPrice = this.getUnitPrice();
        result = result * 59 + (($unitPrice == null) ? 43 : $unitPrice.hashCode());
        final Object $projectCost = this.getProjectCost();
        result = result * 59 + (($projectCost == null) ? 43 : $projectCost.hashCode());
        return result;
    }
}
