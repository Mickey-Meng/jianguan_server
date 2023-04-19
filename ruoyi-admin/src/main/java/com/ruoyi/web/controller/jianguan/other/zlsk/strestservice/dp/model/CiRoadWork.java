package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class CiRoadWork extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u9053\u8def\u7b49\u7ea7(1\u4e3b\u5e72\u8def,2\u6b21\u5e72\u8def,3\u652f\u8def)")
    private Integer roadGrade;
    @ApiModelProperty("\u9053\u8def\u957f\u5ea6(m)")
    private Double roadLength;
    @ApiModelProperty("\u9053\u8def\u5bbd\u5ea6(m)")
    private Double roadWidth;
    @ApiModelProperty("\u9053\u8def\u9762\u79ef(m2)")
    private Double roadArea;
    @ApiModelProperty("\u9053\u8def\u5355\u4ef7(\u5143/m2)")
    private Double unitPrice;
    @ApiModelProperty("\u5efa\u5b89\u5de5\u7a0b\u8d39(\u4e07\u5143)")
    private Double projectCost;

    public Integer getRoadGrade() {
        return this.roadGrade;
    }

    public Double getRoadLength() {
        return this.roadLength;
    }

    public Double getRoadWidth() {
        return this.roadWidth;
    }

    public Double getRoadArea() {
        return this.roadArea;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public Double getProjectCost() {
        return this.projectCost;
    }

    public CiRoadWork setRoadGrade(final Integer roadGrade) {
        this.roadGrade = roadGrade;
        return this;
    }

    public CiRoadWork setRoadLength(final Double roadLength) {
        this.roadLength = roadLength;
        return this;
    }

    public CiRoadWork setRoadWidth(final Double roadWidth) {
        this.roadWidth = roadWidth;
        return this;
    }

    public CiRoadWork setRoadArea(final Double roadArea) {
        this.roadArea = roadArea;
        return this;
    }

    public CiRoadWork setUnitPrice(final Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public CiRoadWork setProjectCost(final Double projectCost) {
        this.projectCost = projectCost;
        return this;
    }

    @Override
    public String toString() {
        return "CiRoadWork(roadGrade=" + this.getRoadGrade() + ", roadLength=" + this.getRoadLength() + ", roadWidth=" + this.getRoadWidth() + ", roadArea=" + this.getRoadArea() + ", unitPrice=" + this.getUnitPrice() + ", projectCost=" + this.getProjectCost() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CiRoadWork)) {
            return false;
        }
        final CiRoadWork other = (CiRoadWork)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$roadGrade = this.getRoadGrade();
        final Object other$roadGrade = other.getRoadGrade();
        Label_0065: {
            if (this$roadGrade == null) {
                if (other$roadGrade == null) {
                    break Label_0065;
                }
            }
            else if (this$roadGrade.equals(other$roadGrade)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$roadLength = this.getRoadLength();
        final Object other$roadLength = other.getRoadLength();
        Label_0102: {
            if (this$roadLength == null) {
                if (other$roadLength == null) {
                    break Label_0102;
                }
            }
            else if (this$roadLength.equals(other$roadLength)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$roadWidth = this.getRoadWidth();
        final Object other$roadWidth = other.getRoadWidth();
        Label_0139: {
            if (this$roadWidth == null) {
                if (other$roadWidth == null) {
                    break Label_0139;
                }
            }
            else if (this$roadWidth.equals(other$roadWidth)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$roadArea = this.getRoadArea();
        final Object other$roadArea = other.getRoadArea();
        Label_0176: {
            if (this$roadArea == null) {
                if (other$roadArea == null) {
                    break Label_0176;
                }
            }
            else if (this$roadArea.equals(other$roadArea)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$unitPrice = this.getUnitPrice();
        final Object other$unitPrice = other.getUnitPrice();
        Label_0213: {
            if (this$unitPrice == null) {
                if (other$unitPrice == null) {
                    break Label_0213;
                }
            }
            else if (this$unitPrice.equals(other$unitPrice)) {
                break Label_0213;
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
        return other instanceof CiRoadWork;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $roadGrade = this.getRoadGrade();
        result = result * 59 + (($roadGrade == null) ? 43 : $roadGrade.hashCode());
        final Object $roadLength = this.getRoadLength();
        result = result * 59 + (($roadLength == null) ? 43 : $roadLength.hashCode());
        final Object $roadWidth = this.getRoadWidth();
        result = result * 59 + (($roadWidth == null) ? 43 : $roadWidth.hashCode());
        final Object $roadArea = this.getRoadArea();
        result = result * 59 + (($roadArea == null) ? 43 : $roadArea.hashCode());
        final Object $unitPrice = this.getUnitPrice();
        result = result * 59 + (($unitPrice == null) ? 43 : $unitPrice.hashCode());
        final Object $projectCost = this.getProjectCost();
        result = result * 59 + (($projectCost == null) ? 43 : $projectCost.hashCode());
        return result;
    }
}
