package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class CiEarthStoneWork extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u5e73\u5747\u6807\u9ad8")
    private Double averageElevation;
    @ApiModelProperty("\u5de5\u7a0b\u91cf(m3)")
    private Double workAmount;
    @ApiModelProperty("\u5355\u4ef7\u6307\u6807(m3)")
    private Double unitPrice;
    @ApiModelProperty("\u5efa\u5b89\u5de5\u7a0b\u8d39(\u4e07\u5143)")
    private Double projectCost;

    public Double getAverageElevation() {
        return this.averageElevation;
    }

    public Double getWorkAmount() {
        return this.workAmount;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public Double getProjectCost() {
        return this.projectCost;
    }

    public CiEarthStoneWork setAverageElevation(final Double averageElevation) {
        this.averageElevation = averageElevation;
        return this;
    }

    public CiEarthStoneWork setWorkAmount(final Double workAmount) {
        this.workAmount = workAmount;
        return this;
    }

    public CiEarthStoneWork setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public CiEarthStoneWork setProjectCost(final Double projectCost) {
        this.projectCost = projectCost;
        return this;
    }

    @Override
    public String toString() {
        return "CiEarthStoneWork(averageElevation=" + this.getAverageElevation() + ", workAmount=" + this.getWorkAmount() + ", unitPrice=" + this.getUnitPrice() + ", projectCost=" + this.getProjectCost() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CiEarthStoneWork)) {
            return false;
        }
        final CiEarthStoneWork other = (CiEarthStoneWork)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$averageElevation = this.getAverageElevation();
        final Object other$averageElevation = other.getAverageElevation();
        Label_0065: {
            if (this$averageElevation == null) {
                if (other$averageElevation == null) {
                    break Label_0065;
                }
            }
            else if (this$averageElevation.equals(other$averageElevation)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$workAmount = this.getWorkAmount();
        final Object other$workAmount = other.getWorkAmount();
        Label_0102: {
            if (this$workAmount == null) {
                if (other$workAmount == null) {
                    break Label_0102;
                }
            }
            else if (this$workAmount.equals(other$workAmount)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$unitPrice = this.getUnitPrice();
        final Object other$unitPrice = other.getUnitPrice();
        Label_0139: {
            if (this$unitPrice == null) {
                if (other$unitPrice == null) {
                    break Label_0139;
                }
            }
            else if (this$unitPrice.equals(other$unitPrice)) {
                break Label_0139;
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
        return other instanceof CiEarthStoneWork;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $averageElevation = this.getAverageElevation();
        result = result * 59 + (($averageElevation == null) ? 43 : $averageElevation.hashCode());
        final Object $workAmount = this.getWorkAmount();
        result = result * 59 + (($workAmount == null) ? 43 : $workAmount.hashCode());
        final Object $unitPrice = this.getUnitPrice();
        result = result * 59 + (($unitPrice == null) ? 43 : $unitPrice.hashCode());
        final Object $projectCost = this.getProjectCost();
        result = result * 59 + (($projectCost == null) ? 43 : $projectCost.hashCode());
        return result;
    }
}
