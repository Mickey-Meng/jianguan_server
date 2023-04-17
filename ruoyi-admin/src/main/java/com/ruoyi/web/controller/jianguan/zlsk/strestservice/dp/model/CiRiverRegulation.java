package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class CiRiverRegulation extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u6cb3\u9053\u9762\u79ef(km)")
    private Double riverArea;
    @ApiModelProperty("\u5355\u4ef7(\u5143/km)")
    private Double unitPrice;
    @ApiModelProperty("\u5efa\u5b89\u5de5\u7a0b\u8d39(\u4e07\u5143)")
    private Double projectCost;

    public Double getRiverArea() {
        return this.riverArea;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public Double getProjectCost() {
        return this.projectCost;
    }

    public CiRiverRegulation setRiverArea(final Double riverArea) {
        this.riverArea = riverArea;
        return this;
    }

    public CiRiverRegulation setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public CiRiverRegulation setProjectCost(final Double projectCost) {
        this.projectCost = projectCost;
        return this;
    }

    @Override
    public String toString() {
        return "CiRiverRegulation(riverArea=" + this.getRiverArea() + ", unitPrice=" + this.getUnitPrice() + ", projectCost=" + this.getProjectCost() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CiRiverRegulation)) {
            return false;
        }
        final CiRiverRegulation other = (CiRiverRegulation)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$riverArea = this.getRiverArea();
        final Object other$riverArea = other.getRiverArea();
        Label_0065: {
            if (this$riverArea == null) {
                if (other$riverArea == null) {
                    break Label_0065;
                }
            }
            else if (this$riverArea.equals(other$riverArea)) {
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
        return other instanceof CiRiverRegulation;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $riverArea = this.getRiverArea();
        result = result * 59 + (($riverArea == null) ? 43 : $riverArea.hashCode());
        final Object $unitPrice = this.getUnitPrice();
        result = result * 59 + (($unitPrice == null) ? 43 : $unitPrice.hashCode());
        final Object $projectCost = this.getProjectCost();
        result = result * 59 + (($projectCost == null) ? 43 : $projectCost.hashCode());
        return result;
    }
}
