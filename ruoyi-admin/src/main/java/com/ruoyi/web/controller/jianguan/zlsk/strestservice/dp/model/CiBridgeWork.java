package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class CiBridgeWork extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u6865\u6881\u7b49\u7ea7(1\u5927\u6865,2\u4e2d\u6865,3\u5c0f\u6865)")
    private Integer bridgeGrade;
    @ApiModelProperty("\u6865\u6881\u9762\u79ef(m2)")
    private Double bridgeArea;
    @ApiModelProperty("\u6865\u6881\u5355\u4ef7(\u5143/m2)")
    private Double unitPrice;
    @ApiModelProperty("\u5efa\u5b89\u5de5\u7a0b\u8d39(\u4e07\u5143)")
    private Double projectCost;

    public Integer getBridgeGrade() {
        return this.bridgeGrade;
    }

    public Double getBridgeArea() {
        return this.bridgeArea;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public Double getProjectCost() {
        return this.projectCost;
    }

    public CiBridgeWork setBridgeGrade(final Integer bridgeGrade) {
        this.bridgeGrade = bridgeGrade;
        return this;
    }

    public CiBridgeWork setBridgeArea(final Double bridgeArea) {
        this.bridgeArea = bridgeArea;
        return this;
    }

    public CiBridgeWork setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public CiBridgeWork setProjectCost(final Double projectCost) {
        this.projectCost = projectCost;
        return this;
    }

    @Override
    public String toString() {
        return "CiBridgeWork(bridgeGrade=" + this.getBridgeGrade() + ", bridgeArea=" + this.getBridgeArea() + ", unitPrice=" + this.getUnitPrice() + ", projectCost=" + this.getProjectCost() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CiBridgeWork)) {
            return false;
        }
        final CiBridgeWork other = (CiBridgeWork)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$bridgeGrade = this.getBridgeGrade();
        final Object other$bridgeGrade = other.getBridgeGrade();
        Label_0065: {
            if (this$bridgeGrade == null) {
                if (other$bridgeGrade == null) {
                    break Label_0065;
                }
            }
            else if (this$bridgeGrade.equals(other$bridgeGrade)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$bridgeArea = this.getBridgeArea();
        final Object other$bridgeArea = other.getBridgeArea();
        Label_0102: {
            if (this$bridgeArea == null) {
                if (other$bridgeArea == null) {
                    break Label_0102;
                }
            }
            else if (this$bridgeArea.equals(other$bridgeArea)) {
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
        return other instanceof CiBridgeWork;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $bridgeGrade = this.getBridgeGrade();
        result = result * 59 + (($bridgeGrade == null) ? 43 : $bridgeGrade.hashCode());
        final Object $bridgeArea = this.getBridgeArea();
        result = result * 59 + (($bridgeArea == null) ? 43 : $bridgeArea.hashCode());
        final Object $unitPrice = this.getUnitPrice();
        result = result * 59 + (($unitPrice == null) ? 43 : $unitPrice.hashCode());
        final Object $projectCost = this.getProjectCost();
        result = result * 59 + (($projectCost == null) ? 43 : $projectCost.hashCode());
        return result;
    }
}
