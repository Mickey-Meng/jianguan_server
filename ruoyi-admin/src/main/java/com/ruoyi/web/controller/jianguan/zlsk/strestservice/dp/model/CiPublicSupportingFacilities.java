package com.ruoyi.web.controller.jianguan.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class CiPublicSupportingFacilities extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u7528\u5730\u9762\u79ef")
    private Double landArea;
    @ApiModelProperty("\u5bb9\u79ef\u7387")
    private Double plotRatio;
    @ApiModelProperty("\u5efa\u7b51\u9762\u79ef(m2)")
    private Double buildingArea;
    @ApiModelProperty("\u5355\u4ef7\u6307\u6807(\u5143/m2)")
    private Double unitPrice;
    @ApiModelProperty("\u5efa\u5b89\u5de5\u7a0b\u8d39(\u4e07\u5143)")
    private Double projectCost;

    public Double getLandArea() {
        return this.landArea;
    }

    public Double getPlotRatio() {
        return this.plotRatio;
    }

    public Double getBuildingArea() {
        return this.buildingArea;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public Double getProjectCost() {
        return this.projectCost;
    }

    public CiPublicSupportingFacilities setLandArea(final Double landArea) {
        this.landArea = landArea;
        return this;
    }

    public CiPublicSupportingFacilities setPlotRatio(final Double plotRatio) {
        this.plotRatio = plotRatio;
        return this;
    }

    public CiPublicSupportingFacilities setBuildingArea(final Double buildingArea) {
        this.buildingArea = buildingArea;
        return this;
    }

    public CiPublicSupportingFacilities setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public CiPublicSupportingFacilities setProjectCost(final Double projectCost) {
        this.projectCost = projectCost;
        return this;
    }

    @Override
    public String toString() {
        return "CiPublicSupportingFacilities(landArea=" + this.getLandArea() + ", plotRatio=" + this.getPlotRatio() + ", buildingArea=" + this.getBuildingArea() + ", unitPrice=" + this.getUnitPrice() + ", projectCost=" + this.getProjectCost() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CiPublicSupportingFacilities)) {
            return false;
        }
        final CiPublicSupportingFacilities other = (CiPublicSupportingFacilities)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$landArea = this.getLandArea();
        final Object other$landArea = other.getLandArea();
        Label_0065: {
            if (this$landArea == null) {
                if (other$landArea == null) {
                    break Label_0065;
                }
            }
            else if (this$landArea.equals(other$landArea)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$plotRatio = this.getPlotRatio();
        final Object other$plotRatio = other.getPlotRatio();
        Label_0102: {
            if (this$plotRatio == null) {
                if (other$plotRatio == null) {
                    break Label_0102;
                }
            }
            else if (this$plotRatio.equals(other$plotRatio)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$buildingArea = this.getBuildingArea();
        final Object other$buildingArea = other.getBuildingArea();
        Label_0139: {
            if (this$buildingArea == null) {
                if (other$buildingArea == null) {
                    break Label_0139;
                }
            }
            else if (this$buildingArea.equals(other$buildingArea)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$unitPrice = this.getUnitPrice();
        final Object other$unitPrice = other.getUnitPrice();
        Label_0176: {
            if (this$unitPrice == null) {
                if (other$unitPrice == null) {
                    break Label_0176;
                }
            }
            else if (this$unitPrice.equals(other$unitPrice)) {
                break Label_0176;
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
        return other instanceof CiPublicSupportingFacilities;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $landArea = this.getLandArea();
        result = result * 59 + (($landArea == null) ? 43 : $landArea.hashCode());
        final Object $plotRatio = this.getPlotRatio();
        result = result * 59 + (($plotRatio == null) ? 43 : $plotRatio.hashCode());
        final Object $buildingArea = this.getBuildingArea();
        result = result * 59 + (($buildingArea == null) ? 43 : $buildingArea.hashCode());
        final Object $unitPrice = this.getUnitPrice();
        result = result * 59 + (($unitPrice == null) ? 43 : $unitPrice.hashCode());
        final Object $projectCost = this.getProjectCost();
        result = result * 59 + (($projectCost == null) ? 43 : $projectCost.hashCode());
        return result;
    }
}
