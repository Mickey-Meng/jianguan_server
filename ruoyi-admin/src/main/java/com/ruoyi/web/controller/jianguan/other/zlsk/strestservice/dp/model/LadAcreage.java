package com.ruoyi.web.controller.jianguan.other.zlsk.strestservice.dp.model;

import java.io.*;
import io.swagger.annotations.*;

public class LadAcreage implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "\u5de5\u7a0bid", required = true)
    private Integer engineeringId;
    @ApiModelProperty("\u603b\u9762\u79ef")
    private Double totalArea;
    @ApiModelProperty("\u96c6\u4f53\u7528\u5730\u9762\u79ef")
    private Double collectiveLandArea;
    @ApiModelProperty("\u57ce\u9547\u5efa\u8bbe\u7528\u5730\u9762\u79ef")
    private Double constructionLandArea;
    @ApiModelProperty("\u4eba\u5747\u5efa\u8bbe\u7528\u5730\u9762\u79ef")
    private Double capiteLandArea;
    @ApiModelProperty("\u4eba\u5747\u8865\u507f\u5efa\u7b51\u9762\u79ef")
    private Double capiteCompensatedArea;
    @ApiModelProperty("\u5bb9\u79ef\u7387")
    private Double plotRatio;
    @ApiModelProperty("\u8015\u5730\u9762\u79ef")
    private Double cultivatedArea;
    @ApiModelProperty("\u57fa\u672c\u519c\u7530\u9762\u79ef")
    private Double farmlandArea;
    @ApiModelProperty("\u73b0\u72b6\u5de5\u4e1a\u5382\u623f\u5360\u5730\u9762\u79ef")
    private Double industrialPlantArea;
    @ApiModelProperty("\u73b0\u72b6\u56fd\u6709\u7528\u5730\u9762\u79ef")
    private Double publicznoLandArea;

    public Integer getEngineeringId() {
        return this.engineeringId;
    }

    public Double getTotalArea() {
        return this.totalArea;
    }

    public Double getCollectiveLandArea() {
        return this.collectiveLandArea;
    }

    public Double getConstructionLandArea() {
        return this.constructionLandArea;
    }

    public Double getCapiteLandArea() {
        return this.capiteLandArea;
    }

    public Double getCapiteCompensatedArea() {
        return this.capiteCompensatedArea;
    }

    public Double getPlotRatio() {
        return this.plotRatio;
    }

    public Double getCultivatedArea() {
        return this.cultivatedArea;
    }

    public Double getFarmlandArea() {
        return this.farmlandArea;
    }

    public Double getIndustrialPlantArea() {
        return this.industrialPlantArea;
    }

    public Double getPublicznoLandArea() {
        return this.publicznoLandArea;
    }

    public void setEngineeringId(final Integer engineeringId) {
        this.engineeringId = engineeringId;
    }

    public void setTotalArea(final Double totalArea) {
        this.totalArea = totalArea;
    }

    public void setCollectiveLandArea(final Double collectiveLandArea) {
        this.collectiveLandArea = collectiveLandArea;
    }

    public void setConstructionLandArea(final Double constructionLandArea) {
        this.constructionLandArea = constructionLandArea;
    }

    public void setCapiteLandArea(final Double capiteLandArea) {
        this.capiteLandArea = capiteLandArea;
    }

    public void setCapiteCompensatedArea(final Double capiteCompensatedArea) {
        this.capiteCompensatedArea = capiteCompensatedArea;
    }

    public void setPlotRatio(final Double plotRatio) {
        this.plotRatio = plotRatio;
    }

    public void setCultivatedArea(final Double cultivatedArea) {
        this.cultivatedArea = cultivatedArea;
    }

    public void setFarmlandArea(final Double farmlandArea) {
        this.farmlandArea = farmlandArea;
    }

    public void setIndustrialPlantArea(final Double industrialPlantArea) {
        this.industrialPlantArea = industrialPlantArea;
    }

    public void setPublicznoLandArea(final Double publicznoLandArea) {
        this.publicznoLandArea = publicznoLandArea;
    }

    @Override
    public String toString() {
        return "LadAcreage(engineeringId=" + this.getEngineeringId() + ", totalArea=" + this.getTotalArea() + ", collectiveLandArea=" + this.getCollectiveLandArea() + ", constructionLandArea=" + this.getConstructionLandArea() + ", capiteLandArea=" + this.getCapiteLandArea() + ", capiteCompensatedArea=" + this.getCapiteCompensatedArea() + ", plotRatio=" + this.getPlotRatio() + ", cultivatedArea=" + this.getCultivatedArea() + ", farmlandArea=" + this.getFarmlandArea() + ", industrialPlantArea=" + this.getIndustrialPlantArea() + ", publicznoLandArea=" + this.getPublicznoLandArea() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LadAcreage)) {
            return false;
        }
        final LadAcreage other = (LadAcreage)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$engineeringId = this.getEngineeringId();
        final Object other$engineeringId = other.getEngineeringId();
        Label_0065: {
            if (this$engineeringId == null) {
                if (other$engineeringId == null) {
                    break Label_0065;
                }
            }
            else if (this$engineeringId.equals(other$engineeringId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$totalArea = this.getTotalArea();
        final Object other$totalArea = other.getTotalArea();
        Label_0102: {
            if (this$totalArea == null) {
                if (other$totalArea == null) {
                    break Label_0102;
                }
            }
            else if (this$totalArea.equals(other$totalArea)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$collectiveLandArea = this.getCollectiveLandArea();
        final Object other$collectiveLandArea = other.getCollectiveLandArea();
        Label_0139: {
            if (this$collectiveLandArea == null) {
                if (other$collectiveLandArea == null) {
                    break Label_0139;
                }
            }
            else if (this$collectiveLandArea.equals(other$collectiveLandArea)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$constructionLandArea = this.getConstructionLandArea();
        final Object other$constructionLandArea = other.getConstructionLandArea();
        Label_0176: {
            if (this$constructionLandArea == null) {
                if (other$constructionLandArea == null) {
                    break Label_0176;
                }
            }
            else if (this$constructionLandArea.equals(other$constructionLandArea)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$capiteLandArea = this.getCapiteLandArea();
        final Object other$capiteLandArea = other.getCapiteLandArea();
        Label_0213: {
            if (this$capiteLandArea == null) {
                if (other$capiteLandArea == null) {
                    break Label_0213;
                }
            }
            else if (this$capiteLandArea.equals(other$capiteLandArea)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$capiteCompensatedArea = this.getCapiteCompensatedArea();
        final Object other$capiteCompensatedArea = other.getCapiteCompensatedArea();
        Label_0250: {
            if (this$capiteCompensatedArea == null) {
                if (other$capiteCompensatedArea == null) {
                    break Label_0250;
                }
            }
            else if (this$capiteCompensatedArea.equals(other$capiteCompensatedArea)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$plotRatio = this.getPlotRatio();
        final Object other$plotRatio = other.getPlotRatio();
        Label_0287: {
            if (this$plotRatio == null) {
                if (other$plotRatio == null) {
                    break Label_0287;
                }
            }
            else if (this$plotRatio.equals(other$plotRatio)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$cultivatedArea = this.getCultivatedArea();
        final Object other$cultivatedArea = other.getCultivatedArea();
        Label_0324: {
            if (this$cultivatedArea == null) {
                if (other$cultivatedArea == null) {
                    break Label_0324;
                }
            }
            else if (this$cultivatedArea.equals(other$cultivatedArea)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$farmlandArea = this.getFarmlandArea();
        final Object other$farmlandArea = other.getFarmlandArea();
        Label_0361: {
            if (this$farmlandArea == null) {
                if (other$farmlandArea == null) {
                    break Label_0361;
                }
            }
            else if (this$farmlandArea.equals(other$farmlandArea)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$industrialPlantArea = this.getIndustrialPlantArea();
        final Object other$industrialPlantArea = other.getIndustrialPlantArea();
        Label_0398: {
            if (this$industrialPlantArea == null) {
                if (other$industrialPlantArea == null) {
                    break Label_0398;
                }
            }
            else if (this$industrialPlantArea.equals(other$industrialPlantArea)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$publicznoLandArea = this.getPublicznoLandArea();
        final Object other$publicznoLandArea = other.getPublicznoLandArea();
        if (this$publicznoLandArea == null) {
            if (other$publicznoLandArea == null) {
                return true;
            }
        }
        else if (this$publicznoLandArea.equals(other$publicznoLandArea)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LadAcreage;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $engineeringId = this.getEngineeringId();
        result = result * 59 + (($engineeringId == null) ? 43 : $engineeringId.hashCode());
        final Object $totalArea = this.getTotalArea();
        result = result * 59 + (($totalArea == null) ? 43 : $totalArea.hashCode());
        final Object $collectiveLandArea = this.getCollectiveLandArea();
        result = result * 59 + (($collectiveLandArea == null) ? 43 : $collectiveLandArea.hashCode());
        final Object $constructionLandArea = this.getConstructionLandArea();
        result = result * 59 + (($constructionLandArea == null) ? 43 : $constructionLandArea.hashCode());
        final Object $capiteLandArea = this.getCapiteLandArea();
        result = result * 59 + (($capiteLandArea == null) ? 43 : $capiteLandArea.hashCode());
        final Object $capiteCompensatedArea = this.getCapiteCompensatedArea();
        result = result * 59 + (($capiteCompensatedArea == null) ? 43 : $capiteCompensatedArea.hashCode());
        final Object $plotRatio = this.getPlotRatio();
        result = result * 59 + (($plotRatio == null) ? 43 : $plotRatio.hashCode());
        final Object $cultivatedArea = this.getCultivatedArea();
        result = result * 59 + (($cultivatedArea == null) ? 43 : $cultivatedArea.hashCode());
        final Object $farmlandArea = this.getFarmlandArea();
        result = result * 59 + (($farmlandArea == null) ? 43 : $farmlandArea.hashCode());
        final Object $industrialPlantArea = this.getIndustrialPlantArea();
        result = result * 59 + (($industrialPlantArea == null) ? 43 : $industrialPlantArea.hashCode());
        final Object $publicznoLandArea = this.getPublicznoLandArea();
        result = result * 59 + (($publicznoLandArea == null) ? 43 : $publicznoLandArea.hashCode());
        return result;
    }
}
